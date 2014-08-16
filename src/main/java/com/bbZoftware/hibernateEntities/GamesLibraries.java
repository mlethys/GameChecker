/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bbZoftware.hibernateEntities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name = "GAMES_LIBRARIES")
@Table(name = "GAMES_LIBRARIES")
public class GamesLibraries implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAMES_LIBRARIES_ID", unique = true, nullable = false)
    private int id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="GAMES_GAME_ID", nullable = false)
    protected Game game;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="LIBRARIES_LIBRARY_ID", nullable = false)
    protected Library library;
    
    public GamesLibraries() {}
    
    public GamesLibraries(Game game, Library library) {
        this.game = game;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
    
    
}
