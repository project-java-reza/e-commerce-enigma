package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @MockBean // bean fake atau inject fake
    private CustomerService customerService;

    @Autowired // data fake atau saat mengirim HTTP Request
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // supaya mapping nya gampang


    @Test
    void itShouldCreateCustomerAndReturnCustomerResponseAndStatusCode() throws Exception{
        // Data Dumy Customer
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("1");
        dummyCustomer.setName("Rizqi");

        when(customerService.create(any(Customer.class))).thenReturn(dummyCustomer);

        // verify ini yang beda, untuk mengirimkan HTTP ke endpoint controller
        mockMvc.perform(post("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dummyCustomer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Successfully create new customer"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()));
        // and Expect sama seperti sebelumnya assertEqual

        verify(customerService, times(1)).create(any(Customer.class));

    }

    @Test
    void isShouldGetAllCustomerAndStatusOk() throws Exception{

        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("1");
        dummyCustomer.setName("Rizqi");

        Customer dummyCustomer2 = new Customer();
        dummyCustomer.setId("2");
        dummyCustomer.setName("Reza");

        List<Customer> dummyCustomers = Arrays.asList(dummyCustomer, dummyCustomer2);

        when(customerService.getAll()).thenReturn(dummyCustomers);


        mockMvc.perform(get("/api/customer"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully get all customer"))
                .andExpect(jsonPath("$.data[0].id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data[0].name").value(dummyCustomer.getName()))
                .andExpect(jsonPath("$.data[1].id").value(dummyCustomer2.getId()))
                .andExpect(jsonPath("$.data[1].name").value(dummyCustomer2.getName()));
        // and Expect sama seperti sebelumnya assertEqual

        verify(customerService, times(1)).getAll();
    }

    @Test
    void isShouldGetDataByIdCustomerAndStatusOk() throws Exception{
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("1");
        dummyCustomer.setName("Rizqi");

        when(customerService.getById(dummyCustomer.getId())).thenReturn(dummyCustomer);

        mockMvc.perform(get("/api/customer/{id}", dummyCustomer.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully get customer by id"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()));

        verify(customerService, times(1)).getById(dummyCustomer.getId());

        System.out.println("Data ID " + dummyCustomer.getId());
    }

    @Test
    void isShouldUpdateDataCustomerAndStatusOk() throws Exception{
        Customer dummyCustomer = new Customer();
        dummyCustomer.setId("1");
        dummyCustomer.setName("Reza");
        dummyCustomer.setAddress("Jalan Raya");
        dummyCustomer.setEmail("Reza@gmail.com");
        dummyCustomer.setMobilePhone("08756456464");

        when(customerService.update(any(Customer.class))).thenReturn(dummyCustomer);
        // yang pake any kalau ngga create atau update

        mockMvc.perform(put("/api/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dummyCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
          .andExpect(jsonPath("$.message").value("Successfully update customer"))
                .andExpect(jsonPath("$.data.id").value(dummyCustomer.getId()))
                .andExpect(jsonPath("$.data.name").value(dummyCustomer.getName()))
                .andExpect(jsonPath("$.data.address").value(dummyCustomer.getAddress()))
                .andExpect(jsonPath("$.data.email").value(dummyCustomer.getEmail()));

        verify(customerService, times(1)).update(any(Customer.class));
    }

    @Test
    void isShouldDeleteDataCustomerAndStatusOk() throws Exception{
        String customerId = "1";

        doNothing().when(customerService).deleteById(customerId);

        mockMvc.perform(delete("/api/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("Successfully delete customer"));

        verify(customerService, times(1)).deleteById(customerId);
    }
}