package com.digital.order.model.mapping;

import com.digital.order.model.dto.request.ShopCreateRequest;
import com.digital.order.model.dto.response.ShopResponse;
import com.digital.order.model.entity.ShopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShopMapping {

    ShopMapping INSTANCE = Mappers.getMapper(ShopMapping.class);

    ShopEntity convertFromRequest(ShopCreateRequest shopCreateRequest);

    ShopResponse convertToResponse(ShopEntity shopEntity);
}
