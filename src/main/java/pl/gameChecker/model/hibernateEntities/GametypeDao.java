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
public interface GametypeDao {
    public List<Gametype> getList();
    public Gametype getById(int id);
    public Gametype getByName(String name);
    public void create(Gametype gametype);
    public void update(Gametype gametype);
    public void delete(Gametype gametype);
}
