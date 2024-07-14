package com.om.operations.operations.driver.entity;

import com.om.operations.masters.entity.Shops;
import com.om.operations.operations.admin.entity.AssignVehicle;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "assign_id")
    private AssignVehicle assignId;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shops shopId;

    @Column(name = "is_cash")
    private Boolean isCash = false;

    @Column(name = "cash_amount")
    private Double cashAmount = 0.0;

    @Column(name = "is_online")
    private Boolean isOnline = false;

    @Column(name = "online_amount")
    private Double onlineAmount = 0.0;

    @Column(name = "online_photo")
    private String onlinePhoto = null;

    @Column(name = "is_check")
    private Boolean isCheck = false;

    @Column(name = "check_amount")
    private Double checkAmount = 0.0;

    @Column(name = "check_photo")
    private String checkPhoto = null;

    @Column(name = "is_balance")
    private Boolean isBalance = false;

    @Column(name = "balance_amount")
    private Double balanceAmount = 0.0;

    @Column(name = "balance_photo")
    private String balancePhoto = null;

    @Column(name = "is_discount")
    private Boolean isDiscount = false;

    @Column(name = "discount_amount")
    private Double discountAmount = 0.0;

    @Column(name = "payment_accept_date_time")
    private LocalDateTime paymentAcceptDateTime = LocalDateTime.now();


//    @Column(name = "data", columnDefinition = "jsonb")
//    private List<PaymentMode> paymentMode;

}
