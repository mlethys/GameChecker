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
public interface SqfaQuestionDao {
    public List<SqfaQuestion> getList();
    public SqfaQuestion getById(int id);
    public List<SqfaQuestion> getWhereTitleLike(String title);
    public void create(SqfaQuestion sqfaQuestion);
    public void update(SqfaQuestion sqfaQuestion);
    public void delete(SqfaQuestion sqfaQuestion);
    public List<SqfaQuestion> getAllQuestionsFromMember(Member member);
}
