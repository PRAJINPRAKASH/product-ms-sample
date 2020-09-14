package com.productmicroservice.api.services;

import com.productmicroservice.api.entity.Product;
import com.productmicroservice.api.exceptions.ResourceNotFoundException;
import com.productmicroservice.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach(result::add);
        return result;
    }
    public List<Product> findProducts(String name) {
        return productRepository.findProductsByNameContains(name);
    }

    @Cacheable(value = "products",key = "#id")
    public Product getProductById(UUID id)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        return product;
    }
    @CacheEvict(value = "products",allEntries = true)
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @CacheEvict(value = "products",allEntries = true)
    public Product updateProduct(UUID id,Product productDetails) throws ResourceNotFoundException {

        productDetails.setId(getProductById(id).getId());
        final Product updatedProduct = productRepository.save(productDetails);
        return updatedProduct;
    }

    @CacheEvict(value = "products",allEntries = true)
    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
