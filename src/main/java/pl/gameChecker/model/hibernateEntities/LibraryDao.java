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
public interface LibraryDao {
    public List<Library> getList();
    public Library getById(int id);
    public void create(Library library);
    public void update(Library library);
    public void delete(Library library);
    public void addGameToMembersLibrary(Member member, Game game);
}
