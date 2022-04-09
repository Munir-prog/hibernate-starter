package com.mprog.dao;

import com.mprog.entity.Payment;
import org.hibernate.SessionFactory;

public class PaymentRepository extends BaseRepository<Long, Payment> {

    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
