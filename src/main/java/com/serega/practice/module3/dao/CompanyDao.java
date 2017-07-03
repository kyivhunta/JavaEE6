package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDao extends CommonDao<Company, Integer> {

    public Company read(Integer key) {
        Company company = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            company = session.get(Company.class, key);

            if (company == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return company;
    }

    public List<Company> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Company> companies = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Company> namedQuery = session.createNamedQuery("Company.getAll", Company.class);

            transaction.commit();

            companies = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return companies;
    }
}
