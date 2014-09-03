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
public class SqfaAnswerCommentDaoImpl extends HibernateDaoSupport implements SqfaAnswerCommentDao {

    public SqfaAnswerCommentDaoImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<SqfaAnswerComment> getList() {
        List<SqfaAnswerComment> sqfaAnswerComments = getHibernateTemplate().loadAll(SqfaAnswerComment.class);
        return sqfaAnswerComments;
    }

    @Override
    @Transactional
    public SqfaAnswerComment getById(int id) {
        SqfaAnswerComment sqfaAnswerComment = (SqfaAnswerComment) getHibernateTemplate().get(SqfaAnswerComment.class, id);
        return sqfaAnswerComment;
    }

    @Override
    @Transactional
    public void create(SqfaAnswerComment sqfaAnswerComment) {
        getHibernateTemplate().save(sqfaAnswerComment);
    }

    @Override
    @Transactional
    public void update(SqfaAnswerComment sqfaAnswerComment) {
        getHibernateTemplate().update(sqfaAnswerComment);
    }

    @Override
    @Transactional
    public void delete(SqfaAnswerComment sqfaAnswerComment) {
        getHibernateTemplate().delete(sqfaAnswerComment);
    }

    @Override
    @Transactional
    public List<SqfaAnswerComment> getAllAnswerCommentsFromMember(Member member) {
        List<SqfaAnswerComment> sqfaAnswerComments = (List<SqfaAnswerComment>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaAnswerComment.class)
        .add(Restrictions.eq("member", member)));

        return sqfaAnswerComments;
    }
    
    
}
