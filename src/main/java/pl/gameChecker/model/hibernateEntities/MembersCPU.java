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
@Entity(name = "MEMBERS_CPUS")
@Table(name = "MEMBERS_CPUS")
public class MembersCPU implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBERS_CPU_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "MEMBERS_CPU_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @Column(name = "MEMBERS_CPU_RELEASE_DATE", nullable = false)
    protected Date releaseDate;
    
    @ManyToOne
    @JoinColumn(name = "COMPANIES_COMPANY_ID", nullable = false)
    protected Company company;
    
    @OneToMany(mappedBy = "membersCPU")
    protected List<MembersPC> membersPCs;

    public MembersCPU() {}

    public MembersCPU(String name, Date releaseDate, Company company) {
        this.name = name;
        this.releaseDate = releaseDate;
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
