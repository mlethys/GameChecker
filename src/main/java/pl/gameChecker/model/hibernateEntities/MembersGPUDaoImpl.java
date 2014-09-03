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
public class MembersGPUDaoImpl extends HibernateDaoSupport implements MembersGPUDao{
    
    public MembersGPUDaoImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<MembersGPU> getList() {
        List<MembersGPU> membersGPUs = getHibernateTemplate().loadAll(MembersGPU.class);
        return membersGPUs;
    }

    @Override
    @Transactional
    public MembersGPU getById(int id) {
        MembersGPU membersGPU = (MembersGPU) getHibernateTemplate().get(MembersGPU.class, id);
        return membersGPU;
    }

    @Override
    @Transactional
    public List<MembersGPU> getWhereNameLike(String name) {
        List<MembersGPU> membersGPUs = (List<MembersGPU>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersGPU.class)
        .add(Restrictions.like("name", name, MatchMode.ANYWHERE)));

        return membersGPUs;
    }

    @Override
    @Transactional
    public void create(MembersGPU membersGPU) {
        getHibernateTemplate().save(membersGPU);
    }

    @Override
    @Transactional
    public void update(MembersGPU membersGPU) {
        getHibernateTemplate().update(membersGPU);
    }

    @Override
    @Transactional
    public void delete(MembersGPU membersGPU) {
        getHibernateTemplate().delete(membersGPU);
    }

    @Override
    @Transactional
    public List<MembersGPU> getAllCPUsOfCompany(Company company) {
        List<MembersGPU> membersGPUs = (List<MembersGPU>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersGPU.class)
        .add(Restrictions.eq("company", company)));

        return membersGPUs;
    }
    
}
