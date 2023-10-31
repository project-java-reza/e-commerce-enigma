package com.enigma.tokonyareza.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SellerResponse {

    private String usename;
    private String email;
    private StoreResponse store;
}
