package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Customer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDao extends CommonDao<Customer, Integer> {


    public Customer read(Integer key) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Customer customer = null;


        try {
            transaction = session.getTransaction();

            transaction.begin();

            customer = session.get(Customer.class, key);

            if (customer == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return customer;
    }

    @Override
    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Customer> customers = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Customer> namedQuery = session.createNamedQuery("Customer.getAll", Customer.class);

            transaction.commit();

            customers = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return customers;
    }
}
