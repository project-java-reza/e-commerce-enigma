package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.entity.ProductPrice;
import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.model.request.ProductRequest;
import com.enigma.tokonyareza.model.response.ProductResponse;
import com.enigma.tokonyareza.repository.ProductRepository;
import com.enigma.tokonyareza.service.ProductPriceService;
import com.enigma.tokonyareza.service.ProductService;
import com.enigma.tokonyareza.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class ProductServiceImplTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductPriceService productPriceService = mock(ProductPriceService.class);

    private final StoreService storeService = mock(StoreService.class);

    private final ProductService productService = new ProductServiceImpl(productRepository, storeService, productPriceService);


    @Test
    void create() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void itShouldReturnProductWhenCreateNewProduct() {
        // data dummy
        // karena pakai ProductRequest di parameter kita pakai data dummy request
        ProductRequest dummyRequest = new ProductRequest();
        dummyRequest.setProductId("product1");
        dummyRequest.setStoreId("12344");
        dummyRequest.setProductName("Store 1");
        dummyRequest.setDescription("Enak dipakai");
        dummyRequest.setPrice(150000L);
        dummyRequest.setStock(20);

        // data dummy Store
        Store dummyStore = new Store();
        dummyStore.setId("store21");
        dummyStore.setName("Toko Jaya");
        dummyStore.setAddress("Jalan Raya kemayoran");

        // data dummy Product
        Product dummyProduct= new Product();
        dummyProduct.setId(dummyRequest.getProductId());
        dummyProduct.setName(dummyRequest.getProductName());
        dummyProduct.setDescription(dummyRequest.getDescription());

        // data dummy Product Price
        ProductPrice dummyProductPrice = new ProductPrice();
        dummyProductPrice.setPrice(dummyRequest.getPrice());
        dummyProductPrice.setStock(dummyRequest.getStock());

        //mock ada 3 storeService, productRepository, ProductPriceService call method yang di mock
        when(storeService.getById(dummyRequest.getStoreId())).thenReturn(dummyStore);
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(dummyProduct);
        when(productPriceService.create(any(ProductPrice.class))).thenReturn(dummyProductPrice);

        // perform
        ProductResponse actualProduct =productService.createProduct(dummyRequest);

        // verify
        verify(storeService, times(1)).getById(dummyRequest.getStoreId());
        verify(productRepository, times(1)).saveAndFlush(any(Product.class));
        verify(productPriceService, times(1)).create(any(ProductPrice.class));

        // assert
        assertEquals(dummyProduct.getName(), actualProduct.getProductName());
        assertEquals(dummyProductPrice.getPrice(), actualProduct.getPrice());
        assertEquals(dummyStore.getName(), actualProduct.getStore().getStoreName());
    }
}