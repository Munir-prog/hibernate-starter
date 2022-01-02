package com.mprog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "users")
@EqualsAndHashCode(of = "name")
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL}, orphanRemoval = true)
//    @OneToMany(mappedBy = "company", cascade = {CascadeType.ALL})
//    @JoinColumn(name = "company_id")
    private Set<User> users = new HashSet<>();


    public void addUser(User user) {
        users.add(user);
        user.setCompany(this);
    }
}
