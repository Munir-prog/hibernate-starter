package com.mprog.runner;

import com.mprog.converter.BirthdayConverter;
import com.mprog.entity.User;
import com.mprog.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

public class HibernateRunner2 {

    public static void main(String[] args) {

        var user = User.builder()
                .username("ivan@gmail.com")
                .firstName("Ivan")
                .lastname("Ivanov")
                .build();

        try (var sessionFactory = HibernateUtil.buildSessionFactory()){
            try (var session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                session1.saveOrUpdate(user);

                session1.getTransaction().commit();
            }

            try (var session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setLastname("Petrov");
                //                session2.delete(user);
//                session2.refresh(user);
                var merge = session2.merge(user);
                session2.getTransaction().commit();
            }
        }
    }
}
