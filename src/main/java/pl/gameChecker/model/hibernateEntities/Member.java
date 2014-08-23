/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gameChecker.model.hibernateEntities;


import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name = "MEMBERS")
@Table(name = "MEMBERS")
public class Member implements Serializable  {

    @Id
    @Column(name = "MEMBER_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "MEMBER_NAME", unique = true, nullable = false, length = 15)
    protected String name;
    
    @Column(name = "MEMBER_PASSWORD", nullable = false)
    protected String password;
    
    @Column(name = "MEMBER_REGISTER_DATE", nullable = false)
    protected Date registerDate;
    
    @Column(name = "MEMBER_MAIL", unique = true, nullable = false, length = 45)
    protected String mail;
    
    @Column(name = "MEMBER_BIRTHDATE", nullable = false)
    protected Date birthDate;
    
    @Column(name = "MEMBER_POINTS", nullable = false)
    protected int points;
    
    @OneToMany(mappedBy = "member")
    protected List<MembersRoles> membersRoles;
    
    @OneToMany(mappedBy = "member")
    protected List<MembersPC> membersPCs;
    
    @OneToOne
    @JoinColumn(name = "MEMBERS_LIBRARIES_LIBRARY_ID")
    protected Library library;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaQuestion> sqfaQuestions;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaAnswer> sqfaAnswers;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaComment> sqfaComments;

    public Member(){}
    
    public Member(String name, String password, Date registerDate, String mail, Date birthDate) {
        this.name = name;
        this.password = password;
        this.registerDate = registerDate;
        this.mail = mail;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MembersRoles> getMembersRoles() {
        return membersRoles;
    }

    public void setMembersRoles(List<MembersRoles> membersRoles) {
        this.membersRoles = membersRoles;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<MembersRoles> getRoles() {
        return membersRoles;
    }

    public void setRoles(List<MembersRoles> roles) {
        this.membersRoles = roles;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public List<SqfaQuestion> getSqfaQuestions() {
        return sqfaQuestions;
    }

    public void setSqfaQuestions(List<SqfaQuestion> sqfaQuestions) {
        this.sqfaQuestions = sqfaQuestions;
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
