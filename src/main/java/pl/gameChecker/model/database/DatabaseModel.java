/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.gameChecker.model.hibernateEntities.Library;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MembersRoles;
import pl.gameChecker.model.hibernateEntities.Role;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModel {

    Session session;
    Transaction transaction;
    
    public void addNewMember(String name, String password, String mail, int birthDay, int birthMonth, int birthYear) {
        
        password = DigestUtils.sha256Hex(password);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(birthYear, birthMonth - 1, birthDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        Member newMember = new Member(name, password, new Date(new java.util.Date().getTime()), mail, new Date(secondsSinceEpoch));
        Library library = new Library();
        newMember.setLibrary(library);
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersRoles membersRoles = new MembersRoles(newMember, (Role) session.get(Role.class, 1));
        
        session.save(membersRoles);
        session.save(library);
        
        transaction.commit();
        session.close();
    }
}
