/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
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
    protected double stars;
    
    @Column(name = "GAME_RATES", nullable = false)
    protected int rates;
    
    @Column(name = "GAME_DESCRIPTION")
    protected String description;
    
    @Column(name = "GAME_POPULARITY", nullable = false, length = 3)
    protected int popularity;
    
    @OneToMany(mappedBy = "game")
    protected List<GamesLibraries> gamesLibraries;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "GAMETYPES_GAMETYPE_ID", nullable = false)
    protected Gametype gametype;

    public Game() {}
    
    @Deprecated
    public Game(String name, Date releaseDate, boolean singleplayer, boolean multiplayer, boolean freeToPlay, Gametype gametype) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.gametype = gametype;
        this.stars = 0;
        this.rates = 0;
        this.popularity = 0;
    }
    
    @Deprecated
    public Game(String name, boolean singleplayer, boolean multiplayer, boolean freeToPlay, int releaseDay, int releaseMonth, int releaseYear, Gametype gametype) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        this.name = name;
        this.releaseDate = new Date(secondsSinceEpoch);
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.gametype = gametype;
        this.stars = 0;
        this.rates = 0;
        this.popularity = 0;
    } 
    
    public Game(String name, Date releaseDate, boolean singleplayer, boolean multiplayer, boolean freeToPlay, String description, Gametype gametype) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.gametype = gametype;
        this.stars = 0;
        this.rates = 0;
        this.description = description;
        this.popularity = 0;
    }
    
    public Game(String name, boolean singleplayer, boolean multiplayer, boolean freeToPlay, int releaseDay, int releaseMonth, int releaseYear, String description, Gametype gametype) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        this.name = name;
        this.releaseDate = new Date(secondsSinceEpoch);
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.gametype = gametype;
        this.stars = 0;
        this.rates = 0;
        this.description = description;
        this.popularity = 0;
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

    public double getStars() {
        return stars;
    }

    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getRates() {
        return rates;
    }

    public void setRates(int rates) {
        this.rates = rates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }
    
    
}
