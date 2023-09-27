package com.digital.shop.controller;

import com.digital.shop.exception.BusinessException;
import com.digital.shop.model.dto.request.ShopCreateRequest;
import com.digital.shop.model.dto.response.ShopResponse;
import com.digital.shop.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
@Slf4j
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping
    public ResponseEntity<ShopResponse> createShop(@RequestBody ShopCreateRequest shopCreateRequest) {
        try {
            return ResponseEntity.ok(shopService.createShop(shopCreateRequest));
        } catch (Exception exception) {
            log.error("createShop exception message: {}", exception.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ShopResponse>> getAllShop() {
        try {
            return ResponseEntity.ok(shopService.findAllShop());
        } catch (Exception exception) {
            log.error("createShop exception message: {}", exception.getMessage());
            throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
