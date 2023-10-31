package com.enigma.tokonyareza.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "m_user_credential")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class UserCredential {


    @Id
    @GenericGenerator(strategy = "uuid2", name= "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "user_id")
    private String id;

    @Column(unique = true)
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "m_user_role",
    joinColumns = @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id"
    ),inverseJoinColumns = @JoinColumn(
            name = "role_id",
            referencedColumnName = "role_id"
    ))

    private List<Role> roles;
}
