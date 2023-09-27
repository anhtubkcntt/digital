package com.digital.order.model.dto.request;

import lombok.Data;

@Data
public class ShopCreateRequest {

    private String shopName;
    private String shopAddress;
    private String contact;
    private String openTime;
    private String closeTime;
}
