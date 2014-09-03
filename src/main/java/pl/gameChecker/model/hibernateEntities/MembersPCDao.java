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
public interface MembersPCDao {
    public List<MembersPC> getList();
    public MembersPC getById(int id);
    public List<MembersPC> getWhereNameLike(String name);
    public void create(MembersPC membersPC);
    public void update(MembersPC membersPC);
    public void delete(MembersPC membersPC);
    public List<MembersPC> getAllPCsOfMember(Member member);
}
