package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

import java.io.Serializable;

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

    private Long id;
    private String name;
    private String email;
    private String password;
}
