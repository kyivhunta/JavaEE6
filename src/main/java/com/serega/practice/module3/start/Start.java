package com.serega.practice.module3.start;


import com.serega.practice.module3.api.API;
import com.serega.practice.module3.api.Application;
import com.serega.practice.module3.consolehelper.*;
import com.serega.practice.module3.controllers.*;
import com.serega.practice.module3.dao.*;
import com.serega.practice.module3.entity.*;
import com.serega.practice.module3.sessionfactory.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import java.util.stream.Collectors;


public class Start {
    public static void main(String[] args) throws IOException {

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//
//        CommonDao<Project, Integer> projectDao = new ProjectDao();
//        CommonDao<Company, Integer> companyDao = new CompanyDao();
//        CommonDao<Customer, Integer> customerDao = new CustomerDao();
//        CommonDao<Developer, Integer> developerDao = new DeveloperDao();
//        CommonDao<Skill, Integer> skillDao = new SkillDao();
//
//        ProjectController projectController = new ProjectControllerImpl(projectDao);
//        CompanyController companyController = new CompanyControllerIml(companyDao);
//        CustomerController customerController = new CustomerControllerIml(customerDao);
//        DeveloperController developerController = new DeveloperControllerImpl(developerDao);
//        SkillsController skillController = new SkillControllerImpl(skillDao);
//
//        API api = new Application(skillController,
//                developerController,
//                customerController,
//                companyController,
//                projectController);
//
//        Mainconsole mainconsole = new Mainconsole(bufferedReader,
//                new ConsoleDeveloper(bufferedReader, api),
//                new ConsoleSkill(bufferedReader, api),
//                new ConsoleCompany(bufferedReader, api),
//                new ConsoleCustomer(bufferedReader, api),
//                new ConsoleProject(bufferedReader, api));
//
//        mainconsole.chooseOperation();
//
//        bufferedReader.close();
//
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();

        transaction.begin();

        Skill project = session.load(Skill.class, 1001);
        System.out.println(project);

        transaction.commit();




        session.close();
        HibernateUtil.shutDown();

//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit1");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//
//        EntityTransaction transaction = entityManager.getTransaction();
//        transaction.begin();
//
//        Company company = new Company();
//        company.setName("GOVNO");
//
//        entityManager.persist(company);
//
//        transaction.commit();
//        entityManager.close();
//        entityManagerFactory.close();


    }
}
