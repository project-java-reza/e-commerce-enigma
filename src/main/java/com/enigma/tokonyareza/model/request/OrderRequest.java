package com.enigma.tokonyareza.model.request;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {
    private String orderId;
    private Integer orderQuantity;
    private String productPriceId;
    private String customerId;

}
