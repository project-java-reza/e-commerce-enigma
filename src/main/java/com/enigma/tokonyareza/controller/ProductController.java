package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.entity.ProductPrice;
import com.enigma.tokonyareza.model.request.ProductRequest;
import com.enigma.tokonyareza.model.response.CommonResponse;
import com.enigma.tokonyareza.model.response.ProductResponse;
import com.enigma.tokonyareza.service.ProductPriceService;
import com.enigma.tokonyareza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    private final ProductPriceService productPriceService;

    @PostMapping(value="/post")
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

    @PostMapping(value = "/all")
    public ResponseEntity<?> createProductAll(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                        .message("Successfully Create New Product")
                        .data(productResponse)
                        .build());
    }

}
