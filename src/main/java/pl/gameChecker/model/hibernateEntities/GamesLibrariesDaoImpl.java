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
public class GamesLibrariesDaoImpl extends HibernateDaoSupport implements GamesLibrariesDao {

    public GamesLibrariesDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    
    @Override
    @Transactional
    public List<GamesLibraries> getList() {
        List<GamesLibraries> gameslibraries = getHibernateTemplate().loadAll(GamesLibraries.class);
        return gameslibraries;
    }

    @Override
    @Transactional
    public GamesLibraries getById(int id) {
        GamesLibraries gameslibrary = (GamesLibraries) getHibernateTemplate().get(GamesLibraries.class, id);
        return gameslibrary;
    }

    @Override
    @Transactional
    public List<GamesLibraries> getGamesLibrariesByLibrary(Library library) {
        List<GamesLibraries> gamesLibraries = (List<GamesLibraries>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(GamesLibraries.class)
        .add(Restrictions.eq("library", library)));

        return gamesLibraries;
        
    }

    @Override
    @Transactional
    public void create(GamesLibraries gamesLibraries) {
        getHibernateTemplate().save(gamesLibraries);
    }

    @Override
    @Transactional
    public void update(GamesLibraries gamesLibraries) {
        getHibernateTemplate().update(gamesLibraries);
    }

    @Override
    @Transactional
    public void delete(GamesLibraries gamesLibraries) {
        getHibernateTemplate().delete(gamesLibraries);
    }

    @Override
    public boolean isMemberGotGameInLibrary(Member member, Game game) {
        List<GamesLibraries> games = (List<GamesLibraries>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(GamesLibraries.class)
        .add(Restrictions.eq("library", member.getLibrary()))
        .add(Restrictions.eq("game", game)));
        
        if(games.size() > 0)
        {
            return true;
        }
        else return false;
    }

    @Override
    public List<GamesLibraries> getGamesLibrariesByGame(Game game) {
        List<GamesLibraries> gamesLibraries = (List<GamesLibraries>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(GamesLibraries.class)
        .add(Restrictions.eq("game", game)));

        return gamesLibraries;
    }

}
