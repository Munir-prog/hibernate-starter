package com.mprog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

    private String firstName;

    private String lastname;

    //    @Convert(converter = BirthdayConverter.class)
    @Column(name = "birthday")
    private Birthday birthday;
}
