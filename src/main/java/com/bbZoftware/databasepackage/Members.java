/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.databasepackage;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */

@Entity
@Table(name = "MEMBERS")
public class Members implements Serializable {
    
    private long id;
    private String name;
    private Date registerDate;
    private String memberEmail;
    private int age;
    private int points;
    private long memberLibrariesLibraryId;

    public Members(){
    }
    
    public Members(String name, Date registerDate, String memberEmail, int age) {
        this.name = name;
        this.registerDate = registerDate;
        this.memberEmail = memberEmail;
        this.age = age;
        this.points = 0;
    }

    @Id
    //    @OneToMany(mappedBy = "MEMBERS")
    @Column(name = "MEMBER_ID", unique = true, nullable = false, updatable = false, precision = 5, scale = 0)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "MEMBER_NAME", nullable = false, length = 20, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "MEMBER_DATE", nullable = false, length = 7)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Column(name = "MEMBER_MAIL", nullable = false, length = 30)
    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    @Column(name = "MEMBER_AGE", nullable = false, length = 3)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    @Column(name = "MEMBER_POINTS", nullable = false)
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    
    @OneToOne
    @JoinColumn(name = "LIBRARY_ID")
    public Library getMemberLibrariesLibraryId() {
        return memberLibrariesLibraryId;
    }

    public void setMemberLibrariesLibraryId(int memberLibrariesLibraryId) {
        this.memberLibrariesLibraryId = memberLibrariesLibraryId;
    }

}
