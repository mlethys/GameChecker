/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbzoftware.databasepackage;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Damian Leœniak
 * @version 1.0
 */
@Entity(name="MEMBERS_ROLES")
@Table(name="MEMBERS_ROLES")
public class MembersRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_ROLES_ID")
    protected int id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="MEMBERS_MEMBER_ID")
    protected Member member;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ROLES_ROLE_ID")
    protected Role role;
    
    public MembersRoles(){};
    
    public MembersRoles(Member member, Role role) {
        this.member = member;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
