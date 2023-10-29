package com.enigma.tokonyareza.model.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
   private String orderId;
   private String customerName;
   private String customerAddress;
   private String customerMobilePhone;
   private String customerEmail;
   private String error;


   private LocalDateTime date;
   private OrderDetailResponse orderDetailResponse;
}
