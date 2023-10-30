package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.entity.Order;
import com.enigma.tokonyareza.model.request.OrderRequest;
import com.enigma.tokonyareza.model.response.CommonResponse;
import com.enigma.tokonyareza.model.response.OrderResponse;
import com.enigma.tokonyareza.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        OrderResponse orderResponse = orderService.createOrder(orderRequest);
        if(orderResponse.getError() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("Failed to Create New Order: " + orderResponse.getError())
                            .data(orderResponse)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .message("Successfully Create New Order")
                            .data(orderResponse)
                            .build());
        }
    }

}
