package com.digital.shop.model.mapping;

import com.digital.shop.model.dto.request.ShopCreateRequest;
import com.digital.shop.model.dto.response.ShopResponse;
import com.digital.shop.model.entity.ShopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopMapping {

    ShopMapping INSTANCE = Mappers.getMapper(ShopMapping.class);

    ShopEntity convertFromRequest(ShopCreateRequest shopCreateRequest);

    ShopResponse convertToResponse(ShopEntity shopEntity);
}
