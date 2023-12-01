package com.enigma.tokonyareza.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerResponse {

    private String customerId;
    private String name;


}
