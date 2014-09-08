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
public class CompanyDaoImpl extends HibernateDaoSupport implements CompanyDao {

    public CompanyDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<Company> getList() {
        List<Company> companies = getHibernateTemplate().loadAll(Company.class);
        return companies;
    }

    @Override
    @Transactional
    public Company getById(int id) {
        Company company = (Company) getHibernateTemplate().get(Company.class, id);
        return company;
    }

    @Override
    @Transactional
    public Company getByName(String name) {
        List<Company> companies = (List<Company>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Company.class)
        .add(Restrictions.eq("name", name)));
        
        if(companies.size() > 0)
        {
            return companies.get(0);
        }
        else return null;
    }

    @Override
    @Transactional
    public void create(Company company) {
        getHibernateTemplate().save(company);
    }

    @Override
    @Transactional
    public void update(Company company) {
        getHibernateTemplate().update(company);
    }

    @Override
    @Transactional
    public void delete(Company company) {
        getHibernateTemplate().delete(company);
    }
}
