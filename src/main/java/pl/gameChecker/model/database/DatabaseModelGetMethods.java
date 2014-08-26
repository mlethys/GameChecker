/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;

import java.sql.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import pl.gameChecker.model.hibernateEntities.Company;
import pl.gameChecker.model.hibernateEntities.Game;
import pl.gameChecker.model.hibernateEntities.Gametype;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MembersCPU;
import pl.gameChecker.model.hibernateEntities.MembersGPU;
import pl.gameChecker.model.hibernateEntities.MembersPC;
import pl.gameChecker.model.hibernateEntities.Role;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerComment;
import pl.gameChecker.model.hibernateEntities.SqfaQuestion;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionComment;

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
    
    public SqfaAnswerComment getSqfaAnswerCommentById(int sqfaAnswerCommentId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        SqfaAnswerComment sqfaAnswerComment = (SqfaAnswerComment) session.get(SqfaAnswerComment.class, sqfaAnswerCommentId);
        
        transaction.commit();
        session.close();
        
        return sqfaAnswerComment;
    }
    
    public SqfaQuestionComment getSqfaQuestionCommentById(int sqfaQuestionCommentId) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        SqfaQuestionComment sqfaQuestionComment = (SqfaQuestionComment) session.get(SqfaQuestionComment.class, sqfaQuestionCommentId);
        
        transaction.commit();
        session.close();
        
        return sqfaQuestionComment;
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
    
    public List<Game> getGamesWhereNameLike(String likeThisName) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria query = session.createCriteria(Game.class);
        query.add(Restrictions.like("name", likeThisName, MatchMode.START));

        List<Game> games = query.list();
        
        transaction.commit();
        session.close();
        
        return games;
    }
    
    public List<Member> getMembersWhereRegisterDateBetween(Date dateFrom, Date dateTo) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Criteria query = session.createCriteria(Member.class);
        query.add(Restrictions.between("registerDate", dateFrom, dateTo));

        List<Member> members = query.list();
        
        transaction.commit();
        session.close();
        
        return members;
    }
    
    public List<Object[]> getGamesAdditionAfterDate(Date afterThisDate) {
        List<Object[]> objects = null;
        
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Query query = session.createQuery("from GAMES g, MEMBERS m, GAMES_LIBRARIES gl where "
                + "gl.game=g.id and gl.library=m.id and gl.additionDate >= :date order by m.name" );
        query.setParameter("date", afterThisDate);
        
        objects = query.list();
        
        transaction.commit();
        session.close();
        
        return objects;
    }
    
    public List<Object[]> getSqfaSummaryBetween(Date dateFrom, Date dateTo) {
        List<Object[]> objects = null;
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        Query query = session.createQuery("select sum(sqfaa.points), m.name from SQFA_ANSWERS sqfaa, MEMBERS m "
                + "where sqfaa.member=m.id and sqfaa.additionDate between '" + dateFrom + " 00:00:01' AND '" + dateTo + " 23:59:59' "
                + "group by m.name having sum(sqfaa.points)>0 order by sqfaa.points");

        objects = query.list();
        
        
        if(objects != null) {
            for (int i = 0; i < objects.size(); i++) {
                Object[] temp = objects.get(i);
                Object[] obj = new Object[3];
                obj[0] = temp[0];
                obj[1] = temp[1];
                int sqfaAnswersCount = ((Long)session.createQuery("select count(*) from SQFA_ANSWERS sqfaa, MEMBERS m "
                    + "where sqfaa.member=m.id and sqfaa.additionDate between '" + dateFrom + " 00:00:01' AND '" + dateTo + " 23:59:59' "
                    + "and m.name='" + obj[1].toString() + "'").uniqueResult()).intValue();
                obj[2] = sqfaAnswersCount;
                objects.set(i, obj);
            }
        }
        
        transaction.commit();
        session.close();
        
        return objects;
    }
    
    public int getGamePopularityProcent(Game game) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
        int totalMembersCount = ((Long)session.createQuery("select count(*) from MEMBERS").uniqueResult()).intValue();
        int membersWithGameCount = ((Long)session.createQuery("select count(*) from GAMES_LIBRARIES where GAMES_GAME_ID = '" + game.getId() + "'").uniqueResult()).intValue();

        transaction.commit();
        session.close();
        
        int result = 100 * membersWithGameCount / totalMembersCount;
        return result;
    }
}