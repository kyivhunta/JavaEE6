package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Developer;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DeveloperDao extends CommonDao<Developer, Integer> {

    public Developer read(Integer key) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Developer developer = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            developer = session.get(Developer.class, key);

            if (developer == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return developer;
    }

    public List<Developer> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Developer> developers = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Developer> namedQuery = session.createNamedQuery("Developer.getAll", Developer.class);

            transaction.commit();

            developers = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return developers;

    }
}
