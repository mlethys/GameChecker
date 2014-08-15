package com.bbZoftware.hibernateEntities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name = "MEMBERS_PCS")
@Table(name = "MEMBERS_PCS")
public class MembersPC implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBERS_PC_ID")
    protected int id;
    
    @Column(name = "MEMBERS_PC_NAME")
    protected String name;
    
    @Column(name="MEMBERS_PC_MEMORY")
    protected int memory;
    
    @OneToMany(mappedBy = "membersPC")
    protected List<MembersCPU> membersCPUs;
    
    @OneToMany(mappedBy = "membersPC")
    protected List<MembersGPU> membersGPUs;

    public MembersPC() {}

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

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public List<MembersCPU> getMembersCPUs() {
        return membersCPUs;
    }

    public void setMembersCPUs(List<MembersCPU> membersCPUs) {
        this.membersCPUs = membersCPUs;
    }

    public List<MembersGPU> getMembersGPUs() {
        return membersGPUs;
    }

    public void setMembersGPUs(List<MembersGPU> membersGPUs) {
        this.membersGPUs = membersGPUs;
    }
    
    
}
