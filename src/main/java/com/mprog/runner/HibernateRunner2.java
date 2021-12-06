package com.mprog.runner;

import com.mprog.converter.BirthdayConverter;
import com.mprog.entity.User;
import com.mprog.util.HibernateUtil;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class HibernateRunner2 {

//    private static final Logger log = LoggerFactory.getLogger(HibernateRunner2.class);
    public static void main(String[] args) {

        var user = User.builder()
                .username("ivan@gmail.com")
                .firstName("Ivan")
                .lastname("Ivanov")
                .build();

        log.info("User entity is in transient state, object: {}", user);
        try (var sessionFactory = HibernateUtil.buildSessionFactory()){
            var session1 = sessionFactory.openSession();
            try (session1) {
                var transaction = session1.beginTransaction();
                log.trace("Transaction  is begun, {}", transaction);

                session1.saveOrUpdate(user);
                log.trace("User is in persistent state: {}, session {}", user, session1);
                session1.getTransaction().commit();
            }

            log.warn("User is in detached state: {}, session: {}", user, session1);
//            try (var session2 = sessionFactory.openSession()) {
//                session2.beginTransaction();
//
//                user.setLastname("Petrov");
//                //                session2.delete(user);
//                session2.refresh(user);
//                var merge = session2.merge(user);
//                session2.getTransaction().commit();
//            }
        } catch (Exception exception) {
            log.error("Exception occurred", exception);
            throw exception;
        }
    }
}
