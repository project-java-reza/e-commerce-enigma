package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping(value = "/customer")
    public Customer createNewCustomer(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping(value = "/customer")
    public List<Customer> getAllCustomer() {
        return customerService.getAll();
    }

    @GetMapping(value = "/customer/{id}")
    public Customer getCustomerId(@PathVariable String id) {
        return customerService.getById(id);
    }

    @PutMapping("/customer")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.delete(id);
    }

}
