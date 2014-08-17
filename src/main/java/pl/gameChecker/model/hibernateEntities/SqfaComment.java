/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import com.bbZoftware.hibernateEntities.*;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name="SQFA_COMMENTS")
@Table(name="SQFA_COMMENTS")
public class SqfaComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SQFA_COMMENT_ID")
    protected int id;
    
    @Column(name="SQFA_COMMENT_CONTENT")
    protected String content;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MEMBERS_MEMBER_ID")
    protected Member member;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="SQFA_QUESTIONS_QUESTION_ID")
    protected SqfaQuestion sqfaQuestion;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="SQFA_ANSWERS_ANSWER_ID")
    protected SqfaAnswer sqfaAnswer;

    public SqfaComment() {}

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

    public SqfaAnswer getSqfaAnswer() {
        return sqfaAnswer;
    }

    public void setSqfaAnswer(SqfaAnswer sqfaAnswer) {
        this.sqfaAnswer = sqfaAnswer;
    }
    
    
}