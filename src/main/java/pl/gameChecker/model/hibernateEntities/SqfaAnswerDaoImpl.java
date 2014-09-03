/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.sql.Date;
import java.util.List;
import org.hibernate.Query;
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
public class SqfaAnswerDaoImpl extends HibernateDaoSupport implements SqfaAnswerDao{

    public SqfaAnswerDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<SqfaAnswer> getList() {
        List<SqfaAnswer> sqfaAnswers = getHibernateTemplate().loadAll(SqfaAnswer.class);
        return sqfaAnswers;
    }

    @Override
    @Transactional
    public SqfaAnswer getById(int id) {
        SqfaAnswer sqfaAnswer = (SqfaAnswer) getHibernateTemplate().get(SqfaAnswer.class, id);
        return sqfaAnswer;
    }

    @Override
    @Transactional
    public void create(SqfaAnswer sqfaAnswer) {
        getHibernateTemplate().save(sqfaAnswer);
    }

    @Override
    @Transactional
    public void update(SqfaAnswer sqfaAnswer) {
        getHibernateTemplate().update(sqfaAnswer);
    }

    @Override
    @Transactional
    public void delete(SqfaAnswer sqfaAnswer) {
        getHibernateTemplate().delete(sqfaAnswer);
    }

    @Override
    @Transactional
    public List<SqfaAnswer> getAllAnswersFromMember(Member member) {
        List<SqfaAnswer> sqfaAnswers = (List<SqfaAnswer>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(SqfaAnswer.class)
        .add(Restrictions.eq("member", member)));

        return sqfaAnswers;
    }

    @Override
    @Transactional
    public List<Object[]> getSqfaSummaryBetween(Date dateFrom, Date dateTo) {
        List<Object[]> objects;
        
        Query query = getSessionFactory().getCurrentSession().createQuery("select sum(sqfaa.points), m.name from SQFA_ANSWERS sqfaa, MEMBERS m "
                + "where sqfaa.member=m.id and sqfaa.additionDate between '" + dateFrom + " 00:00:01' AND '" + dateTo + " 23:59:59' "
                + "group by m.name having sum(sqfaa.points)>0 order by sqfaa.points");

        objects = query.list();
        
        
        if(objects != null) {
            for (int i = 0; i < objects.size(); i++) {
                Object[] temp = objects.get(i);
                Object[] obj = new Object[3];
                obj[0] = temp[0];
                obj[1] = temp[1];
                int sqfaAnswersCount = ((Long)getSessionFactory().getCurrentSession().createQuery("select count(*) from SQFA_ANSWERS sqfaa, MEMBERS m "
                    + "where sqfaa.member=m.id and sqfaa.additionDate between '" + dateFrom + " 00:00:01' AND '" + dateTo + " 23:59:59' "
                    + "and m.name='" + obj[1].toString() + "'").uniqueResult()).intValue();
                obj[2] = sqfaAnswersCount;
                objects.set(i, obj);
            }
        }
                
        return objects;
    }

    @Override
    @Transactional
    public void incrementSqfaAnswerPoints(SqfaAnswer sqfaAnswer) {
        sqfaAnswer.setPoints(sqfaAnswer.getPoints() + 1);
        sqfaAnswer.getMember().setPoints(sqfaAnswer.getMember().getPoints() + 1);
        
        getHibernateTemplate().update(sqfaAnswer.getMember());
        getHibernateTemplate().update(sqfaAnswer);
    }
}
