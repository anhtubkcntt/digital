package com.digital.shop.service;

import com.digital.shop.model.dto.request.ShopCreateRequest;
import com.digital.shop.model.dto.response.ShopResponse;

import java.util.List;

public interface ShopService {

    ShopResponse createShop(ShopCreateRequest shopCreateRequest);
    List<ShopResponse> findAllShop();
}
