package com.techgentsia.ecomexample.productms.services;

import com.techgentsia.ecomexample.productms.repositories.ProductRepository;
import com.techgentsia.ecomexample.productms.entity.Product;
import com.techgentsia.ecomexample.productms.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
