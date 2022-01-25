package com.mprog.runner;


import com.mprog.entity.User;
import com.mprog.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner4 {

    public static void main(String[] args) {

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            User user = session.get(User.class, 1L);

            session.getTransaction().commit();
        }
    }

}

