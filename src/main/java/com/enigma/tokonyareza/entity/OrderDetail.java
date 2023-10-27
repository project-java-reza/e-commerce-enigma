package com.enigma.tokonyareza.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "m_order")
@Builder(toBuilder = true)
public class OrderDetail {

    private String id;
}
