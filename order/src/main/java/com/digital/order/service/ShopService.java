package com.digital.order.service;

import com.digital.order.model.dto.request.ShopCreateRequest;
import com.digital.order.model.dto.response.ShopResponse;

import java.util.List;

public interface ShopService {

    ShopResponse createShop(ShopCreateRequest shopCreateRequest);
    List<ShopResponse> findAllShop();
}
