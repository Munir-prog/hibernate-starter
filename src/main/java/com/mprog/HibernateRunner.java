package com.mprog;

import com.mprog.entity.Role;
import com.mprog.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.concurrent.BlockingQueue;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        BlockingQueue<Connection> pool = null;
//        Connection connection = pool.take();
//        Connection connection = DriverManager.getConnection(
//                "jdbc:postgresql://localhost:5432/hib_learn",
//                "db.username",
//                "postgres"
//        );

        var configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            var user = User.builder()
                    .username("ivan@gmail.com")
                    .firstName("Ivan")
                    .lastname("Ivanov")
                    .birthday(LocalDate.of(2001, 11, 6))
                    .age(20)
                    .role(Role.ADMIN )
                    .build();

            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

            System.out.println("ok");

        }


    }
}

