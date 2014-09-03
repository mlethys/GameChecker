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
public interface CompanyDao {
    public List<Company> getList();
    public Company getById(int id);
    public Company getByName(String name);
    public void create(Company company);
    public void update(Company company);
    public void delete(Company company);
}
