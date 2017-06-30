package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Project;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectDao extends CommonDao<Project, Integer> {

    public Project read(Integer key) {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Project project = session.get(Project.class, key);

            if (project == null) return null;

            return project;
        }
    }

    public List<Project> getAll() {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Project> namedQuery = session.createNamedQuery("Project.getAll", Project.class);

            transaction.commit();

            return namedQuery.getResultList();
        }

    }
}
