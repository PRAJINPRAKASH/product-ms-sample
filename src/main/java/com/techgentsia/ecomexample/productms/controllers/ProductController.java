package com.techgentsia.ecomexample.productms.controllers;

import com.techgentsia.ecomexample.productms.entity.Product;
import com.techgentsia.ecomexample.productms.exceptions.ResourceNotFoundException;
import com.techgentsia.ecomexample.productms.services.ProductService;
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
    private ProductService productService;


    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> findProducts(@Param(value = "name") String name) {
        return productService.findProducts(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") UUID id) throws ResourceNotFoundException {
        Product Product = productService.getProductById(id);
        return ResponseEntity.ok().body(Product);
    }

    @PostMapping("/")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") UUID id,
                                                 @Valid @RequestBody Product productDetails) throws ResourceNotFoundException {
        final Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(value = "id") UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
