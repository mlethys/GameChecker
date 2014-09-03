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
@Entity(name = "LIBRARIES")
@Table(name = "LIBRARIES")
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LIBRARY_ID", unique = true, nullable = false)
    private int id;
    
    @OneToOne(mappedBy = "library")
    protected Member member;
    
    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    protected List<GamesLibraries> gamesLibraries;

    public Library(){}

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

    public List<GamesLibraries> getGamesLibraries() {
        return gamesLibraries;
    }

    public void setGamesLibraries(List<GamesLibraries> gamesLibraries) {
        this.gamesLibraries = gamesLibraries;
    }

    
}
