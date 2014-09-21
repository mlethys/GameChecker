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
public interface SqfaQuestionCommentDao {
    public List<SqfaQuestionComment> getList();
    public SqfaQuestionComment getById(int id);
    public List<SqfaQuestionComment> getCommentsFromQuestion(SqfaQuestion sqfaQuestion);
    public void create(SqfaQuestionComment sqfaQuestionComment);
    public void update(SqfaQuestionComment sqfaQuestionComment);
    public void delete(SqfaQuestionComment sqfaQuestionComment);
    public List<SqfaQuestionComment> getAllQuestionCommentsFromMember(Member member);
}
