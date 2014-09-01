/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pl.gameChecker.model.hibernateEntities.Company;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.GamesLibraries;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.Library;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MembersCPU;
import pl.gameChecker.model.hibernateEntities.MembersGPU;
import pl.gameChecker.model.hibernateEntities.MembersPC;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerComment;
import pl.gameChecker.model.hibernateEntities.SqfaQuestion;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionComment;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModelAddMethods {
    
    private Session session;
    private Transaction transaction;

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
                
        session.save(newMember);
        session.save(library);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewGame(String name, boolean isSingleplayer, boolean isMultiplayer, boolean isFreeToPlay, int releaseDay, int releaseMonth, int releaseYear, String gametypeName) {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
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
    
    public void addGameToMembersLibrary(Member member, Game game) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Library library = (Library) session.get(Library.class, member.getLibrary().getId());
        GamesLibraries gamesLibraries = new GamesLibraries(game, library, new Date(secondsSinceEpoch));
        
        session.save(gamesLibraries);
        
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
    
    public void addNewMembersCPU(String name, int releaseDay, int releaseMonth, int releaseYear, Company company) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersCPU membersCPU = new MembersCPU(name, new Date(secondsSinceEpoch), company);
        
        session.save(membersCPU);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewMembersGPU(String name, int releaseDay, int releaseMonth, int releaseYear, int memory, Company company) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersGPU membersGPU = new MembersGPU(name, new Date(secondsSinceEpoch), memory, company);
        
        session.save(membersGPU);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewCompany(String name) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Company company = new Company(name);
        
        session.save(company);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewSqfaQuestion(Member member, String title, String questionContent) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        SqfaQuestion sqfaQuestion = new SqfaQuestion(member, title, questionContent, new Timestamp(secondsSinceEpoch));
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.save(sqfaQuestion);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewSqfaAnswer(Member member, SqfaQuestion sqfaQuestion, String answerContent) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        SqfaAnswer sqfaAnswer = new SqfaAnswer(member, sqfaQuestion, answerContent, new Timestamp(secondsSinceEpoch));
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.save(sqfaAnswer);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewSqfaAnswerComment(Member member, SqfaAnswer sqfaAnswer, String commentContent) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        SqfaAnswerComment sqfaAnswerComment = new SqfaAnswerComment(member, sqfaAnswer, commentContent, new Timestamp(secondsSinceEpoch));
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.save(sqfaAnswerComment);
        
        transaction.commit();
        session.close();
    }
    
    public void addNewSqfaQuestionComment(Member member, SqfaQuestion sqfaQuestion, String commentContent) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        SqfaQuestionComment sqfaQuestionComment = new SqfaQuestionComment(member, sqfaQuestion, commentContent, new Timestamp(secondsSinceEpoch));
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        session.save(sqfaQuestionComment);
        
        transaction.commit();
        session.close();
    }
}
