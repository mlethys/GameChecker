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
public interface MembersRatesAnswersDao {
    public List<MembersRatesAnswers> getList();
    public MembersRatesAnswers getById(int id);
    public List<MembersRatesAnswers> getMembersRatesGamesByAnswer(SqfaAnswer sqfaAnswer);
    public List<MembersRatesAnswers> getMembersRatesGamesByMember(Member member);
    public void create(MembersRatesAnswers membersRatesAnswers);
    public void update(MembersRatesAnswers membersRatesAnswers);
    public void delete(MembersRatesAnswers membersRatesAnswers);
    public boolean isMemberRatedAnswer(Member member, SqfaAnswer sqfaAnswer);
}
