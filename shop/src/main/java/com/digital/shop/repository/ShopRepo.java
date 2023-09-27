package com.digital.shop.repository;

import com.digital.shop.model.entity.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<ShopEntity, String> {
}
