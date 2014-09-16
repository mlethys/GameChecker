/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author bbZoftware
 */
public interface GameDao {
    public List<Game> getList();
    public Game getById(int id);
    public Game getByName(String name);
    public List<Game> getByNameAndMember(String name, Member member);
    public List<Game> getGamesWhereNameLike(String likeThis);
    public List<Object[]> getGamesAdditionAfterDate(Date afterThisDate);
    public void rateGame(Game game, int rating);
    public int getGamePopularityProcent(Game game);
    public void create(Game game);
    public void update(Game game);
    public void delete(Game game);
    public List<Game> getSearchGameResults(String name, Date releasedDateBetweenLow, Date releasedDateBetweenHigh, boolean singleplayer, boolean multiplayer, boolean freeToPlay, double gameStarsBeetweenLow, double gameStarsBeetweenHigh, Gametype gametype, int gamePopularityBetweenLow, int gamePopularityBetweenHigh);
    public void updateGameInfo(Game game, String name, boolean isSingleplayer, boolean isMultiplayer, boolean isFreeToPlay, int releaseDay, int releaseMonth, int releaseYear, String gametypeName);
    public List<Game> getGamesFromMember(Member member);
    public void updateGamePopularity(Game game);
}
