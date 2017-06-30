package com.serega.practice.module3.sessionfactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.io.File;

public class HibernateUtil {


    private static final SessionFactory ourSessionFactory;

    static {
        try {
            File file = new File("D:\\GoJava#6\\JavaEE\\JavaEE6\\src\\main\\resources\\META-INF\\hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(file);

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void shutDown(){

        ourSessionFactory.close();
    }

}
