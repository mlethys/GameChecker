/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name="SQFA_ANSWERS")
@Table(name="SQFA_ANSWERS")
public class SqfaAnswer implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SQFA_ANSWER_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="SQFA_ANSWER_CONTENT", unique = true, nullable = false, length = Integer.MAX_VALUE)
    protected String content;
    
    @Column(name="SQFA_ANSWER_POINTS", nullable = false)
    protected int points;
    
    @Column(name = "SQFA_ANSWER_ADDITION_DATE", nullable = false)
    protected Timestamp additionDate;
    
    @OneToMany(mappedBy = "sqfaAnswer")
    protected List<SqfaAnswerComment> sqfaAnswerComments;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_MEMBER_ID", nullable = false)
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name = "SQFA_QUESTIONS_QUESTION_ID", nullable = false)
    protected SqfaQuestion sqfaQuestion;
    
    public SqfaAnswer() {}
    
    public SqfaAnswer(Member member, SqfaQuestion sqfaQuestion, String content, Timestamp additionDate) {
        this.member = member;
        this.sqfaQuestion = sqfaQuestion;
        this.content = content;
        this.additionDate = additionDate;
        this.points = 0;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<SqfaAnswerComment> getSqfaAnswerComments() {
        return sqfaAnswerComments;
    }

    public void setSqfaAnswerComments(List<SqfaAnswerComment> sqfaAnswerComments) {
        this.sqfaAnswerComments = sqfaAnswerComments;
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
