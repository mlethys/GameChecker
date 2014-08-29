/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.dbModel;

import java.sql.Date;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.gameChecker.model.database.HibernateUtil;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.Member;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModelUpdateMethods {

    private Session session;
    private Transaction transaction;

    
    public void updateMemberProfile(Member member, String name, String password, String mail, int birthDay, int birthMonth, int birthYear) {
        String pass = DigestUtils.sha256Hex(password);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(birthYear, birthMonth - 1, birthDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        if (member != null) {
            member.setPassword(pass);
            member.setMail(mail);
            member.setBirthDate(new Date(secondsSinceEpoch));
            session.update(member);
        }
        
        transaction.commit();
        session.close();
    }
    
    public void updateGameInfo(Game game, String name, boolean isSingleplayer, boolean isMultiplayer, boolean isFreeToPlay, int releaseDay, int releaseMonth, int releaseYear, String gametypeName) {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Gametype.class);
        criteria.add(Restrictions.eq("name", gametypeName));
        Gametype gametype = (Gametype) criteria.uniqueResult();

        if (game != null) {
            game.setName(name);
            game.setGametype(gametype);
            game.setSingleplayer(isSingleplayer);
            game.setMultiplayer(isMultiplayer);
            game.setFreeToPlay(isFreeToPlay);
            game.setReleaseDate(new Date(secondsSinceEpoch));
            session.update(game);
        }
        
        transaction.commit();
        session.close();
    }  
}
