package com.mprog.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.CascadeType.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile", "userChats"})
@Builder
@Entity
@Table(name = "users")
@TypeDef(name = "jsonBin", typeClass = JsonBinaryType.class)
public class User {


//    @Id
//    @GeneratedValue(generator = "user_gen", strategy = GenerationType.TABLE)
//    @TableGenerator(name = "user_gen",
//            table = "all_seq",
//            allocationSize = 1,
//            pkColumnName = "table_name",
//            valueColumnName = "pk_value"
//    )
//    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
////    @SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



//    @EmbeddedId
//    @Embedded
//    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;

    //    @Type(type = "jsonb")
    @Type(type = "jsonBin")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;


//    @Transient
//    private String test;


    @ManyToOne(fetch = FetchType.LAZY)
//    @ManyToOne(fetch = FetchType.EAGER, cascade = {ALL})
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(
            mappedBy = "user",
            cascade = ALL,
            fetch = FetchType.LAZY,
            optional = false
    )
    private Profile profile;


    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<UserChat> userChats = new ArrayList<>();

}

