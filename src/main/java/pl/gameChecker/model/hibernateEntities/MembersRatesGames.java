/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.gameChecker.model.hibernateEntities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Damian Leśniak
 * @version 1.0
 */
@Entity
@Table(name = "MEMBERS_RATES_GAMES")
public class MembersRatesGames implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBERS_RATES_GAMES_ID")
    protected int id;
    
    @Column(name = "MEMBERS_RATES_GAMES_IS_RATED")
    protected boolean isRated;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_RATES_GAMES_MEMBER_ID")
    protected Member member;
    
    @ManyToOne
    @JoinColumn(name = "MEMBERS_RATES_GAMES_GAME_ID")
    protected Game game;

    public MembersRatesGames(boolean isRated, Member member, Game game) {
        this.isRated = isRated;
        this.member = member;
        this.game = game;
    }

    public MembersRatesGames() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIsRated() {
        return isRated;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    
    
}
