package com.enigma.tokonyareza.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductPriceRequest {
    private String productId;

    private String productName;

    private String description;

    private Long price;

    private Integer stock;

    private String storeId;

}
