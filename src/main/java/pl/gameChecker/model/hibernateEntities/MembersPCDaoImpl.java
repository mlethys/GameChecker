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
public class MembersPCDaoImpl extends HibernateDaoSupport implements MembersPCDao{

    public MembersPCDaoImpl(SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }
    
    @Override
    @Transactional
    public List<MembersPC> getList() {
        List<MembersPC> membersPCs = getHibernateTemplate().loadAll(MembersPC.class);
        return membersPCs;
    }

    @Override
    @Transactional
    public MembersPC getById(int id) {
        MembersPC membersPC = (MembersPC) getHibernateTemplate().get(MembersPC.class, id);
        return membersPC;
    }

    @Override
    @Transactional
    public List<MembersPC> getWhereNameLike(String name) {
        List<MembersPC> membersPCs = (List<MembersPC>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersPC.class)
        .add(Restrictions.like("name", name, MatchMode.ANYWHERE)));

        return membersPCs;
    }

    @Override
    @Transactional
    public void create(MembersPC membersPC) {
        getHibernateTemplate().save(membersPC);
    }

    @Override
    @Transactional
    public void update(MembersPC membersPC) {
        getHibernateTemplate().update(membersPC);
    }

    @Override
    @Transactional
    public void delete(MembersPC membersPC) {
        getHibernateTemplate().delete(membersPC);
    }

    @Override
    @Transactional
    public List<MembersPC> getAllPCsOfMember(Member member) {
        List<MembersPC> membersPCs = (List<MembersPC>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersPC.class)
        .add(Restrictions.eq("member", member)));

        return membersPCs;
    }
}
