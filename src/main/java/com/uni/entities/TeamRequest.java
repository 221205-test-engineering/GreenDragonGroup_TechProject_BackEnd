package com.uni.entities;

public class TeamRequest {
    private int teamRequestId;
    private String teamName;
    private int requesterId;
    private String teamRequestStatus;

    public TeamRequest() {
    }

    public TeamRequest(int teamRequestId, String teamName, int requesterId, String teamRequestStatus) {
        this.teamRequestId = teamRequestId;
        this.teamName = teamName;
        this.requesterId = requesterId;
        this.teamRequestStatus = teamRequestStatus;
    }

    public int getTeamRequestId() {
        return teamRequestId;
    }

    public void setTeamRequestId(int teamRequestId) {
        this.teamRequestId = teamRequestId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(int requesterId) {
        this.requesterId = requesterId;
    }

    public String getTeamRequestStatus() {
        return teamRequestStatus;
    }

    public void setTeamRequestStatus(String teamRequestStatus) {
        this.teamRequestStatus = teamRequestStatus;
    }
}
