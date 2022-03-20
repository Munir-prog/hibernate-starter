package com.mprog.runner;


import com.mprog.entity.Company;
import com.mprog.entity.Payment;
import com.mprog.entity.User;
import com.mprog.entity.UserChat;
import com.mprog.util.HibernateUtil;
import com.mprog.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;
import org.hibernate.jpa.QueryHints;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public class HibernateRunner4 {

    @Transactional
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession();
             Session session1 = sessionFactory.openSession()) {
            TestDataImporter.importData(sessionFactory);
            session.beginTransaction();
            session1.beginTransaction();

            session.createQuery("select u from Payment u", Payment.class)
                    .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
                    .setHint("javax.persistence.lock.timeout", 5000)
                    .list();

            Payment payment = session.find(Payment.class, 1L, LockModeType.PESSIMISTIC_READ);
            payment.setAmount(payment.getAmount() + 10);

            Payment theSamePayment = session1.find(Payment.class, 1L);
            theSamePayment.setAmount(payment.getAmount() + 20);

            session1.getTransaction().commit();
            session.getTransaction().commit();
        }
    }

    private static void beforeTransactionLessonsCode() {
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            RootGraph<User> entityGraph = session.createEntityGraph(User.class);
            entityGraph.addAttributeNodes("company", "userChats");
            SubGraph<UserChat> userChats = entityGraph.addSubGraph("userChats", UserChat.class);
            userChats.addAttributeNode("chat");
//            session.enableFetchProfile("withCompanyAndPayments");
            RootGraph<?> graph = session.getEntityGraph("withCompanyAndChat");


            Map<String, Object> properties = Map.of(
                    GraphSemantic.LOAD.getJpaHintName(), graph
            );
            User user = session.find(User.class, 1L, properties);

            System.out.println(user.getCompany().getName());
            System.out.println(user.getUserChats().size());


            List<User> users = session.createQuery("select u from User u " +
                            "join fetch u.payments where 1 = 1", User.class)
//                    .setHint(GraphSemantic.LOAD.getJpaHintName(), properties)
//                    .setHint(GraphSemantic.LOAD.getJpaHintName(), session.getEntityGraph("withCompanyAndChat"))
                    .setHint(GraphSemantic.LOAD.getJpaHintName(), entityGraph)
                    .list();
            users.forEach(it -> System.out.println(it.getUserChats().size()));
//            users.forEach(user -> System.out.println(user.getUserChats()));
            users.forEach(it -> System.out.println(it.getCompany().getName()));

            session.getTransaction().commit();
        }
    }

}

