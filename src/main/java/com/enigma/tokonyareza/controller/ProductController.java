package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/products")
    public Product createNewProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping(value = "/products")
    public List<Product> getAllProduct() {
        return productService.getAll();
    }

    @GetMapping(value = "/products/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getById(id);
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.delete(id);
    }

}
