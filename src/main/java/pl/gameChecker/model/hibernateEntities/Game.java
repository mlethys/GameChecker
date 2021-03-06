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
    
    @Column(name = "GAME_STARS", nullable = false)
    protected double stars;
    
    @Column(name = "GAME_RATES", nullable = false)
    protected int rates;
    
    @Column(name = "GAME_DESCRIPTION")
    protected String description;
    
    @Column(name = "GAME_MINIMAL_CPU_RELEASE_YEAR")
    protected int minimalCPUReleaseYear;
    
    @Column(name = "GAME_MINIMAL_GPU_RELEASE_YEAR")
    protected int minimalGPUReleaseYear;
    
    @Column(name = "GAME_MINIMAL_RAM")
    protected int minimalRam;
    
    @Column(name = "GAME_RECOMMENDED_CPU_RELEASE_YEAR")
    protected int recommendedCPUReleaseYear;
    
    @Column(name = "GAME_RECOMMENDED_GPU_RELEASE_YEAR")
    protected int recommendedGPUReleaseYear;
    
    @Column(name = "GAME_RECOMMENDED_RAM")
    protected int recommendedRam;
    
    @Column(name = "GAME_POPULARITY", nullable = false, length = 3)
    protected int popularity;
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    protected List<GamesLibraries> gamesLibraries;
    
    @ManyToOne
    @JoinColumn(name = "GAMETYPES_GAMETYPE_ID", nullable = false)
    protected Gametype gametype;
    
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    protected List<MembersRatesGames> membersRatesGames;

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

    public Game(String name, boolean singleplayer, boolean multiplayer, boolean freeToPlay, 
            int releaseDay, int releaseMonth, int releaseYear, String description, 
            int minimalCPUReleaseYear, int minimalGPUReleaseYear, int minimalRam, 
            int recommendedCPUReleaseYear, int recommendedGPUReleaseYear, int recommendedRam, Gametype gametype) {
        
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.clear();
        calendar.set(releaseYear, releaseMonth - 1, releaseDay);
        long secondsSinceEpoch = calendar.getTimeInMillis();
        
        this.name = name;
        this.releaseDate = new Date(secondsSinceEpoch);
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.description = description;
        this.minimalCPUReleaseYear = minimalCPUReleaseYear;
        this.minimalGPUReleaseYear = minimalGPUReleaseYear;
        this.minimalRam = minimalRam;
        this.recommendedCPUReleaseYear = recommendedCPUReleaseYear;
        this.recommendedGPUReleaseYear = recommendedGPUReleaseYear;
        this.recommendedRam = recommendedRam;
        this.gametype = gametype;
        
        this.stars = 0;
        this.rates = 0;
        this.popularity = 0;
    }
    
    public Game(String name, Date releaseDate, boolean singleplayer, boolean multiplayer, boolean freeToPlay, String description, 
            int minimalCPUReleaseYear, int minimalGPUReleaseYear, int minimalRam, 
            int recommendedCPUReleaseYear, int recommendedGPUReleaseYear, int recommendedRam, Gametype gametype) {
        
        this.name = name;
        this.releaseDate = releaseDate;
        this.singleplayer = singleplayer;
        this.multiplayer = multiplayer;
        this.freeToPlay = freeToPlay;
        this.description = description;
        this.minimalCPUReleaseYear = minimalCPUReleaseYear;
        this.minimalGPUReleaseYear = minimalGPUReleaseYear;
        this.minimalRam = minimalRam;
        this.recommendedCPUReleaseYear = recommendedCPUReleaseYear;
        this.recommendedGPUReleaseYear = recommendedGPUReleaseYear;
        this.recommendedRam = recommendedRam;
        this.gametype = gametype;
        
        this.stars = 0;
        this.rates = 0;
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

    public List<MembersRatesGames> getMembersRatesGames() {
        return membersRatesGames;
    }

    public void setMembersRatesGames(List<MembersRatesGames> membersRatesGames) {
        this.membersRatesGames = membersRatesGames;
    }

    public int getMinimalCPUReleaseYear() {
        return minimalCPUReleaseYear;
    }

    public void setMinimalCPUReleaseYear(int minimalCPUReleaseYear) {
        this.minimalCPUReleaseYear = minimalCPUReleaseYear;
    }

    public int getMinimalGPUReleaseYear() {
        return minimalGPUReleaseYear;
    }

    public void setMinimalGPUReleaseYear(int minimalGPUReleaseYear) {
        this.minimalGPUReleaseYear = minimalGPUReleaseYear;
    }

    public int getMinimalRam() {
        return minimalRam;
    }

    public void setMinimalRam(int minimalRam) {
        this.minimalRam = minimalRam;
    }

    public int getRecommendedCPUReleaseYear() {
        return recommendedCPUReleaseYear;
    }

    public void setRecommendedCPUReleaseYear(int recommendedCPUReleaseYear) {
        this.recommendedCPUReleaseYear = recommendedCPUReleaseYear;
    }

    public int getRecommendedGPUReleaseYear() {
        return recommendedGPUReleaseYear;
    }

    public void setRecommendedGPUReleaseYear(int recommendedGPUReleaseYear) {
        this.recommendedGPUReleaseYear = recommendedGPUReleaseYear;
    }

    public int getRecommendedRam() {
        return recommendedRam;
    }

    public void setRecommendedRam(int recommendedRam) {
        this.recommendedRam = recommendedRam;
    }
    
    
}
