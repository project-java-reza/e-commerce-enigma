package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.repository.StoreRepository;
import com.enigma.tokonyareza.service.StoreService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class StoreServiceImplTest {

    // mock repository atau tiruannya
    private final StoreRepository storeRepository = mock(StoreRepository.class);

    // create service instance
    private final StoreService storeService = new StoreServiceImpl(storeRepository);

    @Test
    void itShouldReturnStoreWhenCreateNewStore() {
        Store dummyStore = new Store();
        dummyStore.setId("123");
        dummyStore.setName("Toko Selamet");

        when(storeRepository.save(any(Store.class))).thenReturn(dummyStore);

        // perform
        Store createStore = storeService.create(dummyStore);

        verify(storeRepository, times(1)).save(dummyStore);

        assertEquals(dummyStore.getId(), createStore.getId());
        assertEquals(dummyStore.getName(), createStore.getName());
    }

    @Test
    void itShouldGetDataStoreOneWhenGetByIdStore() {
        Store dummyStore = new Store();
        dummyStore.setId("123");
        dummyStore.setName("Toko Selamet");

        when(storeRepository.findById(dummyStore.getId())).thenReturn(Optional.of(dummyStore));

        // perform
        Store actualStore = storeService.getById(dummyStore.getId());

        // verify
        verify(storeRepository, times(1)).findById(dummyStore.getId());

        // verify expected and actual
        assertEquals(dummyStore.getId(), actualStore.getId());
        assertEquals(dummyStore.getName(), actualStore.getName());

    }

    @Test
    void itShouldGetAllDataStoreWhenCallGetAll() {
        List<Store> dummyStore = new ArrayList<>();
        dummyStore.add(new Store("1", "123", "Toko Selamet", "Rambutan", "085156811978"));

        dummyStore.add(new Store("2", "124", "Toko makmur", "Jakarta", "0215533" ));

        dummyStore.add(new Store("3", "125", "Toko Jaya Abadi", "Bandung", "08755746"));

        when(storeRepository.findAll()).thenReturn(dummyStore);

        List<Store> actualStore = storeService.getAll();

        verify(storeRepository, times(1)).findAll();

        for (int i = 0; i < dummyStore.size(); i++) {
            assertEquals(dummyStore.get(i).getId(), actualStore.get(i).getId());
            assertEquals(dummyStore.get(i).getName(), actualStore.get(i).getName());
        }
        assertEquals(dummyStore.size(), actualStore.size());
    }

    @Test
    void itShouldUpdateDataStoreWhenCallUpdateById() {

        Store dummyStoreUpdate = new Store();
        dummyStoreUpdate.setId("12312321");
        dummyStoreUpdate.setName("Toko Jaya Abadi");
        dummyStoreUpdate.setAddress("Jalan Raya Baru");
        dummyStoreUpdate.setNoSiup("232323");
        dummyStoreUpdate.setMobilePhone("0824264644");


        when(storeRepository.findById(dummyStoreUpdate.getId())).thenReturn(Optional.of(new Store("1", "1234", "Toko Berkah jaya", "Jakarta", "0851568111")));

        when(storeRepository.save(dummyStoreUpdate)).thenReturn(dummyStoreUpdate);

        // perform
        Store updatedStore = storeService.update(dummyStoreUpdate);

        // Verifikasi pemanggilan hanya 1 kali
        verify(storeRepository, times(1)).findById(dummyStoreUpdate.getId());
        verify(storeRepository, times(1)).save(dummyStoreUpdate);

        // Verifikasi hasil pembaruan
        assertEquals(dummyStoreUpdate.getNoSiup(), updatedStore.getNoSiup());
        assertEquals(dummyStoreUpdate.getName(), updatedStore.getName());

        System.out.println("Data " + dummyStoreUpdate.getName() + " " + updatedStore.getName()); // untuk mengecek isi data nya apa

    }

    @Test
    void itShouldDeleteWhenCallDeleteById() {
        String storeId = "1";
        storeService.deleteById(storeId);
        verify(storeRepository, times(1)).deleteById(storeId);
    }

    // kesimpulannya kita harus butuh data dummy nya
    // di verify dilihat ada berapa di repository itu method yang dipanggil
    // abis itu di cek 1 per 1 di assert Equals, ga cuma NoSiup dan Name
    // unit test selanjutnya kita akan coba yang ProductServiceImpl
}