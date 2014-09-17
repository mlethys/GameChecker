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
public interface MemberDao {
    public List<Member> getList();
    public Member getById(int id);
    public Member getByName(String name);
    public List<Member> getMembersWhereRegisterDateBetween(Date dateFrom, Date dateTo);
    public void create(Member member);
    public void update(Member member);
    public void delete(Member member);
    public void delete(Member member, List<SqfaAnswer> sqfaAnswersFromMember, List<SqfaQuestion> sqfaQuestionsFromMember,
        List<SqfaQuestionComment> sqfaQuestionCommentsFromMember, List<SqfaAnswerComment> sqfaAnswerCommentsFromMember,
        List<MembersRatesGames> membersRatesGamesFromMember);
    public boolean isMemberLoginMatchPassword(String login, String password);
    public void updateMemberProfile(Member member, String name, String password, String mail, int birthDay, int birthMonth, int birthYear);
    public boolean exists(Member member);
}
