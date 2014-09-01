package pl.gameChecker.model.hibernateEntities;

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
    @Column(name="MEMBERS_PC_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "MEMBERS_PC_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @Column(name="MEMBERS_PC_MEMORY", nullable = false)
    protected int memory;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_MEMBER_ID", nullable = false)
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_CPUS_ID", nullable = false)
    protected MembersCPU membersCPU;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_GPUS_ID", nullable = false)
    protected MembersGPU membersGPU;

    public MembersPC() {}

    public MembersPC(Member member, String name, int memory, MembersCPU membersCPU, MembersGPU membersGPU) {
        this.member = member;
        this.name = name;
        this.memory = memory;
        this.membersCPU = membersCPU;
        this.membersGPU = membersGPU;
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

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public MembersCPU getMembersCPU() {
        return membersCPU;
    }

    public void setMembersCPU(MembersCPU membersCPU) {
        this.membersCPU = membersCPU;
    }

    public MembersGPU getMembersGPU() {
        return membersGPU;
    }

    public void setMembersGPU(MembersGPU membersGPU) {
        this.membersGPU = membersGPU;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    
}
