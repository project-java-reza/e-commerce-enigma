package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.exception.DuplicateEmailException;
import com.enigma.tokonyareza.repository.CustomerRepository;
import com.enigma.tokonyareza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already used");
        }
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
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
        } else {
 throw new RuntimeException("Pelanggan dengan ID " + customer.getId() + " tidak ditemukan. Tidak dapat melakukan pembaruan.");
        }
    }

    @Override
    public void deleteById(String id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
    }
}
