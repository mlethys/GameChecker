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
public class MembersRatesGamesDaoImpl extends HibernateDaoSupport implements MembersRatesGamesDao {
    
    public MembersRatesGamesDaoImpl(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    @Transactional
    public List<MembersRatesGames> getList() {
        List<MembersRatesGames> membersRatesGames = getHibernateTemplate().loadAll(MembersRatesGames.class);
        return membersRatesGames;
    }

    @Override
    @Transactional
    public MembersRatesGames getById(int id) {
        MembersRatesGames membersRatesGame = (MembersRatesGames) getHibernateTemplate().get(MembersRatesGames.class, id);
        return membersRatesGame;
    }

    @Override
    @Transactional
    public List<MembersRatesGames> getMembersRatesGamesByGame(Game game) {
        List<MembersRatesGames> membersRatesGames = (List<MembersRatesGames>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesGames.class)
        .add(Restrictions.eq("game", game)));
        
    return membersRatesGames;
    }

    @Override
    @Transactional
    public List<MembersRatesGames> getMembersRatesGamesByMember(Member member) {
        List<MembersRatesGames> membersRatesGames = (List<MembersRatesGames>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesGames.class)
        .add(Restrictions.eq("member", member)));

        return membersRatesGames;
    }

    @Override
    @Transactional
    public void create(MembersRatesGames membersRatesGames) {
        getHibernateTemplate().save(membersRatesGames);
    }

    @Override
    @Transactional
    public void update(MembersRatesGames membersRatesGames) {
        getHibernateTemplate().update(membersRatesGames);
    }

    @Override
    @Transactional
    public void delete(MembersRatesGames membersRatesGames) {
        getHibernateTemplate().delete(membersRatesGames);
    }

    @Override
    @Transactional
    public boolean isMemberRatedGame(Member member, Game game) {
        List<MembersRatesGames> membersRatesGames = (List<MembersRatesGames>) getHibernateTemplate().findByCriteria(
        DetachedCriteria.forClass(MembersRatesGames.class)
        .add(Restrictions.and(Restrictions.eq("member", member), Restrictions.eq("game", game))));
        
        return membersRatesGames.size() > 0;
    }

}
