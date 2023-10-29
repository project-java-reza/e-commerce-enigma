package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.OrderDetail;
import com.enigma.tokonyareza.repository.OrderDetailRepository;
import com.enigma.tokonyareza.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail getById(String id) {
        return orderDetailRepository.findById(id).get();
    }
}
