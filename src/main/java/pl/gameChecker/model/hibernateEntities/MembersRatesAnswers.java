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
@Entity
@Table(name = "MEMBERS_RATES_ANSWERS")
public class MembersRatesAnswers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_RATES_ANSWERS_ID")
    protected int id;
    
    @Column(name = "MEMBERS_RATES_ANSWERS_IS_RATED", nullable = false)
    protected boolean isRated;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_RATES_ANSWERS_MEMBER_ID")
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_RATES_ANSWERS_ANSWER_ID")
    protected SqfaAnswer sqfaAnswer;

    public MembersRatesAnswers(boolean isRated, Member member, SqfaAnswer sqfaAnswer) {
        this.isRated = isRated;
        this.member = member;
        this.sqfaAnswer = sqfaAnswer;
    }

    public MembersRatesAnswers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsRated() {
        return isRated;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
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

    public void setSqfaAnswer(SqfaAnswer sqfaAnswer) {
        this.sqfaAnswer = sqfaAnswer;
    }
}
