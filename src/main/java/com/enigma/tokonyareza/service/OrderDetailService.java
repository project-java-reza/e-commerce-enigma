package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.OrderDetail;

public interface OrderDetailService {

    OrderDetail createOrderDetail(OrderDetail orderDetail);

    OrderDetail getById(String id);

}
