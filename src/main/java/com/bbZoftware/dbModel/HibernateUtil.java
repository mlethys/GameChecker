/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.dbModel;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author bbZoftware
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            Configuration config = new Configuration();
            config.configure();
            ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
            srBuilder.applySettings(config.getProperties());
            ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            
        } catch (HibernateException ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
