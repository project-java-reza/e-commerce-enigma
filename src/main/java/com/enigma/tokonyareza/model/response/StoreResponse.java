package com.enigma.tokonyareza.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class StoreResponse {

    private String id;
    private String storeName;
    private String storeAddress;
}
