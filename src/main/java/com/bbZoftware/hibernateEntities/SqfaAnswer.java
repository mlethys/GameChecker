/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.hibernateEntities;

import java.io.Serializable;
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
    @Column(name="SQFA_ANSWER_ID")
    protected int id;
    
    @Column(name="SQFA_ANSWER_CONTENT")
    protected String content;
    
    @Column(name="SQFA_ANSWER_POINTS")
    protected int points;
    
    @OneToMany(mappedBy = "sqfaAnswer")
    protected List<SqfaComment> sqfaComments;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MEMBERS_MEMBER_ID")
    protected Member member;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SQFA_QUESTIONS_QUESTION_ID")
    protected SqfaQuestion sqfaQuestion;
    
    public SqfaAnswer() {}

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

    public List<SqfaComment> getSqfaComments() {
        return sqfaComments;
    }

    public void setSqfaComments(List<SqfaComment> sqfaComments) {
        this.sqfaComments = sqfaComments;
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
    
    
}
