/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import org.hibernate.Session;
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
public class LibraryDaoImpl extends HibernateDaoSupport implements LibraryDao{

    public LibraryDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    @Override
    @Transactional
    public List<Library> getList() {
        List<Library> libraries = getHibernateTemplate().loadAll(Library.class);
        return libraries;
    }

    @Override
    @Transactional
    public Library getById(int id) {
        Library library = (Library) getHibernateTemplate().get(Library.class, id);
        return library;
    }

    @Override
    @Transactional
    public void create(Library library) {
        getHibernateTemplate().save(library);
    }

    @Override
    @Transactional
    public void update(Library library) {
        getHibernateTemplate().update(library);
    }

    @Override
    @Transactional
    public void delete(Library library) {
        getHibernateTemplate().delete(library);
    }

    @Override
    @Transactional
    public void addGameToMembersLibrary(Member member, Game game) {
        getHibernateTemplate().save(newGameInLibrary(member, game));
        getHibernateTemplate().update(updatedGamePopularity(game));
    }
    
    protected GamesLibraries newGameInLibrary(Member member, Game game) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        Library library = (Library) getHibernateTemplate().get(Library.class, member.getLibrary().getId());
        GamesLibraries gamesLibraries = new GamesLibraries(game, library, new Date(secondsSinceEpoch));
        return gamesLibraries;
    }
    
    protected Game updatedGamePopularity(Game game){
        int totalMembersCount = ((Long)getSessionFactory().getCurrentSession().createQuery("select count(*) from MEMBERS").uniqueResult()).intValue();
        int membersWithGameCount = ((Long)getSessionFactory().getCurrentSession().createQuery("select count(*) from GAMES_LIBRARIES where GAMES_GAME_ID = '" + game.getId() + "'").uniqueResult()).intValue();
        
        int result = 100 * membersWithGameCount / totalMembersCount;
        game.setPopularity(result);
        return game;
    }

    @Override
    @Transactional
    public void removeGameFromMembersLibrary(Member member, Game game) {
        List<GamesLibraries> gamesLibraries = (List<GamesLibraries>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(GamesLibraries.class)
        .add(Restrictions.and(Restrictions.eq("library", member.getLibrary()), Restrictions.eq("game", game))));
        
        if(gamesLibraries.size() > 0)
        {
            getHibernateTemplate().delete(gamesLibraries.get(0));
        }        
//        getHibernateTemplate().update(updatedGamePopularity(game));
    }
}
