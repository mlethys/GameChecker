/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import javax.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;

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
    
    @Column(name = "MEMBER_AVATAR", nullable = true)
    protected String avatarURL;
    
    @ManyToOne
    @JoinColumn(name = "ROLES_ROLE_ID")
    protected Role role;
    
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    protected List<MembersPC> membersPCs;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LIBRARIES_LIBRARY_ID")
    protected Library library;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaQuestion> sqfaQuestions;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaAnswer> sqfaAnswers;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaQuestionComment> sqfaQuestionComments;
    
    @OneToMany(mappedBy = "member")
    protected List<SqfaAnswerComment> sqfaAnswerComments;
    
    @OneToMany(mappedBy = "member")
    protected List<MembersRatesGames> membersRatesGames;
    
    public Member(){}
    
    @Deprecated
    public Member(String name, String password, String mail, int birthDay, int birthMonth, int birthYear) {
        password = DigestUtils.sha256Hex(password);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        calendar.clear();
        calendar.set(birthYear, birthMonth - 1, birthDay);
        long secondsSinceEpochToBirthday = calendar.getTimeInMillis();
        
        this.name = name;
        this.password = password;
        this.registerDate = new Date(secondsSinceEpoch);
        this.mail = mail;
        this.birthDate = new Date(secondsSinceEpochToBirthday);
        this.points = 0;
    }
    
    public Member(String name, String password, String mail, int birthDay, int birthMonth, int birthYear, Role role) {
        password = DigestUtils.sha256Hex(password);
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long secondsSinceEpoch = calendar.getTimeInMillis();
        calendar.clear();
        calendar.set(birthYear, birthMonth - 1, birthDay);
        long secondsSinceEpochToBirthday = calendar.getTimeInMillis();
        
        this.name = name;
        this.password = password;
        this.registerDate = new Date(secondsSinceEpoch);
        this.mail = mail;
        this.birthDate = new Date(secondsSinceEpochToBirthday);
        this.points = 0;
        this.role = role;
    }

    public int getId() {
        return id;
    }
    
    public int getAge() {
        Calendar calendar=Calendar.getInstance();
        Calendar calendarnow=Calendar.getInstance();    
        calendarnow.getTimeZone();
        calendar.setTime(birthDate);
        int getmonth= calendar.get(Calendar.MONTH);
        int getyears= calendar.get(Calendar.YEAR);
        int currentmonth= calendarnow.get(Calendar.MONTH);
        int currentyear= calendarnow.get(Calendar.YEAR);
        return ((currentyear*12+currentmonth)-(getyears*12+getmonth))/12;
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

    public List<MembersPC> getMembersPCs() {
        return membersPCs;
    }

    public void setMembersPCs(List<MembersPC> membersPCs) {
        this.membersPCs = membersPCs;
    }

    public List<SqfaQuestionComment> getSqfaQuestionComments() {
        return sqfaQuestionComments;
    }

    public void setSqfaQuestionComments(List<SqfaQuestionComment> sqfaQuestionComments) {
        this.sqfaQuestionComments = sqfaQuestionComments;
    }

    public List<SqfaAnswerComment> getSqfaAnswerComments() {
        return sqfaAnswerComments;
    }

    public void setSqfaAnswerComments(List<SqfaAnswerComment> sqfaAnswerComments) {
        this.sqfaAnswerComments = sqfaAnswerComments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getAvatarUrl() {
        return this.avatarURL;
    }
    
    public void setAvatarUrl(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public List<MembersRatesGames> getMembersRatesGames() {
        return membersRatesGames;
    }

    public void setMembersRatesGames(List<MembersRatesGames> membersRatesGames) {
        this.membersRatesGames = membersRatesGames;
    }
    
    
}
