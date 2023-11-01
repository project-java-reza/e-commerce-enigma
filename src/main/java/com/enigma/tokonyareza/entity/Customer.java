package com.enigma.tokonyareza.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "m_customer")
@Builder(toBuilder = true)
public class Customer {

    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(name = "mobile_phone", length = 100, nullable = false, unique = true)
    private String mobilePhone;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    /*@OneToMany(mappedBy = "customer")
    private List<Order> orders;*/

    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
}
