package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.repository.CustomerRepository;
import com.enigma.tokonyareza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;


    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer update(Customer customer) {
        Customer currentCustomer= getById(customer.getId());
        if (currentCustomer != null) {
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}
