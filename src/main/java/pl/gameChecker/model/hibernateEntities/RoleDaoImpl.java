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
public class RoleDaoImpl extends HibernateDaoSupport implements RoleDao{

    public RoleDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<Role> getList() {
        List<Role> roles = getHibernateTemplate().loadAll(Role.class);
        return roles;
    }

    @Override
    @Transactional
    public Role getById(int id) {
        Role role = (Role) getHibernateTemplate().get(Role.class, id);
        return role;
    }

    @Override
    @Transactional
    public void create(Role role) {
        getHibernateTemplate().save(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        getHibernateTemplate().update(role);
    }

    @Override
    @Transactional
    public void delete(Role role) {
        getHibernateTemplate().delete(role);
    }

    @Override
    @Transactional
    public Role getByName(String name) {
        List<Role> roles = (List<Role>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Role.class)
        .add(Restrictions.eq("name", name)));

        if(roles.size() > 0)
        {
            return roles.get(0);
        }
        else return null;
    }
}
