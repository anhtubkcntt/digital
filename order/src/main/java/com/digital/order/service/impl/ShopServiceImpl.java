package com.digital.order.service.impl;

import com.digital.order.exception.BusinessException;
import com.digital.order.model.dto.request.ShopCreateRequest;
import com.digital.order.model.dto.response.ShopResponse;
import com.digital.order.model.entity.ShopEntity;
import com.digital.order.model.mapping.ShopMapping;
import com.digital.order.repository.ShopRepo;
import com.digital.order.service.ShopService;
import com.digital.order.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    ShopRepo shopRepo;

    @Override
    public ShopResponse createShop(ShopCreateRequest shopCreateRequest) {
        ShopEntity shopEntity = ShopMapping.INSTANCE.convertFromRequest(shopCreateRequest);
        shopEntity.setShopId("SI" + CommonUtils.getRandomNumberString());
        shopEntity.setShopCode("SC" + CommonUtils.getRandomNumberString());
        ShopEntity shopResponse = shopRepo.save(shopEntity);
        return ShopMapping.INSTANCE.convertToResponse(shopResponse);
    }

    @Override
    public List<ShopResponse> findAllShop() {
        List<ShopEntity> shopEntityList = shopRepo.findAll();
        if (shopEntityList.isEmpty()) {
            throw new BusinessException("could not find any shop", HttpStatus.NOT_FOUND);
        }
        List<ShopResponse> shopResponses = new ArrayList<>();
        shopEntityList.forEach(shopEntity -> {
            ShopResponse shopResponse = ShopMapping.INSTANCE.convertToResponse(shopEntity);
            shopResponses.add(shopResponse);
        });
        return shopResponses;
    }
}
