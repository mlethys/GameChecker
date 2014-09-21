/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.util.List;
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
public class SqfaQuestionCommentDaoImpl extends HibernateDaoSupport implements SqfaQuestionCommentDao{

    public SqfaQuestionCommentDaoImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<SqfaQuestionComment> getList() {
        List<SqfaQuestionComment> sqfaQuestionComments = getHibernateTemplate().loadAll(SqfaQuestionComment.class);
        return sqfaQuestionComments;
    }

    @Override
    @Transactional
    public SqfaQuestionComment getById(int id) {
        SqfaQuestionComment sqfaQuestionComment = (SqfaQuestionComment) getHibernateTemplate().get(SqfaQuestionComment.class, id);
        return sqfaQuestionComment;
    }

    @Override
    @Transactional
    public void create(SqfaQuestionComment sqfaQuestionComment) {
        getHibernateTemplate().save(sqfaQuestionComment);
    }

    @Override
    @Transactional
    public void update(SqfaQuestionComment sqfaQuestionComment) {
        getHibernateTemplate().update(sqfaQuestionComment);
    }

    @Override
    @Transactional
    public void delete(SqfaQuestionComment sqfaQuestionComment) {
        getHibernateTemplate().delete(sqfaQuestionComment);
    }

    @Override
    @Transactional
    public List<SqfaQuestionComment> getAllQuestionCommentsFromMember(Member member) {
        List<SqfaQuestionComment> sqfaQuestionComments = (List<SqfaQuestionComment>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestionComment.class)
        .add(Restrictions.eq("member", member)));

        return sqfaQuestionComments;
    }

    @Override
    @Transactional
    public List<SqfaQuestionComment> getCommentsFromQuestion(SqfaQuestion sqfaQuestion) {
        List<SqfaQuestionComment> sqfaQuestionComments = (List<SqfaQuestionComment>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaQuestionComment.class)
        .add(Restrictions.eq("sqfaQuestion", sqfaQuestion)));

        return sqfaQuestionComments;
    }
    
}
