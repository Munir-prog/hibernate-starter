package com.mprog.entity;

import com.mprog.converter.BirthdayConverter;
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

//    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birthday")
    private Birthday birthday;

    @Enumerated(EnumType.STRING)
    private Role role;

}

