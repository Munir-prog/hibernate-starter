package com.mprog;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        configuration.configure();
        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            System.out.println("ok");

        }


    }
}

