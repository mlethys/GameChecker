/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name="SQFA_QUESTION_COMMENTS")
@Table(name="SQFA_QUESTION_COMMENTS")
public class SqfaQuestionComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SQFA_QUESTION_COMMENT_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="SQFA_QUESTION_COMMENT_CONTENT", nullable = false, length = Integer.MAX_VALUE)
    protected String content;
    
    @Column(name = "SQFA_ANSWER_ADDITION_DATE", nullable = false)
    protected Timestamp additionDate;
    
    @ManyToOne
    @JoinColumn(name="MEMBERS_MEMBER_ID", nullable = false)
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name="SQFA_QUESTIONS_QUESTION_ID", nullable = false)
    protected SqfaQuestion sqfaQuestion;
        
    public SqfaQuestionComment() {}

    public SqfaQuestionComment(Member member, SqfaQuestion sqfaQuestion, String content, Timestamp additionDate) {
        this.content = content;
        this.member = member;
        this.additionDate = additionDate;
        this.sqfaQuestion = sqfaQuestion;
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public SqfaQuestion getSqfaQuestion() {
        return sqfaQuestion;
    }

    public void setSqfaQuestion(SqfaQuestion sqfaQuestion) {
        this.sqfaQuestion = sqfaQuestion;
    }       

    public Timestamp getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Timestamp additionDate) {
        this.additionDate = additionDate;
    }
}
