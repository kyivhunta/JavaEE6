package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Skill;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;


public abstract class CommonDao<T, K extends Serializable> {


    public abstract T read(K key);

    public abstract List<T> getAll();

    public void create(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        session.save(entity);

        transaction.commit();

        session.close();
    }

    public void update(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        session.merge(entity);

        transaction.commit();

        session.close();

    }

    public void delete(T entity) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        session.delete(entity);

        transaction.commit();

        session.close();

    }


}
