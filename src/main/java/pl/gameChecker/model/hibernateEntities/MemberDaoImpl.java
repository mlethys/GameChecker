/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class MemberDaoImpl extends HibernateDaoSupport implements MemberDao {

    public MemberDaoImpl (SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    @Override
    @Transactional
    public List<Member> getList() {
        List<Member> members = getHibernateTemplate().loadAll(Member.class);
        return members;
    }

    @Override
    @Transactional
    public Member getById(int id) {
        Member member = (Member) getHibernateTemplate().get(Member.class, id);
        return member;
    }

    @Override
    @Transactional
    public Member getByName(String name) {
        List<Member> members = (List<Member>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Member.class)
        .add(Restrictions.eq("name", name)));

        if(members.size() > 0)
        {
            return members.get(0);
        }
        else return null;
    }
    
    @Override
    @Transactional
    public boolean exists(Member member) {
        List<Member> members = (List<Member>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Member.class)
        .add(Restrictions.or(Restrictions.eq("name", member.getName()), Restrictions.eq("mail", member.getMail()))));
        
        if(!members.isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<Member> getMembersWhereRegisterDateBetween(Date dateFrom, Date dateTo) {
        List<Member> members = (List<Member>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Member.class)
        .add(Restrictions.between("registerDate", dateFrom, dateTo)));

        for(Member m : members) {
            System.out.println("tabela members: " +m.getName());
        }
        
        return members;
    }

    @Override
    @Transactional
    public void create(Member member) {
        Library library = new Library();
        member.setLibrary(library);
        
        getHibernateTemplate().save(library);
        getHibernateTemplate().save(member);
    }

    @Override
    @Transactional
    public void update(Member member) {
        getHibernateTemplate().update(member);
    }

    @Override
    @Transactional
    public void delete(Member member, List<SqfaAnswer> sqfaAnswersFromMember, List<SqfaQuestion> sqfaQuestionsFromMember,
        List<SqfaQuestionComment> sqfaQuestionCommentsFromMember, List<SqfaAnswerComment> sqfaAnswerCommentsFromMember) {
        
        for(SqfaAnswer answer : sqfaAnswersFromMember) {
            answer.setMember(null);
            getHibernateTemplate().update(answer);
        }
        
        for(SqfaQuestion question : sqfaQuestionsFromMember) {
            question.setMember(null);
            getHibernateTemplate().update(question);
        }
        
        for(SqfaQuestionComment questionComment : sqfaQuestionCommentsFromMember) {
            questionComment.setMember(null);
            getHibernateTemplate().update(questionComment);
        }
        
        for(SqfaAnswerComment answerComment : sqfaAnswerCommentsFromMember) {
            answerComment.setMember(null);
            getHibernateTemplate().update(answerComment);
        }
        
        getHibernateTemplate().delete(member);
        getHibernateTemplate().delete(member.getLibrary());
    }

    @Override
    @Transactional
    public boolean isMemberLoginMatchPassword(String login, String password) {
        String pass = DigestUtils.sha256Hex(password);
                        
        
        List<Member> members = (List<Member>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Member.class)
        .add(Restrictions.and(Restrictions.eq("password", pass), Restrictions.eq("name", login))));
        
        if (members.size() >0){
            System.out.println("Zalogowano.");
            return true;
        }
        else { 
            System.out.println("Niezalogowano.");
            return false;
        }
    }

    @Override
    @Transactional
    public void updateMemberProfile(Member member, String name, String password, String mail, int birthDay, int birthMonth, int birthYear) {
        String pass = DigestUtils.sha256Hex(password);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(birthYear, birthMonth - 1, birthDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
                
        if (member != null) {
            member.setPassword(pass);
            member.setMail(mail);
            member.setBirthDate(new Date(secondsSinceEpoch));
            getHibernateTemplate().update(member);
        }
    }

    @Override
    @Transactional
    public void delete(Member member) {
        getHibernateTemplate().delete(member);
    }
    
    @Override
    @Transactional
    public void deleteMemberHelper(Member member){
        List<SqfaAnswer> sqfaAnswers = (List<SqfaAnswer>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaAnswer.class)
        .add(Restrictions.eq("member", member)));
        
        for(SqfaAnswer sa : sqfaAnswers){
            sa.setMember(null);
            getHibernateTemplate().update(sa);
        }
        
        List<SqfaQuestion> sqfaQuestions = (List<SqfaQuestion>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestion.class)
        .add(Restrictions.eq("member", member)));
        
        for(SqfaQuestion sq : sqfaQuestions){
            sq.setMember(null);
            getHibernateTemplate().update(sq);
        }
        
        List<SqfaAnswerComment> sqfaAnswerComments = (List<SqfaAnswerComment>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaAnswerComment.class)
        .add(Restrictions.eq("member", member)));
        
        for(SqfaAnswerComment sac : sqfaAnswerComments){
            sac.setMember(null);
            getHibernateTemplate().update(sac);
        }
        
        List<SqfaQuestionComment> sqfaQuestionComments = (List<SqfaQuestionComment>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestionComment.class)
        .add(Restrictions.eq("member", member)));
        
        for(SqfaQuestionComment sqc : sqfaQuestionComments){
            sqc.setMember(null);
            getHibernateTemplate().update(sqc);
        }
    }
}
