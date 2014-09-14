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
import java.util.Vector;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class GameDaoImpl extends HibernateDaoSupport implements GameDao {

    public GameDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
    @Override
    @Transactional
    public List<Game> getList() {
        List<Game> games = getHibernateTemplate().loadAll(Game.class);
        return games;
    }

    @Override
    @Transactional
    public Game getById(int id) {
        Game game = (Game) getHibernateTemplate().get(Game.class, id);
        return game;
    }

    @Override
    @Transactional
    public Game getByName(String name) {
        List<Game> games = (List<Game>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Game.class)
        .add(Restrictions.eq("name", name)));
        
        if(games.size() > 0)
        {
            return games.get(0);
        }
        else return null;
    }

    @Override
    @Transactional
    public List<Game> getGamesWhereNameLike(String likeThis) {
        List<Game> games = (List<Game>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(Game.class)
        .add(Restrictions.like("name", likeThis, MatchMode.START)));
        return games;
    }

    @Override
    @Transactional
    public List<Object[]> getGamesAdditionAfterDate(final Date afterThisDate) {
        return getHibernateTemplate().execute(new HibernateCallback<List>() {
            @Override
            public List doInHibernate(Session s) throws HibernateException {
                Query query = s.createQuery("from GAMES g, MEMBERS m, GAMES_LIBRARIES gl where "
                + "gl.game=g.id and gl.library=m.id and gl.additionDate >= :date order by m.name" );
                query.setParameter("date", afterThisDate);
                return query.list();
            }
        });
    }

    @Override
    @Transactional
    public void create(Game game) {
        getHibernateTemplate().save(game);
    }

    @Override
    @Transactional
    public void update(Game game) {
        getHibernateTemplate().update(game);
    }

    @Override
    @Transactional
    public void delete(Game game) {
        getHibernateTemplate().delete(game);
    }

    @Override
    @Transactional
    public void rateGame(Game game, int rating) {
        double stars = game.getStars();
        int rates = game.getRates();
        int newRates = rates + 1;
        game.setRates(newRates);

        double average = (stars * rates + rating) / newRates;
        game.setStars(average);

        getHibernateTemplate().update(game);
    }

    @Override
    @Transactional
    public int getGamePopularityProcent(Game game) {
        int totalMembersCount = ((Long)getSessionFactory().getCurrentSession().createQuery("select count(*) from MEMBERS").uniqueResult()).intValue();
        int membersWithGameCount = ((Long)getSessionFactory().getCurrentSession().createQuery("select count(*) from GAMES_LIBRARIES where GAMES_GAME_ID = '" + game.getId() + "'").uniqueResult()).intValue();
        
        int result = 100 * membersWithGameCount / totalMembersCount;
        return result;
    }

    @Override
    @Transactional
    public List<Game> getSearchGameResults(String name, Date releasedDateBetweenLow, Date releasedDateBetweenHigh, boolean singleplayer, boolean multiplayer, boolean freeToPlay, double gameStarsBeetweenLow, double gameStarsBeetweenHigh, Gametype gametype, int gamePopularityBetweenLow, int gamePopularityBetweenHigh) {
        boolean firstCriteria = true;
        String preparedQuery = "from GAMES g ";
        if(name != null) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.name like %" + name + "% ";
            firstCriteria = false;
        }
        if(releasedDateBetweenLow != null) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.releaseDate <='" + releasedDateBetweenLow + "' ";
            firstCriteria = false;
        }
        if(releasedDateBetweenHigh != null) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.releaseDate >='" + releasedDateBetweenHigh + "' ";
            firstCriteria = false;
        }
        if(singleplayer) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.singleplayer=" + singleplayer + " ";
            firstCriteria = false;
        }
        if(multiplayer) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.multiplayer=" + multiplayer + " ";
            firstCriteria = false;
        }
        if(freeToPlay) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.freeToPlay=" + freeToPlay + " ";
            firstCriteria = false;
        }
        if(gameStarsBeetweenHigh >= gameStarsBeetweenLow && gameStarsBeetweenLow >= 0 && gameStarsBeetweenLow <= 5) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.stars>=" + gameStarsBeetweenLow + " ";
        }
        if(gameStarsBeetweenHigh >= gameStarsBeetweenLow && gameStarsBeetweenHigh >= 0 && gameStarsBeetweenLow <= 5) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.stars<=" + gameStarsBeetweenHigh + " ";
        }
        if(gamePopularityBetweenHigh >= gamePopularityBetweenLow && gamePopularityBetweenLow >= 0) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.popularity>=" + gamePopularityBetweenLow + " ";
        }
        if(gamePopularityBetweenHigh >= gamePopularityBetweenLow && gamePopularityBetweenHigh <= 100) {
            if(!firstCriteria) 
                preparedQuery += "and "; 
            else 
                preparedQuery += "where ";
            preparedQuery += "g.popularity<=" + gamePopularityBetweenHigh + " ";
        }
            
        Query query = getSessionFactory().getCurrentSession().createQuery(preparedQuery);

        System.out.println(query.toString());

        List<Game> games = query.list();
                
        for(Game g : games) {
            System.out.println(g.getName());
        }
        return games;
    }

    @Override
    @Transactional
    public void updateGameInfo(Game game, String name, boolean isSingleplayer, boolean isMultiplayer, boolean isFreeToPlay, int releaseDay, int releaseMonth, int releaseYear, String gametypeName) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
                
        Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Gametype.class);
        criteria.add(Restrictions.eq("name", gametypeName));
        Gametype gametype = (Gametype) criteria.uniqueResult();

        if (game != null) {
            game.setName(name);
            game.setGametype(gametype);
            game.setSingleplayer(isSingleplayer);
            game.setMultiplayer(isMultiplayer);
            game.setFreeToPlay(isFreeToPlay);
            game.setReleaseDate(new Date(secondsSinceEpoch));
            getHibernateTemplate().update(game);
        }
    }

    @Override
    @Transactional
    public List<Game> getGamesFromMember(Member member) {
        List<Object[]> objects;
        List<Game> games = new Vector<Game>();
        
        Query query = getSessionFactory().getCurrentSession().createQuery("from GAMES_LIBRARIES gl, GAMES g where "
                + "g.id=gl.game and gl.library = :memberLibrary group by gl.id");
        query.setParameter("memberLibrary", member.getLibrary());

        objects = query.list();
        
        
        if(objects != null) {
            games.clear();
            for(Object[] obj : objects){
                games.add((Game) obj[1]);
            }
        }
        if(games.size() > 0)
            return games;
        else
            return null;
    }

}
