/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity(name = "GAMES")
@Table(name = "GAMES")
public class Game implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAME_ID", unique = true, nullable = false)
    protected int id;
    
    @Column(name = "GAME_NAME", unique = true, nullable = false, length = 45)
    protected String name;
    
    @Column(name = "GAME_RELEASE_DATE", nullable = true)
    protected Date releaseDate;
    
    @Column(name = "GAME_SINGLEPLAYER", nullable = false)
    protected boolean singleplayer;
    
    @Column(name = "GAME_MULTIPLAYER", nullable = false)
    protected boolean multiplayer;
    
    @Column(name = "GAME_FREE", nullable = false)
    protected boolean freeToPlay;
    
    @Column(name = "GAME_STARS", nullable = false, length = 1)
    protected int stars;
    
    @OneToMany(mappedBy = "game")
    protected List<GamesLibraries> gamesLibraries;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "GAMETYPES_GAMETYPE_ID", nullable = false)
    protected Gametype gametype;

    public Game() {}
    
    public Game(String name, Date releaseDate, boolean singleplayer, boolean multiplayer, boolean freeToPlay, Gametype gametype) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.gametype = gametype;
        this.stars = 0;
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

    public boolean isSingleplayer() {
        return singleplayer;
    }

    public void setSingleplayer(boolean singleplayer) {
        this.singleplayer = singleplayer;
    }

    public boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public boolean isFreeToPlay() {
        return freeToPlay;
    }

    public void setFreeToPlay(boolean freeToPlay) {
        this.freeToPlay = freeToPlay;
    }

    public List<GamesLibraries> getGamesLibraries() {
        return gamesLibraries;
    }

    public void setGamesLibraries(List<GamesLibraries> gamesLibraries) {
        this.gamesLibraries = gamesLibraries;
    }

    public Gametype getGametype() {
        return gametype;
    }

    public void setGametype(Gametype gametype) {
        this.gametype = gametype;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
