package com.mprog.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.xml.stream.XMLStreamConstants.SPACE;

//import static com.mprog.util.StringUtils.SPACE;

@NamedQuery(name = "findUserByName", query = "select u from User u " +
        "left join u.company c " +
        "where u.personalInfo.firstName = :firstname and c.name = :companyName " +
        "order by u.personalInfo.lastname desc")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "userChats", "payments"})
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User implements Comparable<User>, BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AttributeOverride(name = "birthday", column = @Column(name = "birthday"))
    private PersonalInfo personalInfo;

    @Column(unique = true)
    private String username;

    @Type(type = "dmdev")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id") // company_id
    private Company company;

//    @OneToOne(
//            mappedBy = "user",
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    private Profile profile;

    @Builder.Default
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<UserChat> userChats = new HashSet<>();

    @Builder.Default
//    @BatchSize(size = 3)
//    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @Override
    public int compareTo(User o) {
        return username.compareTo(o.username);
    }

    public String fullName() {
        return getPersonalInfo().getFirstName() + " " + getPersonalInfo().getLastname();
    }
}



//
//package com.mprog.entity;
//
//import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
//import lombok.*;
//import org.hibernate.annotations.Type;
//import org.hibernate.annotations.TypeDef;
//
//import javax.persistence.*;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static javax.persistence.CascadeType.*;
//import static javax.xml.stream.XMLStreamConstants.SPACE;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@EqualsAndHashCode(of = "username")
//@ToString(exclude = {"company", "profile", "userChats"})
//@Builder
//@Entity
//@Table(name = "users")
//@TypeDef(name = "jsonBin", typeClass = JsonBinaryType.class)
//@Inheritance(strategy = InheritanceType.JOINED)
//@NamedQuery(name = "findUserByName", query = "select u from User u" +
////                                    " join u.company c" +
//        " where u.personalInfo.firstName = :firstName and u.company.name = :companyName" +
//        " order by u.personalInfo.lastname desc")
//public class User implements BaseEntity<Long> {
//
//
////    @Id
////    @GeneratedValue(generator = "user_gen", strategy = GenerationType.TABLE)
////    @TableGenerator(name = "user_gen",
////            table = "all_seq",
////            allocationSize = 1,
////            pkColumnName = "table_name",
////            valueColumnName = "pk_value"
////    )
////    @GeneratedValue(generator = "user_gen", strategy = GenerationType.SEQUENCE)
//////    @SequenceGenerator(name = "user_gen", sequenceName = "users_id_seq", allocationSize = 1)
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    //    @EmbeddedId
////    @Embedded
////    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
//    private PersonalInfo personalInfo;
//
//    @Column(unique = true)
//    private String username;
//
//    //    @Type(type = "jsonb")
//    @Type(type = "jsonBin")
//    private String info;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;
//
//
////    @Transient
////    private String test;
//
//
//    @ManyToOne(fetch = FetchType.LAZY)
////    @ManyToOne(fetch = FetchType.EAGER, cascade = {ALL})
//    @JoinColumn(name = "company_id")
//    private Company company;
//
//    @OneToOne(
//            mappedBy = "user",
//            cascade = ALL,
//            fetch = FetchType.LAZY
////            optional = false
//    )
//    private Profile profile;
//
//
//    @Builder.Default
//    @OneToMany(mappedBy = "receiver")
//    private List<Payment> payments = new ArrayList<>();
//
//
//    @Builder.Default
//    @OneToMany(mappedBy = "user")
//    private List<UserChat> userChats = new ArrayList<>();
//
//    public String fullName() {
//        return getPersonalInfo().getFirstName() + SPACE + getPersonalInfo().getLastname();
//    }
//
//}

