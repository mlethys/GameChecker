/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.dbModel;

import com.bbZoftware.hibernateEntities.Company;
import com.bbZoftware.hibernateEntities.Game;
import com.bbZoftware.hibernateEntities.Gametype;
import com.bbZoftware.hibernateEntities.Member;
import com.bbZoftware.hibernateEntities.MembersCPU;
import com.bbZoftware.hibernateEntities.MembersGPU;
import com.bbZoftware.hibernateEntities.MembersPC;
import com.bbZoftware.hibernateEntities.Role;
import com.bbZoftware.hibernateEntities.SqfaAnswer;
import com.bbZoftware.hibernateEntities.SqfaComment;
import com.bbZoftware.hibernateEntities.SqfaQuestion;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModelGetMethods {

    private Session session;
    private Transaction transaction;
    
    public Member getMemberById(int memberId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Member member = (Member) session.get(Member.class, memberId);
        
        transaction.commit();
        session.close();
        
        return member;
    }
    
    public Game getGameById(int gameId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Game game = (Game) session.get(Game.class, gameId);
        
        transaction.commit();
        session.close();
        
        return game;
    }
    
    public SqfaAnswer getSqfaAnswerById(int sqfaAnswerId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        SqfaAnswer sqfaAnswer = (SqfaAnswer) session.get(SqfaAnswer.class, sqfaAnswerId);
        
        transaction.commit();
        session.close();
        
        return sqfaAnswer;
    }
    
    public SqfaQuestion getSqfaQuestionById(int sqfaQuestionId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        SqfaQuestion sqfaQuestion = (SqfaQuestion) session.get(SqfaQuestion.class, sqfaQuestionId);
        
        transaction.commit();
        session.close();
        
        return sqfaQuestion;
    }
    
    public SqfaComment getSqfaCommentById(int sqfaCommentId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        SqfaComment sqfaComment = (SqfaComment) session.get(SqfaComment.class, sqfaCommentId);
        
        transaction.commit();
        session.close();
        
        return sqfaComment;
    }
    
    public MembersPC getMembersPCById(int membersPCId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersPC membersPC = (MembersPC) session.get(MembersPC.class, membersPCId);
        
        transaction.commit();
        session.close();
        
        return membersPC;
    }
    
    public MembersCPU getMembersCPUById(int membersPCUId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersCPU membersCPU = (MembersCPU) session.get(MembersCPU.class, membersPCUId);
        
        transaction.commit();
        session.close();
        
        return membersCPU;
    }
    
    public MembersGPU getMembersGPUById(int membersPCUId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        MembersGPU membersGPU = (MembersGPU) session.get(MembersGPU.class, membersPCUId);
        
        transaction.commit();
        session.close();
        
        return membersGPU;
    }
    
    public Role getRoleById(int roleId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Role role = (Role) session.get(Role.class, roleId);
        
        transaction.commit();
        session.close();
        
        return role;
    }
    
    public Company getCompanyById(int companyId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Company company = (Company) session.get(Company.class, companyId);
        
        transaction.commit();
        session.close();
        
        return company;
    }
    
    public Gametype getGametypeById(int gametypeId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Gametype gametype = (Gametype) session.get(Gametype.class, gametypeId);
        
        transaction.commit();
        session.close();
        
        return gametype;
    }
}
