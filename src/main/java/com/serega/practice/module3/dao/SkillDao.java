package com.serega.practice.module3.dao;

import com.serega.practice.module3.entity.Skill;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SkillDao extends CommonDao<Skill, Integer> {

    public Skill read(Integer key) {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Skill skill = session.get(Skill.class, key);

            if (skill == null) return null;

            return skill;
        }
    }

    public List<Skill> getAll() {

        try (Session session = HibernateUtil.getSession()) {

            Transaction transaction = session.getTransaction();

            transaction.begin();

            Query<Skill> namedQuery = session.createNamedQuery("Skill.getAll", Skill.class);

            transaction.commit();

            return namedQuery.getResultList();
        }

    }
}
