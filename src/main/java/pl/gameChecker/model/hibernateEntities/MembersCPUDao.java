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
public interface MembersCPUDao {
    public List<MembersCPU> getList();
    public MembersCPU getById(int id);
    public List<MembersCPU> getWhereNameLike(String name);
    public void create(MembersCPU membersCPU);
    public void update(MembersCPU membersCPU);
    public void delete(MembersCPU membersCPU);
    public List<MembersCPU> getAllCPUsOfCompany(Company company);
    public MembersCPU getByName(String name);
}
