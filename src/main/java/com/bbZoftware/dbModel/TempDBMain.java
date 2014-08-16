/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.dbModel;

import com.bbZoftware.hibernateEntities.Game;
import com.bbZoftware.hibernateEntities.Member;
import com.bbZoftware.hibernateEntities.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class TempDBMain {
    
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction transaction = session.beginTransaction();
        
        Role myRole3 = new Role("Administrator");
        Role myRole2 = new Role("Junior Admin");
        Role myRole1 = new Role("Moderator");
        Role myRole0 = new Role("User");
        //MembersRoles membersRole = new MembersRoles(me, myRole3);
        
        session.save(myRole0);
        session.save(myRole1);
        session.save(myRole2);
        session.save(myRole3);

        transaction.commit();
        session.close();
        
        DatabaseModel dbm = new DatabaseModel();
        dbm.addNewMember("bbZ", "tempPassword", "gamemajster@gmail.com", 22, 11, 1991);
        dbm.addNewGame("Jedi Academy", true, true, false, 5, 4, 2005, "Action");
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Member member = (Member) session.get(Member.class, 1);
        Game game = (Game) session.get(Game.class, 1);

        transaction.commit();
        session.close();
        
        dbm.addGameToMembersLibrary(member, game);
    }

}
