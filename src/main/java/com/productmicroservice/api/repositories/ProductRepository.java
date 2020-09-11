package com.productmicroservice.api.repositories;

import com.productmicroservice.api.models.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Override
    @Cacheable(value = "products")
    List<Product> findAll();
    @Override
    @Cacheable(value = "products",key = "#id")
    Optional<Product> findById(UUID id);

    @CacheEvict(value = "products",allEntries = true)
    @Override
    <S extends Product> S save(S s);

    @Override
    @CacheEvict(value = "products",allEntries = true)
    void delete(Product product);

    List<Product> findProductsByNameContains(String name);
}
