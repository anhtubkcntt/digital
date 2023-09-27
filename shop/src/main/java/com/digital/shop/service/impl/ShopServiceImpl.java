package com.digital.shop.service.impl;

import com.digital.shop.exception.BusinessException;
import com.digital.shop.model.dto.request.ShopCreateRequest;
import com.digital.shop.model.dto.response.ShopResponse;
import com.digital.shop.model.entity.ShopEntity;
import com.digital.shop.model.mapping.ShopMapping;
import com.digital.shop.repository.ShopRepo;
import com.digital.shop.service.ShopService;
import com.digital.shop.utils.CommonUtils;
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
