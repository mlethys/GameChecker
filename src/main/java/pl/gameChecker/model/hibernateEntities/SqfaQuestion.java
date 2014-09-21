/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.*;
/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name="SQFA_QUESTIONS")
@Table(name="SQFA_QUESTIONS")
public class SqfaQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQFA_QUESTION_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "SQFA_QUESTION_TITLE", unique = true, nullable = false)
    protected String title;

    @Column(name = "SQFA_QUESTION_CONTENT", nullable = false)
    protected String content;
    
    @Column(name = "SQFA_QUESTION_ADDITION_DATE", nullable = false)
    protected Timestamp additionDate;
    
    @Column(name = "SQFA_QUESTION_ASNWERS_COUNT")
    protected int answersCount;

    @ManyToOne
    @JoinColumn(name="MEMBERS_MEMBER_ID", nullable = true)
    protected Member member;

    @OneToMany(mappedBy = "sqfaQuestion", cascade = CascadeType.ALL)
    protected List<SqfaAnswer> sqfaAnswers;

    @OneToMany(mappedBy = "sqfaQuestion", cascade = CascadeType.ALL)
    protected List<SqfaQuestionComment> sqfaQuestionComments;

    public SqfaQuestion() {}

    public SqfaQuestion(Member member, String title, String content) { 
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        this.member = member;
        this.content = content;
        this.additionDate = new Timestamp(secondsSinceEpoch);
        this.title = title;
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

    public List<SqfaAnswer> getSqfaAnswers() {
        return sqfaAnswers;
    }

    public void setSqfaAnswers(List<SqfaAnswer> sqfaAnswers) {
        this.sqfaAnswers = sqfaAnswers;
    }

    public List<SqfaQuestionComment> getSqfaQuestionComments() {
        return sqfaQuestionComments;
    }

    public void setSqfaQuestionComments(List<SqfaQuestionComment> sqfaQuestionComments) {
        this.sqfaQuestionComments = sqfaQuestionComments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }    

    public Timestamp getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Timestamp additionDate) {
        this.additionDate = additionDate;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }
}