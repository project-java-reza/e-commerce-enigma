package com.enigma.tokonyareza.model.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String orderId;
    private LocalDateTime transDate;
    private CustomerResponse customer;
    private List<OrderDetailResponse> orderDetail;
    private String error;
}
