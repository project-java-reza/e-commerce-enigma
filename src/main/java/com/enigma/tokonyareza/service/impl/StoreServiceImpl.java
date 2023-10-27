package com.enigma.tokonyareza.service.impl;
import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.model.response.StoreResponse;
import com.enigma.tokonyareza.repository.StoreRepository;
import com.enigma.tokonyareza.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store create(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public Store getById(String id) {
        return storeRepository.findById(id).get();
    }

    @Override
    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store update(Store store) {
        Store currentStore = getById(store.getId());
        if (currentStore != null) {
            return storeRepository.save(store);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        storeRepository.deleteById(id);
    }

    @Override
    public List<StoreResponse> getAllResponse() {

        List<Store> stores = storeRepository.findAll();
        List<StoreResponse> responses = new ArrayList<>();
        for (Store store : stores) {
            StoreResponse response = StoreResponse.builder()
                    .id(store.getId())
                    .storeName(store.getName())
                    .address(store.getAddress())
                    .build();
            responses.add(response);
        }
        return responses;
    }
}
