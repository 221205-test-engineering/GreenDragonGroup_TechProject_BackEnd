package com.uni.entities;

public class GameRequest {
    private int gameRequestId;
    private int gameId;
    private int userId;

    private String venue;
    private String season;

    public GameRequest() {}

    public GameRequest(int gameRequestId, int gameId, int userId, String venue, String season){
        this.gameRequestId = gameRequestId;
        this.gameId = gameId;
        this.userId = userId;
        this.venue = venue;
        this.season = season;
    }

    public int getGameRequestId() { return gameRequestId; }

    public void setGameRequestId(int gameRequestId) { this.gameRequestId = gameRequestId; }

    public int getGameId() { return gameId; }

    public void setGameId(int gameId) { this.gameId = gameId; }

    public int getUserId() { return userId; }

    public void setUserId(int userId) { this.userId = userId; }

    public String getVenue() { return venue; }

    public void setVenue(String venue) { this.venue = venue; }

    public String getSeason() { return season; }

    public void setSeason(String season) { this.season = season; }

}
