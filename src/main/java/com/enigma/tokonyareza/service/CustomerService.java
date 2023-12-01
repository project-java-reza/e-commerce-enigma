package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);

    Customer getById(String id);

    List<Customer> getAll();

    Customer update(Customer customer);

    void deleteById(String id);
}
