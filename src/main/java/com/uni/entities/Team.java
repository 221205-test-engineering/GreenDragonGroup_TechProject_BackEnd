package com.uni.entities;

public class Team {

    private String name;
    private int captain;
    private String sport;
    private String teamStatus;

    public Team() {
    }

    public Team(String name, int captain, String sport, String teamStatus) {
        this.name = name;
        this.captain = captain;
        this.sport = sport;
        this.teamStatus = teamStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaptain() {
        return captain;
    }

    public void setCaptain(int captain) {
        this.captain = captain;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTeamStatus() {
        return teamStatus;
    }

    public void setTeamStatus(String teamStatus) {
        this.teamStatus = teamStatus;
    }
}
