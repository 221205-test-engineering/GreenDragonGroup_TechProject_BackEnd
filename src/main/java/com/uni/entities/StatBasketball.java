package com.uni.entities;

public class StatBasketball {

    private int statBasketballId;
    private int userId;
    private int gameId;
    private String teamName;

    private int points;
    private int rebounds;
    private int assists;
    private int fouls;

    public StatBasketball() {
    }

    public StatBasketball(int statBasketballId, int userId, int gameId, String teamName, int points, int rebounds, int assists, int fouls) {
        this.statBasketballId = statBasketballId;
        this.userId = userId;
        this.gameId = gameId;
        this.teamName = teamName;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.fouls = fouls;
    }

    public int getStatBasketballId() {
        return statBasketballId;
    }

    public void setStatBasketballId(int statBasketballId) {
        this.statBasketballId = statBasketballId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }
}
