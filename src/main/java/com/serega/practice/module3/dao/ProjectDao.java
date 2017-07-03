package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Project;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ProjectDao extends CommonDao<Project, Integer> {

    public Project read(Integer key) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Project project = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            project = session.get(Project.class, key);

            if (project == null) return null;

            transaction.commit();


        }catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return project;
    }

    public List<Project> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Project> projects = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Project> namedQuery = session.createNamedQuery("Project.getAll", Project.class);

            transaction.commit();

            projects = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return projects;
    }
}
