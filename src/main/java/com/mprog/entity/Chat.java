package com.mprog.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "name")
@ToString(exclude = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String name;


    @Builder.Default
    @ManyToMany(mappedBy = "chats")
    private Set<User> users = new HashSet<>();
}

