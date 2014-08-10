/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.databasepackage;

import java.io.Serializable;
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
 * @author Damian Leśniak
 * @version 1.0
 */

@Entity
@Table(name = "MEMBERS_ROLES")
public class MembersRoles implements Serializable {
    private long membersRolesId;
    private Members membersMemberId;
    private long rolesRoleId;
    
    public MembersRoles(){
        
    }

    public MembersRoles(long membersRolesId, Members membersMemberId, long rolesRoleId) {
        this.membersRolesId = membersRolesId;
        this.membersMemberId = membersMemberId;
        this.rolesRoleId = rolesRoleId;
    }

    @Id
    @Column(name = "MEMBERS_ROLES_ID", unique = true, nullable = false, updatable = false, precision = 5)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getMembersRolesId() {
        return membersRolesId;
    }

    public void setMembersRolesId(long membersRolesId) {
        this.membersRolesId = membersRolesId;
    }
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_MEMBER_ID")
    public Members getMembersMemberId() {
        return membersMemberId;
    }

    public void setMembersMemberId(Members membersMemberId) {
        this.membersMemberId = membersMemberId;
    }
    
    
    @JoinColumn(name = "ROLES_ROLE_ID")
    public long getRolesRoleId() {
        return rolesRoleId;
    }

    public void setRolesRoleId(long rolesRoleId) {
        this.rolesRoleId = rolesRoleId;
    }
    
    
}
