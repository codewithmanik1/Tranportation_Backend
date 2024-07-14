package com.om.operations.masters.service.impl;
import com.om.operations.common.ApiResponse;
import com.om.operations.common.FileUtil;
import com.om.operations.masters.dto.request.*;
import com.om.operations.masters.entity.*;
import com.om.operations.masters.repository.*;
import com.om.operations.masters.service.MasterService;
import com.om.operations.security.JwtUtils;
import com.om.operations.storage.FileSystemStorageServiceMaster;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;


@Service
public class MasterServiceImpl implements MasterService {

//    @Value("${employee.photos.dir}")
//    private String uploadEmployeeImagesDir;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private RouteRepo routeRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private ShopRepo shopRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private FileSystemStorageServiceMaster fileSystemStorageServiceMaster;


    @Override
    public ResponseEntity<?> saveEmployees(EmployeeReqDto employeeReqDto) {
        var response = new ApiResponse<>();

        Employee employee = new Employee();

        if(employeeReqDto.getId() != null){
            employee = employeeRepo.findById(employeeReqDto.getId()).get();
            employee.setLastModifiedBy(employeeReqDto.getUpdatedBy());
            employee.setLastModifiedDate(LocalDateTime.now());
            employee.setIsActive(employeeReqDto.getIsActive());
            response.responseMethod(HttpStatus.OK.value(), "Data updated successfully", null, null);
        }else{
            System.out.println("HERE !!!!!!!!!!!!!!!!!!!!");
            employee.setCreatedBy(employeeReqDto.getCreatedBy());
            employee.setCreatedDate(LocalDateTime.now());
            employee.setLastModifiedBy(employeeReqDto.getCreatedBy());
            employee.setLastModifiedDate(LocalDateTime.now());
            employee.setUserName(employeeReqDto.getUserName());
            employee.setPassword(passwordEncoder.encode(employeeReqDto.getPassword()));
            response.responseMethod(HttpStatus.CREATED.value(), "Employee created successfully", null, null);
        }
        employee.setRoleId(employeeReqDto.getRoleId());
        employee.setFirstName(employeeReqDto.getFirstName());
        employee.setLastName(employeeReqDto.getLastName());
        employee.setMobileNo(employeeReqDto.getMobileNo());
        employee.setAadhaarNo(employeeReqDto.getAadhaarNo());

//        // Ensure the directory exists
//        FileUtil.createDirectoryIfNotExists(uploadEmployeeImagesDir);

        // Decode and save the photo
        try {
            if (employeeReqDto.getPhoto() != null) {
                System.out.println("Code goes here !!!");
                String uniqueFilename = System.currentTimeMillis() + ".jpg";
                fileSystemStorageServiceMaster.storeBaseImage(employeeReqDto.getPhoto(), uniqueFilename, FileSystemStorageServiceMaster.EMPLOYEEIMAGES);
                employee.setPhotoPath("/"+FileSystemStorageServiceMaster.EMPLOYEEIMAGES + "/"+ uniqueFilename);
                System.out.println("Employee imges saves here "+ "/"+FileSystemStorageServiceMaster.EMPLOYEEIMAGES + "/"+ uniqueFilename);
            }else{
                employee.setPhotoPath(null);
            }
        } catch (IOException e) {
            response.responseMethod(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to save image", null, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        employeeRepo.save(employee);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> saveVehicle(VehicleReqDto vehicleReqDto) {
        var response = new ApiResponse<>();

        Vehicle vehicle = new Vehicle();

        if(vehicleReqDto.getId() != null){
            vehicle = vehicleRepo.findById(vehicleReqDto.getId()).get();
            vehicle.setLastModifiedBy(vehicleReqDto.getUpdatedBy());
            vehicle.setLastModifiedDate(LocalDateTime.now());
            vehicle.setIsActive(vehicleReqDto.getIsActive());
            response.responseMethod(HttpStatus.OK.value(), "Data updated successfully", null, null);
        }else{
            vehicle.setCreatedBy(vehicleReqDto.getCreatedBy());
            vehicle.setCreatedDate(LocalDateTime.now());
            vehicle.setLastModifiedBy(vehicleReqDto.getCreatedBy());
            vehicle.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.CREATED.value(), "Vehicle created successfully", null, null);
        }
        vehicle.setVehicleNo(vehicleReqDto.getVehicleNo());
        vehicleRepo.save(vehicle);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> saveRoute(RouteReqDto routeReqDto) {
        var response = new ApiResponse<>();

        Route route = new Route();

        if(routeReqDto.getId() != null){
            route = routeRepo.findById(routeReqDto.getId()).get();
            route.setLastModifiedBy(routeReqDto.getUpdatedBy());
            route.setLastModifiedDate(LocalDateTime.now());
            route.setIsActive(routeReqDto.getIsActive());
            response.responseMethod(HttpStatus.OK.value(), "Data updated successfully", null, null);
        }else{
            route.setCreatedBy(routeReqDto.getCreatedBy());
            route.setCreatedDate(LocalDateTime.now());
            route.setLastModifiedBy(routeReqDto.getCreatedBy());
            route.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.CREATED.value(), "Route created successfully", null, null);
        }
        route.setRouteName(routeReqDto.getRoute());
        routeRepo.save(route);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> saveRole(RoleReqDto roleReqDto) {
        var response = new ApiResponse<>();

        Role role = new Role();

        if(roleReqDto.getId() != null){
            role = roleRepo.findById(roleReqDto.getId()).get();
            role.setLastModifiedBy(roleReqDto.getUpdatedBy());
            role.setLastModifiedDate(LocalDateTime.now());
            role.setIsActive(roleReqDto.getIsActive());
            response.responseMethod(HttpStatus.OK.value(), "Data updated successfully", null, null);
        }else{
            role.setCreatedBy(roleReqDto.getCreatedBy());
            role.setCreatedDate(LocalDateTime.now());
            role.setLastModifiedBy(roleReqDto.getCreatedBy());
            role.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.CREATED.value(), "Route created successfully", null, null);
        }
        role.setRole(roleReqDto.getRole());
        roleRepo.save(role);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> saveShop(ShopReqDto shopReqDto) {
        var response = new ApiResponse<>();

        Shops shops = new Shops();

        if(shopReqDto.getId() != null){
            shops = shopRepo.findById(shopReqDto.getId()).get();
            shops.setLastModifiedBy(shopReqDto.getUpdatedBy());
            shops.setLastModifiedDate(LocalDateTime.now());
            shops.setIsActive(shopReqDto.getIsActive());
            response.responseMethod(HttpStatus.OK.value(), "Data updated successfully", null, null);
        }else{
            shops.setCreatedBy(shopReqDto.getCreatedBy());
            shops.setCreatedDate(LocalDateTime.now());
            shops.setLastModifiedBy(shopReqDto.getCreatedBy());
            shops.setLastModifiedDate(LocalDateTime.now());
            response.responseMethod(HttpStatus.CREATED.value(), "Shop created successfully", null, null);
        }
        shops.setRouteId(shopReqDto.getRouteId());
        shops.setShopName(shopReqDto.getShopName());
        shopRepo.save(shops);
        return ResponseEntity.ok(response);
    }
}
