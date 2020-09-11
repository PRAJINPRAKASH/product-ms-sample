package com.productmicroservice.api.controllers;

import com.productmicroservice.api.exceptions.ResourceNotFoundException;
import com.productmicroservice.api.models.Product;
import com.productmicroservice.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/")
    public Iterable<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    @GetMapping("/search")
    public List<Product> findAllProducts(@Param(value = "name") String name) {
        return productRepository.findProductsByNameContains(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {
        Product Product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));
        return ResponseEntity.ok().body(Product);
    }

    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productRepository.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
                                                   @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        productDetails.setId(product.getId());
        final Product updatedProduct = productRepository.save(productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") UUID id)
            throws ResourceNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + id));

        productRepository.delete(product);
        return ResponseEntity.accepted().body(product);
    }
}
