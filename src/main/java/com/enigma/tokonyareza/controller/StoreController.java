package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.model.response.StoreResponse;
import com.enigma.tokonyareza.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class StoreController {

    private final StoreService storeService;

    @PostMapping(value = "/store")
    public Store createNewStore(@RequestBody Store store) {
        return storeService.create(store);
    }

    @GetMapping(value = "/store")
    public List<Store> getAllStore() {
       return storeService.getAll();
    }

    @GetMapping(value = "/store/{id}")
    public Store getStoreById(@PathVariable String id) {
       return storeService.getById(id);
    }

    @PutMapping("/store")
    public Store updateStore(@RequestBody Store store) {
        return storeService.update(store);
    }

    @DeleteMapping("/store/{id}")
    public void deleteStore(@PathVariable String id) {
        storeService.delete(id);
    }

    @GetMapping("/store/dto")
    public ResponseEntity<List<StoreResponse>> getAllStoreResponse() {
        List<StoreResponse> allStoreResponse = storeService.getAllResponse();
        return new ResponseEntity<>(allStoreResponse, HttpStatus.OK);
    }
}
