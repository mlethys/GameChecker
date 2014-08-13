/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.databasepackage;

import java.sql.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class TempDBMain {
    
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure();
        ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
        srBuilder.applySettings(config.getProperties());
        ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
        SessionFactory factory = config.buildSessionFactory(serviceRegistry);
        
        Session session = factory.openSession();
        
        Transaction transaction = session.beginTransaction();
        
        Member me = new Member("bbZ", new Date(new java.util.Date().getTime()), "gamemajster@gmail.com", 22, 100);
        Role myRole3 = new Role("Administrator");
        Role myRole2 = new Role("Junior Admin");
        Role myRole1 = new Role("Moderator");
        Role myRole0 = new Role("User");
        MembersRoles membersRole = new MembersRoles(me, myRole3);
        
        session.save(myRole0);
        session.save(myRole1);
        session.save(myRole2);
        //session.save(myRole3);
        session.save(membersRole);

        transaction.commit();
        
        session.close();
    }

}
