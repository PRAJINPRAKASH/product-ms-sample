package com.productmicroservice.api.repositories;

import com.productmicroservice.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findProductsByNameContains(String name);
}
