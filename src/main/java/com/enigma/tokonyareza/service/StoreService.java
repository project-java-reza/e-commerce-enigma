package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.model.response.StoreResponse;

import java.util.List;
public interface StoreService { // ngga harus interface cuma wajib harus punya contractfitur, service disini create, getById, update, delete
    Store create (Store store);
    Store getById (String id);
    List<Store> getAll();
    Store update(Store store);
    void deleteById(String id);

    List<StoreResponse> getAllResponse();
}

