package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.*;
import com.enigma.tokonyareza.model.request.OrderRequest;
import com.enigma.tokonyareza.model.response.OrderResponse;
import com.enigma.tokonyareza.repository.OrderDetailRepository;
import com.enigma.tokonyareza.repository.OrderRepository;
import com.enigma.tokonyareza.repository.ProductPriceRepository;
import com.enigma.tokonyareza.service.*;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final ProductPriceRepository productPriceRepository;

    private final ProductPriceService productPriceService;

    private final CustomerService customerService;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderResponse createOrder(OrderRequest request) {

        System.out.println("Customer Id " + request.getCustomerId());
        Customer customer = customerService.getById(request.getCustomerId());

        System.out.println("Product ID " + request.getProductPriceId());
        ProductPrice productPrice = productPriceService.getById(request.getProductPriceId());

        if (productPrice.getStock() >= request.getOrderQuantity()) {
            Integer newStock = productPrice.getStock() - request.getOrderQuantity();
            productPrice.setStock(newStock);
                productPriceRepository.saveAndFlush(productPrice);

                Order order = Order.builder()
                        .transDate(LocalDateTime.now())
                        .customer(customer)
                        .build();

                orderRepository.saveAndFlush(order);

                OrderDetail orderDetail = OrderDetail.builder()
                        .quantity(request.getOrderQuantity())
                        .order(order)
                        .productPrice(productPrice)
                        .build();

                orderDetailRepository.saveAndFlush(orderDetail);

                return OrderResponse.builder()
                        .orderId(order.getId())
                        .customerName(customer.getName())
                        .customerAddress(customer.getAddress())
                        .customerMobilePhone(customer.getMobilePhone())
                        .customerEmail(customer.getEmail())
                        .build();
            } else {
                return OrderResponse.builder()
                        .error("Product Out Of Stock.")
                        .build();
            }
    }

}
