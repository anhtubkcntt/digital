package com.digital.order.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "shop")
@Entity
@NoArgsConstructor
public class ShopEntity {

    @Id
    @Column(name = "shop_id")
    private String shopId;
    @Column(name = "shop_code")
    private String shopCode;
    @Column(name = "shop_name")
    private String shopName;
    @Column(name = "shop_address")
    private String shopAddress;
    @Column(name = "contact")
    private String contact;
    @Column(name = "opentime")
    private String openTime;
    @Column(name = "closetime")
    private String closeTime;
}
