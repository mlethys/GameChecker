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
public interface GamesLibrariesDao {
    public List<GamesLibraries> getList();
    public GamesLibraries getById(int id);
    public List<GamesLibraries> getGamesLibrariesByLibrary(Library library);
    public void create(GamesLibraries gamesLibraries);
    public void update(GamesLibraries gamesLibraries);
    public void delete(GamesLibraries gamesLibraries);
}
