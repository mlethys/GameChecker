/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.util.List;

/**
 *
 * @author bbZoftware
 */
public interface MembersRatesGamesDao {
    public List<MembersRatesGames> getList();
    public MembersRatesGames getById(int id);
    public List<MembersRatesGames> getMembersRatesGamesByGame(Game game);
    public List<MembersRatesGames> getMembersRatesGamesByMember(Member member);
    public void create(MembersRatesGames membersRatesGames);
    public void update(MembersRatesGames membersRatesGames);
    public void delete(MembersRatesGames membersRatesGames);
    public boolean isMemberRatedGame(Member member, Game game);
}
