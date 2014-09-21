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
public interface SqfaAnswerDao {
    public List<SqfaAnswer> getList();
    public SqfaAnswer getById(int id);
    public List<SqfaAnswer> getAnswersFromQuestion(SqfaQuestion sqfaQuestion);
    public void create(SqfaAnswer sqfaAnswer);
    public void update(SqfaAnswer sqfaAnswer);
    public void delete(SqfaAnswer sqfaAnswer);
    public List<SqfaAnswer> getAllAnswersFromMember(Member member);
    public List<Object[]> getSqfaSummaryBetween(Date dateFrom, Date dateTo);
    public void incrementSqfaAnswerPoints(SqfaAnswer sqfaAnswer);
}
