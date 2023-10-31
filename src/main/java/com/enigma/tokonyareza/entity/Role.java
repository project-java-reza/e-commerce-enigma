package com.enigma.tokonyareza.entity;

import com.enigma.tokonyareza.entity.constant.ERole;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "m_role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class Role {

    @Id
    @GenericGenerator(strategy = "uuid2", name= "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "role_id")
    private String id;

    @Enumerated(EnumType.STRING)
    private ERole eRole;



}
