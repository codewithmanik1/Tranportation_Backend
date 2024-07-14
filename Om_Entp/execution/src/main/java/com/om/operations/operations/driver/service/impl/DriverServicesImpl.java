package com.om.operations.operations.driver.service.impl;

import com.om.operations.common.ApiResponse;
import com.om.operations.operations.admin.entity.AssignVehicle;
import com.om.operations.operations.admin.repository.AssignVehicleRepo;
import com.om.operations.operations.driver.dto.request.ShopSalesReqDto;
import com.om.operations.operations.driver.entity.Sales;
import com.om.operations.operations.driver.repository.SalesRepo;
import com.om.operations.operations.driver.service.DriverServices;
import com.om.operations.storage.FileSystemStorageServiceMaster;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DriverServicesImpl implements DriverServices {

    @Autowired
    private AssignVehicleRepo assignVehicleRepo;

    @Autowired
    private SalesRepo salesRepo;

    @Autowired
    private FileSystemStorageServiceMaster fileSystemStorageServiceMaster;

    @Override
    @Transactional
    public ResponseEntity<?> saveShopSaleByDriver(ShopSalesReqDto shopSalesReqDto) {
        var response = new ApiResponse<>();

        Sales sales = new Sales();
        sales.setAssignId(shopSalesReqDto.getAssignId());
        sales.setShopId(shopSalesReqDto.getShopId());
        sales.setIsCash(shopSalesReqDto.getIsCash());
        sales.setCashAmount(shopSalesReqDto.getCashAmount());
        sales.setIsOnline(shopSalesReqDto.getIsOnline());
        sales.setOnlineAmount(shopSalesReqDto.getOnlineAmount());
        sales.setIsCheck(shopSalesReqDto.getIsCheck());
        sales.setCheckAmount(shopSalesReqDto.getCheckAmount());
        sales.setIsBalance(shopSalesReqDto.getIsBalance());
        sales.setBalanceAmount(shopSalesReqDto.getBalanceAmount());
        sales.setIsDiscount(shopSalesReqDto.getIsDiscount());
        sales.setDiscountAmount(shopSalesReqDto.getDiscountAmount());

        //save photos
        try {
            sales.setOnlinePhoto(storeImage(shopSalesReqDto.getOnlinePhoto()));
            sales.setCheckPhoto(storeImage(shopSalesReqDto.getCheckPhoto()));
            sales.setBalancePhoto(storeImage(shopSalesReqDto.getBalancePhoto()));
        } catch (IOException e) {
            response.responseMethod(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save photo", null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Sales savedSales = salesRepo.save(sales);

        //calculate total sale : calculations
        Double totalSaleForTheShop = calculateTotalSale(savedSales);

        //update data in the assign vehicle
        Optional<AssignVehicle> optionalAssignVehicle = assignVehicleRepo.findById(shopSalesReqDto.getAssignId().getId());
        if (optionalAssignVehicle.isPresent()) {
            AssignVehicle assignVehicle = optionalAssignVehicle.get();
            updateAssignVehicle(assignVehicle, totalSaleForTheShop, savedSales.getDiscountAmount());
            assignVehicleRepo.save(assignVehicle);
        } else {
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "AssignVehicle not found", null, null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        //note:
        //Handle Balance which is remaining by shops that will be entries in table while doing settlement by the driver for that assign Id

        response.responseMethod(HttpStatus.OK.value(), "Data saved successfully", null, null);
        return ResponseEntity.ok(response);
    }

    private String storeImage(String imageBase64) throws IOException {
        if (imageBase64 != null) {
            String uniqueFilename = System.currentTimeMillis() + ".jpg";
            fileSystemStorageServiceMaster.storeBaseImage(imageBase64, uniqueFilename, FileSystemStorageServiceMaster.PAYMENTIMAGES);
            return "/" + FileSystemStorageServiceMaster.PAYMENTIMAGES + "/" + uniqueFilename;
        }
        return null;
    }

    private Double calculateTotalSale(Sales sales) {
        return sales.getCashAmount() + sales.getOnlineAmount() + sales.getCheckAmount() + sales.getBalanceAmount() + sales.getDiscountAmount();
    }

    private void updateAssignVehicle(AssignVehicle assignVehicle, Double totalSale, Double discountAmount) {
        assignVehicle.setReturnInVehicle(assignVehicle.getReturnInVehicle() - totalSale);
        assignVehicle.setTotalDiscount(assignVehicle.getTotalDiscount() + discountAmount);
        assignVehicle.setTotalSale(assignVehicle.getTotalSale() + totalSale);
    }


    @Override
    public ResponseEntity<?> getShopAutoComplete(Long routeId, String searchString) {
        var response = new ApiResponse<>();
        List<Map<String, Object>> getData = salesRepo.getShopAutoComplete(routeId, searchString);

        if(!getData.isEmpty())
            response.responseMethod(HttpStatus.OK.value(), "Data fetch successfully", getData, null);
        else
            response.responseMethod(HttpStatus.NOT_FOUND.value(), "Data not found", getData, null);

        return ResponseEntity.ok(response);
    }
}
