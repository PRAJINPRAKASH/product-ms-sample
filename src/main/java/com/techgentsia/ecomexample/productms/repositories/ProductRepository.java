package com.techgentsia.ecomexample.productms.repositories;

import com.techgentsia.ecomexample.productms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findProductsByNameContains(String name);
}
