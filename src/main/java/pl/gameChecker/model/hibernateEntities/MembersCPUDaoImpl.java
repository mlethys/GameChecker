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
public class MembersCPUDaoImpl extends HibernateDaoSupport implements MembersCPUDao{

    public MembersCPUDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<MembersCPU> getList() {
        List<MembersCPU> membersCPUs = getHibernateTemplate().loadAll(MembersCPU.class);
        return membersCPUs;
    }

    @Override
    @Transactional
    public MembersCPU getById(int id) {
        MembersCPU membersCPU = (MembersCPU) getHibernateTemplate().get(MembersCPU.class, id);
        return membersCPU;
    }

    @Override
    @Transactional
    public List<MembersCPU> getWhereNameLike(String name) {
        List<MembersCPU> membersCPUs = (List<MembersCPU>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersCPU.class)
        .add(Restrictions.like("name", name, MatchMode.ANYWHERE)));

        return membersCPUs;
    }

    @Override
    @Transactional
    public void create(MembersCPU membersCPU) {
        getHibernateTemplate().save(membersCPU);
    }

    @Override
    @Transactional
    public void update(MembersCPU membersCPU) {
        getHibernateTemplate().update(membersCPU);
    }

    @Override
    @Transactional
    public void delete(MembersCPU membersCPU) {
        getHibernateTemplate().delete(membersCPU);
    }

    @Override
    @Transactional
    public List<MembersCPU> getAllCPUsOfCompany(Company company) {
        List<MembersCPU> membersCPUs = (List<MembersCPU>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersCPU.class)
        .add(Restrictions.eq("company", company)));

        return membersCPUs;
    }
}
