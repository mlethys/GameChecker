/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.database;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.gameChecker.model.hibernateEntities.GamesLibraries;
import pl.gameChecker.model.hibernateEntities.Library;
import pl.gameChecker.model.hibernateEntities.Member;
import pl.gameChecker.model.hibernateEntities.MembersPC;
import pl.gameChecker.model.hibernateEntities.SqfaAnswer;
import pl.gameChecker.model.hibernateEntities.SqfaAnswerComment;
import pl.gameChecker.model.hibernateEntities.SqfaQuestion;
import pl.gameChecker.model.hibernateEntities.SqfaQuestionComment;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
public class DatabaseModelRemoveMethods {
    
    private Session session;
    private Transaction transaction;
    private DatabaseModelGetMethods dbmGet = new DatabaseModelGetMethods();
    
    public void removeMember(Member member) {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        
//        Member memberToDelete = (Member) session.get(Member.class, member.getId());
        for(SqfaAnswer answer : dbmGet.getSqfaAnswersFromMember(member)) {
            answer.setMember(null);
            session.update(answer);
        }
        
        for(SqfaQuestion question : dbmGet.getSqfaQuestionsFromMember(member)) {
            question.setMember(null);
            session.update(question);
        }
        
        for(SqfaQuestionComment questionComment : dbmGet.getSqfaQuestionCommentsFromMember(member)) {
            questionComment.setMember(null);
            session.update(questionComment);
        }
        
        for(SqfaAnswerComment answerComment : dbmGet.getSqfaAnswerCommentsFromMember(member)) {
            answerComment.setMember(null);
            session.update(answerComment);
        }

        for(MembersPC membersPC : dbmGet.getMembersPCByMember(member)) {
            session.delete(membersPC);
        }
        
        Library library = dbmGet.getLibraryById(member.getId());
        for(GamesLibraries gamesLibraries : dbmGet.getGamesLibrariesByLibrary(library)) {
            session.delete(gamesLibraries);
        }
        
        session.delete(member);
        session.delete(library);

        transaction.commit();
        session.close();
    }

}
