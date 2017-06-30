package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Developer;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DeveloperDao extends CommonDao<Developer, Integer> {

    public Developer read(Integer key) {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Developer developer = session.get(Developer.class, key);

            if (developer == null) return null;

            return developer;
        }
    }

    public List<Developer> getAll() {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Developer> namedQuery = session.createNamedQuery("Developer.getAll", Developer.class);

            transaction.commit();

            return namedQuery.getResultList();
        }

    }
}
