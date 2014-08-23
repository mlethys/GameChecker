/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;


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
    @Column(name="SQFA_COMMENT_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="SQFA_COMMENT_CONTENT", nullable = false, length = Integer.MAX_VALUE)
    protected String content;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MEMBERS_MEMBER_ID", nullable = false)
    protected Member member;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="SQFA_QUESTIONS_QUESTION_ID", nullable = false)
    protected SqfaQuestion sqfaQuestion;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="SQFA_ANSWERS_ANSWER_ID", nullable = false)
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
