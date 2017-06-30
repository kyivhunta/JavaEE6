package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Company;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CompanyDao extends CommonDao<Company, Integer> {

    public Company read(Integer key) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.getTransaction();

            transaction.begin();

            Company company = session.get(Company.class, key);

            if (company == null) return null;

            return company;
        }
    }

    public List<Company> getAll() {

        try (Session session = HibernateUtil.getSession()) {


            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Company> namedQuery = session.createNamedQuery("Company.getAll", Company.class);

            transaction.commit();

            return namedQuery.getResultList();

        }
    }
}
