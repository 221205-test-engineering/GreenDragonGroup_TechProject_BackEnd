package com.uni.entities;

public class Game {

    private int gameId;
    private String venueTitle;
    private String seasonTitle;
    private String homeTeam;
    private String awayTeam;
    private int homeScore;
    private int awayScore;
    private long gameStart;
    private String outcome;

    public Game() {
    }

    public Game(int gameId, String venueTitle, String seasonTitle, String homeTeam, String awayTeam, int homeScore, int awayScore, long gameStart, String outcome) {
        this.gameId = gameId;
        this.venueTitle = venueTitle;
        this.seasonTitle = seasonTitle;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.gameStart = gameStart;
        this.outcome = outcome;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getVenueTitle() {
        return venueTitle;
    }

    public void setVenueTitle(String venueTitle) {
        this.venueTitle = venueTitle;
    }

    public String getSeasonTitle() {
        return seasonTitle;
    }

    public void setSeasonTitle(String seasonTitle) {
        this.seasonTitle = seasonTitle;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public long getGameStart() {
        return gameStart;
    }

    public void setGameStart(long gameStart) {
        this.gameStart = gameStart;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }
}
