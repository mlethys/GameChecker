/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import java.sql.Date;
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
    protected int id;
    
    @Column(name = "GAMES_LIBRARIES_ADDITION_DATE")
    protected Date additionDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="GAMES_GAME_ID", nullable = false)
    protected Game game;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="LIBRARIES_LIBRARY_ID", nullable = false)
    protected Library library;
    
    public GamesLibraries() {}
    
    public GamesLibraries(Game game, Library library, Date additionDate) {
        this.game = game;
        this.library = library;
        this.additionDate = additionDate;
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

    public Date getAdditionDate() {
        return additionDate;
    }

    public void setAdditionDate(Date additionDate) {
        this.additionDate = additionDate;
    }
}
