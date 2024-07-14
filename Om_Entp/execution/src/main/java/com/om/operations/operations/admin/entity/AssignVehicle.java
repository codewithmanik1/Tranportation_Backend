package com.om.operations.operations.admin.entity;

import com.om.operations.masters.entity.Employee;
import com.om.operations.masters.entity.Route;
import com.om.operations.masters.entity.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "assign_vehicle")
public class AssignVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicleId;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route routeId;

    @ManyToOne
    @JoinColumn(name = "assign_by_id")
    private Employee assignById;

    @Column(name = "assign_date_time")
    private LocalDateTime assignDateTime = LocalDateTime.now();

    @Column(name = "is_complete")
    private Boolean isComplete = false;

    @Column(name = "total_material")
    @NotNull(message = "Total Material cannot be null")
    private Double totalMaterial;

    @Column(name = "total_sale")
    private Double totalSale = 0.00;

    @Column(name = "return_in_vehicle")
    private Double returnInVehicle;

    @Column(name = "total_expense")
    private Double totalExpense = 0.00;

    @Column(name = "total_discount")
    private Double totalDiscount = 0.00;
}
