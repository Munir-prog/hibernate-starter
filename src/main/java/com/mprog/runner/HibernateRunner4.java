package com.mprog.runner;


import com.mprog.entity.User;
import com.mprog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HibernateRunner4 {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

//            User user = session.get(User.class, 1L);

            List<User> users = session.createQuery("select u from User u " +
                    "join fetch u.payments where 1 = 1", User.class).list();
            users.forEach(user -> System.out.println(user.getPayments()));
//            users.forEach(user -> System.out.println(user.getUserChats()));
            users.forEach(user -> System.out.println(user.getCompany().getName()));

            session.getTransaction().commit();
        }
    }

}

