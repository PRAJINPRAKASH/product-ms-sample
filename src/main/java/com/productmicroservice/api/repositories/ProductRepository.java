package com.productmicroservice.api.repositories;

import com.productmicroservice.api.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {
}
