/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucla.lab2corte1;

import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 *
 * @author josera
 */
public class HibernateUtil {
    //private static final SessionFactory sessionFactory;
    
    public static Session getHibernateSession() {
        final SessionFactory sf = new Configuration()
            .configure("hibernate.cfg.xml").buildSessionFactory();

        final Session session = sf.openSession();
        return session;
    }
}
