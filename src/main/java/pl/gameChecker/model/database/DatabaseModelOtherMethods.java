/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModelOtherMethods {

    private Session session;
    private Transaction transaction;
    
    public void incrementSqfaAnswerPoints(SqfaAnswer sqfaAnswer) {
        sqfaAnswer.setPoints(sqfaAnswer.getPoints() + 1);
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.update(sqfaAnswer);
        
        transaction.commit();
        session.close();
    }
    
    public boolean isLoginMatchPassword(String login, String password) {
        String pass = DigestUtils.sha256Hex(password);
        
        System.out.println(login + " " + pass);
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria criteria = session.createCriteria(Member.class)
        .add(Restrictions.and(Restrictions.eq("password", pass), Restrictions.eq("name", login)));
        
        try {
            Member member = (Member) criteria.uniqueResult();
            transaction.commit();
            session.close();
            if (member != null){
                System.out.println("Zalogowano.");
                return true;
            }
            else { 
                System.out.println("Niezalogowano.");
                return false;
            }
                
        } catch(HibernateException ex) {
            transaction.commit();
            session.close();
            System.err.println(ex);
            return false;
        }
    }

    public void rateGame(Game game, int rating) {
        double stars = game.getStars();
        int rates = game.getRates();
        int newRates = rates + 1;
        game.setRates(newRates);
        
        double average = (stars * rates + rating) / newRates;
        game.setStars(average);
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.update(game);
        
        transaction.commit();
        session.close();
    }
}