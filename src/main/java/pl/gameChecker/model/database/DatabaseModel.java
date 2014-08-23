/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.dbModel;

import com.bbZoftware.hibernateEntities.Game;
import com.bbZoftware.hibernateEntities.GamesLibraries;
import com.bbZoftware.hibernateEntities.Gametype;
import com.bbZoftware.hibernateEntities.Library;
import com.bbZoftware.hibernateEntities.Member;
import com.bbZoftware.hibernateEntities.MembersCPU;
import com.bbZoftware.hibernateEntities.MembersGPU;
import com.bbZoftware.hibernateEntities.MembersPC;
import com.bbZoftware.hibernateEntities.MembersRoles;
import com.bbZoftware.hibernateEntities.Role;
import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

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
    
    public void addNewGame(String name, boolean isSingleplayer, boolean isMultiplayer, boolean isFreeToPlay, int releaseDay, int releaseMonth, int releaseYear, String gametypeName) {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseDay, releaseMonth - 1, releaseYear);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Gametype.class);
        criteria.add(Restrictions.eq("name", gametypeName));
        Gametype gametype = (Gametype) criteria.uniqueResult();
        
        Game game = new Game(name, new Date(secondsSinceEpoch), isSingleplayer, isMultiplayer, isFreeToPlay, gametype);
        
        session.save(game);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewMembersPC(Member member, String namePC, int memoryPC, MembersCPU membersCPU, MembersGPU membersGPU) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersPC membersPC = new MembersPC(member, namePC, memoryPC, membersCPU, membersGPU);
        
        session.save(membersPC);
        
        transaction.commit();
        session.close();
    }
    
    public void addGameToMembersLibrary(Member member, Game game) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Library library = (Library) session.get(Library.class, member.getLibrary().getId());
        GamesLibraries gamesLibraries = new GamesLibraries(game, library);
        
        session.save(gamesLibraries);
        
        transaction.commit();
        session.close();
    }
}
