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
@Entity(name="GAMETYPES")
@Table(name="GAMETYPES")
public class Gametype implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="GAMETYPE_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name="GAMETYPE_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @OneToMany(mappedBy = "gametype")
    protected List<Game> games;

    public Gametype() {}
    
    public Gametype(String name) {
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

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
    
    
}
