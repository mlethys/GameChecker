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
public class GametypeDaoImpl extends HibernateDaoSupport implements GametypeDao{
    
    public GametypeDaoImpl (SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<Gametype> getList() {
        List<Gametype> gametypes = getHibernateTemplate().loadAll(Gametype.class);
        return gametypes;
    }

    @Override
    @Transactional
    public Gametype getById(int id) {
        Gametype gametype = (Gametype) getHibernateTemplate().get(Gametype.class, id);
        return gametype;
    }

    @Override
    @Transactional
    public Gametype getByName(String name) {
        List<Gametype> gametypes = (List<Gametype>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Gametype.class)
        .add(Restrictions.eq("name", name)));
        
        if(gametypes.size() > 0)
        {
            return gametypes.get(0);
        }
        else 
            return null;
    }

    @Override
    @Transactional
    public void create(Gametype gametype) {
        getHibernateTemplate().save(gametype);
    }

    @Override
    @Transactional
    public void update(Gametype gametype) {
        getHibernateTemplate().update(gametype);
    }

    @Override
    @Transactional
    public void delete(Gametype gametype) {
        getHibernateTemplate().delete(gametype);
    }
    
}
