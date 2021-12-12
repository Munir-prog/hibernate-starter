package com.mprog.runner;

import com.mprog.entity.*;
import com.mprog.util.HibernateUtil;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner3 {

    public static void main(String[] args) throws SQLException {

        var google = Company.builder()
                .name("Google")
                .build();

        var user = User.builder()
                .username("ivan12@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstName("Vanya")
                        .lastname("Ivanov")
                        .birthday(new Birthday(LocalDate.of(2001, 11, 6)))
                        .build())
                .company(google)
                .info("""
                            {
                                "name": "Ivan",
                                "id": 25
                            }
                            """)
                .role(Role.ADMIN)
                .build();

        try (var sessionFactory = HibernateUtil.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

//            session.save(google);
//            session.save(user);

            var user1 = session.get(User.class, 1L);

            session.getTransaction().commit();
        }
    }
}

