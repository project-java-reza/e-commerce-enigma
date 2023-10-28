package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Order;

import java.util.List;

public interface OrderService {

    Order create(Order order);

    Order getById(String id);

    List<Order> getAll();

    Order update(Order order);

    void delete(String id);
}
