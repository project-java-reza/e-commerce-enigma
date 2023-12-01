package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Order;
import com.enigma.tokonyareza.model.request.OrderRequest;
import com.enigma.tokonyareza.model.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);
    OrderResponse getOrderById(String id);
    List<OrderResponse> getAllTransaction();

}
