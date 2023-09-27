package com.digital.order.model.dto.response;

import lombok.Data;

@Data
public class ShopResponse {

    private String shopId;
    private String shopCode;
    private String shopName;
    private String shopAddress;
    private String contact;
    private String openTime;
    private String closeTime;
}
