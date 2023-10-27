package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping
    public List<Product> getAllProduct() {
        return productService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getById(id);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.delete(id);
    }
}
