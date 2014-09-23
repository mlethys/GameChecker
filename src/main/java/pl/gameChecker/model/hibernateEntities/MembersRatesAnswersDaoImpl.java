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
public class MembersRatesAnswersDaoImpl extends HibernateDaoSupport implements MembersRatesAnswersDao {
    
    public MembersRatesAnswersDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<MembersRatesAnswers> getList() {
        List<MembersRatesAnswers> membersRatesAnswerses = getHibernateTemplate().loadAll(MembersRatesAnswers.class);
        return membersRatesAnswerses;
    }

    @Override
    @Transactional
    public MembersRatesAnswers getById(int id) {
        MembersRatesAnswers membersRatesAnswers = (MembersRatesAnswers) getHibernateTemplate().get(MembersRatesAnswers.class, id);
        return membersRatesAnswers;
    }

    @Override
    @Transactional
    public List<MembersRatesAnswers> getMembersRatesGamesByAnswer(SqfaAnswer sqfaAnswer) {
        List<MembersRatesAnswers> membersRatesAnswers = (List<MembersRatesAnswers>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesAnswers.class)
        .add(Restrictions.eq("sqfaAnswer", sqfaAnswer)));
        
    return membersRatesAnswers;
    }

    @Override
    @Transactional
    public List<MembersRatesAnswers> getMembersRatesGamesByMember(Member member) {
        List<MembersRatesAnswers> membersRatesAnswers = (List<MembersRatesAnswers>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesAnswers.class)
        .add(Restrictions.eq("member", member)));

        return membersRatesAnswers;
    }

    @Override
    @Transactional
    public void create(MembersRatesAnswers membersRatesAnswers) {
        getHibernateTemplate().save(membersRatesAnswers);
    }

    @Override
    @Transactional
    public void update(MembersRatesAnswers membersRatesAnswers) {
        getHibernateTemplate().update(membersRatesAnswers);
    }

    @Override
    @Transactional
    public void delete(MembersRatesAnswers membersRatesAnswers) {
        getHibernateTemplate().delete(membersRatesAnswers);
    }

    @Override
    @Transactional
    public boolean isMemberRatedAnswer(Member member, SqfaAnswer sqfaAnswer) {
        List<MembersRatesAnswers> membersRatesAnswers = (List<MembersRatesAnswers>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesAnswers.class)
        .add(Restrictions.and(Restrictions.eq("member", member), Restrictions.eq("sqfaAnswer", sqfaAnswer))));
        
        return membersRatesAnswers.size() > 0;
    }

}
