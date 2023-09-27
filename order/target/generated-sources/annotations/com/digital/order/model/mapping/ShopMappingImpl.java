package com.digital.order.model.mapping;

import com.digital.order.model.dto.request.ShopCreateRequest;
import com.digital.order.model.dto.response.ShopResponse;
import com.digital.order.model.entity.ShopEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T16:38:42+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Oracle Corporation)"
)
public class ShopMappingImpl implements ShopMapping {

    @Override
    public ShopEntity convertFromRequest(ShopCreateRequest shopCreateRequest) {
        if ( shopCreateRequest == null ) {
            return null;
        }

        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setShopName( shopCreateRequest.getShopName() );
        shopEntity.setShopAddress( shopCreateRequest.getShopAddress() );
        shopEntity.setContact( shopCreateRequest.getContact() );
        shopEntity.setOpenTime( shopCreateRequest.getOpenTime() );
        shopEntity.setCloseTime( shopCreateRequest.getCloseTime() );

        return shopEntity;
    }

    @Override
    public ShopResponse convertToResponse(ShopEntity shopEntity) {
        if ( shopEntity == null ) {
            return null;
        }

        ShopResponse shopResponse = new ShopResponse();

        shopResponse.setShopId( shopEntity.getShopId() );
        shopResponse.setShopCode( shopEntity.getShopCode() );
        shopResponse.setShopName( shopEntity.getShopName() );
        shopResponse.setShopAddress( shopEntity.getShopAddress() );
        shopResponse.setContact( shopEntity.getContact() );
        shopResponse.setOpenTime( shopEntity.getOpenTime() );
        shopResponse.setCloseTime( shopEntity.getCloseTime() );

        return shopResponse;
    }
}
