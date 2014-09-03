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
public interface SqfaAnswerCommentDao {
    public List<SqfaAnswerComment> getList();
    public SqfaAnswerComment getById(int id);
    public void create(SqfaAnswerComment sqfaAnswerComment);
    public void update(SqfaAnswerComment sqfaAnswerComment);
    public void delete(SqfaAnswerComment sqfaAnswerComment);
    public List<SqfaAnswerComment> getAllAnswerCommentsFromMember(Member member);
}
