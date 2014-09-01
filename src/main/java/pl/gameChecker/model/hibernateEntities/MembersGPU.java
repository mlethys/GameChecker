package pl.gameChecker.model.hibernateEntities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name = "MEMBERS_GPUS")
@Table(name="MEMBERS_GPUS")
public class MembersGPU implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBERS_GPU_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "MEMBERS_GPU_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @Column(name = "MEMBERS_GPU_RELEASE_DATE", nullable = false)
    protected Date releaseDate;
    
    @Column(name = "MEMBERS_GPU_MEMORY", nullable = false)
    protected int memory;
    
    @ManyToOne
    @JoinColumn(name = "COMPANIES_COMPANY_ID", nullable = false)
    protected Company company;
    
    @OneToMany(mappedBy = "membersGPU")
    protected List<MembersPC> membersPCs;

    public MembersGPU() {}

    public MembersGPU(String name, Date releaseDate, int memory, Company company) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.memory = memory;
        this.company = company;
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

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<MembersPC> getMembersPCs() {
        return membersPCs;
    }

    public void setMembersPCs(List<MembersPC> membersPCs) {
        this.membersPCs = membersPCs;
    }


    
}
