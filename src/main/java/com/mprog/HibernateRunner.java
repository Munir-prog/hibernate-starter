package com.mprog;

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


    }
}

