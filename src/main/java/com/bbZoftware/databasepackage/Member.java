/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbzoftware.databasepackage;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Damian Leœniak
 * @version 1.0
 */
@Entity(name = "MEMBERS")
@Table(name = "MEMBERS")
public class Member {

    @Id
    @Column(name = "MEMBER_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(name = "MEMBER_NAME")
    protected String name;
    
    @Column(name = "REGISTER_DATE")
    protected Date registerDate;
    
    @Column(name = "MEMBER_MAIL")
    protected String mail;
    
    @Column(name = "MEMBER_AGE")
    protected int age;
    
    @Column(name = "MEMBER_POINTS")
    protected int points;
    
    @OneToMany(mappedBy = "member")
    protected List<MembersRoles> roles;

    public Member() {};
    
    public Member(String name, Date registerDate, String mail, int age, int points) {
        this.name = name;
        this.registerDate = registerDate;
        this.mail = mail;
        this.age = age;
        this.points = points;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<MembersRoles> getRoles() {
        return roles;
    }

    public void setRoles(List<MembersRoles> roles) {
        this.roles = roles;
    }
    
    

}
