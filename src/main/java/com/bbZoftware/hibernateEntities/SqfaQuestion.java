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
@Entity(name="SQFA_QUESTIONS")
@Table(name="SQFA_QUESTIONS")
public class SqfaQuestion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SQFA_QUESTION_ID")
    protected int id;
    
    @Column(name = "SQFA_QUESTION_CONTENT")
    protected String content;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MEMBERS_MEMBER_ID")
    protected Member member;
    
    @OneToMany(mappedBy = "sqfaQuestion")
    protected List<SqfaAnswer> sqfaAnswers;
    
    @OneToMany(mappedBy = "sqfaQuestion")
    protected List<SqfaComment> sqfaComments;

    public SqfaQuestion() {}
    
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

    public List<SqfaComment> getSqfaComments() {
        return sqfaComments;
    }

    public void setSqfaComments(List<SqfaComment> sqfaComments) {
        this.sqfaComments = sqfaComments;
    }
    
    
    
}
