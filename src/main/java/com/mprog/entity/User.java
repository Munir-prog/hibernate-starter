package com.mprog.entity;

import com.mprog.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@TypeDef(name = "jsonBin", typeClass = JsonBinaryType.class)
public class User {

    @Id
    private String username;

    private String firstName;

    private String lastname;

//    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birthday")
    private Birthday birthday;

//    @Type(type = "jsonb")
    @Type(type = "jsonBin")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

}

