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
public interface MembersGPUDao {
    public List<MembersGPU> getList();
    public MembersGPU getById(int id);
    public List<MembersGPU> getWhereNameLike(String name);
    public void create(MembersGPU membersGPU);
    public void update(MembersGPU membersGPU);
    public void delete(MembersGPU membersGPU);
    public List<MembersGPU> getAllCPUsOfCompany(Company company);
    public MembersGPU getByName(String name);
}
