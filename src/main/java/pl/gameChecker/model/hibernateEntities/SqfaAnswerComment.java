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
@Entity(name="SQFA_ANSWER_COMMENTS")
@Table(name="SQFA_ANSWER_COMMENTS")
public class SqfaAnswerComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SQFA_ANSWER_COMMENT_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="SQFA_ANSWER_COMMENT_CONTENT", nullable = false, length = Integer.MAX_VALUE)
    protected String content;
    
    @Column(name = "SQFA_ANSWER_ADDITION_DATE", nullable = false)
    protected Timestamp additionDate;
   
    @ManyToOne
    @JoinColumn(name="MEMBERS_MEMBER_ID", nullable = true)
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name="SQFA_ANSWERS_ANSWER_ID", nullable = false)
    protected SqfaAnswer sqfaAnswer;
        
    public SqfaAnswerComment() {}

    public SqfaAnswerComment(Member member, SqfaAnswer sqfaAnswer, String content, Timestamp additionDate) {
        this.content = content;
        this.member = member;
        this.additionDate = additionDate;
        this.sqfaAnswer = sqfaAnswer;
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

    public SqfaAnswer getSqfaAnswer() {
        return sqfaAnswer;
    }

    public void SqfaAnswer(SqfaAnswer sqfaAnswer) {
        this.sqfaAnswer = sqfaAnswer;
    }       

    public Timestamp getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Timestamp additionDate) {
        this.additionDate = additionDate;
    }
}
