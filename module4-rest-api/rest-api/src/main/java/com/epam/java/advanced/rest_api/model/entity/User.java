package com.epam.java.advanced.rest_api.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.SoftDelete;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@SoftDelete
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 30)
    @Column(nullable = false, length = 30)
    private String username;

    @Size(max = 80)
    @Column(nullable = false, length = 80)
    private String password;

    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime updated = LocalDateTime.now();

//    @Column(nullable = false)
//    private Boolean deleted = false;
}
