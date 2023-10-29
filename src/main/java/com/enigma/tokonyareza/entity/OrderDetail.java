package com.enigma.tokonyareza.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "t_order_detail")
@Builder
public class OrderDetail {

    @Id
    @GenericGenerator(strategy = "uuid2", name= "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="product_price_id")
    private ProductPrice productPrice;

    private Integer quantity;

}
