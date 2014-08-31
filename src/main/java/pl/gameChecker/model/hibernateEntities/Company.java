/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */

@Entity(name = "COMPANIES")
@Table(name = "COMPANIES")
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMPANY_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "COMPANY_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @OneToMany(mappedBy = "company")
    protected List<MembersCPU> membersCPUs;
    
    @OneToMany(mappedBy = "company")
    protected List<MembersGPU> membersGPUs;

    public Company() {}

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
