/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
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
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name="ROLES")
@Table(name="ROLES")
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROLE_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="ROLE_NAME", unique = true, nullable = false, length = 45)
    protected String name;

    @OneToMany(mappedBy = "role")
    protected List<Member> members;

    public Role(){}
    
    public Role(String name) {
        this.name = name;
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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
