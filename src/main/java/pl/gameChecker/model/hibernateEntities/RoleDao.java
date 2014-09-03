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
public interface RoleDao {
    public List<Role> getList();
    public Role getById(int id);
    public Role getByName(String name);
    public void create(Role role);
    public void update(Role role);
    public void delete(Role role);
}
