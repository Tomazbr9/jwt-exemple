package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.*;

import lombok.*;

import javax.management.relation.Role;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Getter
@Setter
public class UserModel implements Serializable {

    private static final long seriaVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
