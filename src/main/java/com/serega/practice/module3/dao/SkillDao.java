package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Skill;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SkillDao extends CommonDao<Skill, Integer> {

    public Skill read(Integer key) {


        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Skill skill = null;

        try {

            transaction = session.getTransaction();

            transaction.begin();

            skill = session.get(Skill.class, key);

            if (skill == null) return null;


        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return skill;
    }

    public List<Skill> getAll() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Skill> skills = null;

        try {
            transaction = session.getTransaction();

            transaction.begin();

            Query<Skill> namedQuery = session.createNamedQuery("Skill.getAll", Skill.class);

            transaction.commit();

            skills = namedQuery.getResultList();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return skills;
    }
}
