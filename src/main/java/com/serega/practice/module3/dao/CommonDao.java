package com.serega.practice.module3.dao;

import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;


public abstract class CommonDao<T, K extends Serializable> {

    protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    public abstract T read(K key);

    public abstract List<T> getAll();

    public void create(T entity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.save(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void update(T entity) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.merge(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void delete(T entity) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            session.delete(entity);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }


}
