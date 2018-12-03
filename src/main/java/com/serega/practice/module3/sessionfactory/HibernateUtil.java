package com.serega.practice.module3.sessionfactory;

import org.hibernate.HibernateException;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

import java.io.File;

public class HibernateUtil {


    private static final SessionFactory ourSessionFactory;

    private HibernateUtil() {
    }

    static {
        try {
            File file = new File("/home/shevchenko/Загрузки/java materials/JavaEE6/src/main/resources/META-INF/hibernate.cfg.xml");
            Configuration configuration = new Configuration();
            configuration.configure(file);

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() throws HibernateException {

        return ourSessionFactory;
    }

    public static void shutDown() {

        ourSessionFactory.close();
    }

}
