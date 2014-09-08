/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class SqfaQuestionDaoImpl extends HibernateDaoSupport implements SqfaQuestionDao{
    
    public SqfaQuestionDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<SqfaQuestion> getList() {
        List<SqfaQuestion> sqfaQuestions = getHibernateTemplate().loadAll(SqfaQuestion.class);
        return sqfaQuestions;
    }

    @Override
    @Transactional
    public SqfaQuestion getById(int id) {
        SqfaQuestion sqfaQuestion = (SqfaQuestion) getHibernateTemplate().get(SqfaQuestion.class, id);
        return sqfaQuestion;
    }
    
    @Override
    @Transactional
    public List<SqfaQuestion> getWhereTitleLike(String title) {
        List<SqfaQuestion> sqfaQuestions = (List<SqfaQuestion>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestion.class)
        .add(Restrictions.like("title", title, MatchMode.ANYWHERE)));

        return sqfaQuestions;
    }

    @Override
    @Transactional
    public void create(SqfaQuestion sqfaQuestion) {
        getHibernateTemplate().save(sqfaQuestion);
    }

    @Override
    @Transactional
    public void update(SqfaQuestion sqfaQuestion) {
        getHibernateTemplate().update(sqfaQuestion);
    }

    @Override
    @Transactional
    public void delete(SqfaQuestion sqfaQuestion) {
        getHibernateTemplate().delete(sqfaQuestion);
    }

    @Override
    @Transactional
    public List<SqfaQuestion> getAllQuestionsFromMember(Member member) {
        List<SqfaQuestion> sqfaQuestions = (List<SqfaQuestion>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestion.class)
        .add(Restrictions.eq("member", member)));

        return sqfaQuestions;
    }
    
}
