package com.mprog.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;

    private String firstName;

    private String lastname;

    @Column(name = "birthday")
    private LocalDate birthday;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Role role;

}

