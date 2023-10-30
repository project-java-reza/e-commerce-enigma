package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.*;
import com.enigma.tokonyareza.model.request.OrderRequest;
import com.enigma.tokonyareza.model.response.*;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductPriceService productPriceService;
    private final OrderDetailRepository orderDetailRepository;


    @Override
    @Transactional(rollbackOn = Exception.class)
    public OrderResponse createOrder(OrderRequest orderRequest) {

        // TODO 1 : Validate customer
        Customer customer = customerService.getById(orderRequest.getCustomerId());


        // TODO 2 : Convert orderDetailRequest to orderDetail
        List<OrderDetail> orderDetails = orderRequest.getOrderDetail().stream().map(orderDetailRequest -> {

            //TODO 3 : Validate Product Price
            ProductPrice productPrice = productPriceService.getById(orderDetailRequest.getProductPriceId());
            return OrderDetail.builder()
                    .productPrice(productPrice)
                    .quantity(orderDetailRequest.getOrderQuantity())
                    .build();
        }).collect(Collectors.toList());

        // TODO 4 : Crete New Order
        Order order = Order.builder()
                .customer(customer)
                .transDate(LocalDateTime.now())
                .orderDetails(orderDetails)
                .build();
        orderRepository.saveAndFlush(order);

        List<OrderDetailResponse> orderDetailResponses = order.getOrderDetails().stream().map(orderDetail -> {

            //TODO 5 : Set order from orderDetail after creating new order
            orderDetail.setOrder(order);
            System.out.println(order);

            // TODO 6 : Change the stock from the purchase quantity
            ProductPrice currentProductPrice = orderDetail.getProductPrice();
            currentProductPrice.setStock(currentProductPrice.getStock() - orderDetail.getQuantity());

            return OrderDetailResponse.builder()
                    .orderDetailId(orderDetail.getId())
                    .orderResponseQuantity(orderDetail.getQuantity())

                    //TODO 7 : Convert product to productResponse(from productPrice)
                    .product(ProductResponse.builder()
                            .id(currentProductPrice.getProduct().getId())
                            .productName(currentProductPrice.getProduct().getName())
                            .description(currentProductPrice.getProduct().getDescription())
                            .price(currentProductPrice.getPrice())
                            .stock(currentProductPrice.getStock())

                            //TODO 8 : Convert store to storeResponse (from productPrice)
                            .store(StoreResponse.builder()
                                    .id(currentProductPrice.getStore().getId())
                                    .storeName(currentProductPrice.getStore().getName())
                                    .address(currentProductPrice.getStore().getAddress())
                                    .build())
                            .build())
                    .build();
        }).collect(Collectors.toList());

        //TODO 9 : Convert customer to customerResponse\
        CustomerResponse customerResponse = CustomerResponse.builder()
                .customerId(customer.getId())
                .name(customer.getName())
                .build();

        return OrderResponse.builder()
                .orderId(order.getId())
                .customer(customerResponse)
                .transDate(order.getTransDate())
                .orderDetail(orderDetailResponses)
                .build();
    }

    @Override
    public OrderResponse getOrderById(String id) {
        return null;
    }

    @Override
    public List<OrderResponse> getAllTransaction() {
        return null;
    }
}
