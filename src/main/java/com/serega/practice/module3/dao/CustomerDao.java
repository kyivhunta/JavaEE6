package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Customer;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao extends CommonDao<Customer, Integer> {


    public Customer read(Integer key) {

        try (Session session = HibernateUtil.getSession();) {


            Transaction transaction = session.getTransaction();

            transaction.begin();

            Customer customer = session.get(Customer.class, key);

            if (customer == null) return null;

            return customer;
        }
    }

    @Override
    public List<Customer> getAll() {

        try (Session session = HibernateUtil.getSession();) {


            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Customer> namedQuery = session.createNamedQuery("Customer.getAll", Customer.class);

            transaction.commit();

            return namedQuery.getResultList();
        }

    }
}
