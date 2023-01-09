package com.uni.dtos;

import com.uni.entities.StatBasketball;

import java.util.List;

public class PlayerCard {

    private int id;
    private String username;
    private List<StatBasketball> basketballStats;
    private int heightInches;
    private int weightLbs;
    private String profilePic;
    private boolean hideBiometrics;

    public PlayerCard() {
    }

    public PlayerCard(int id, String username, List<StatBasketball> basketballStats, int heightInches, int weightLbs, String profilePic, boolean hideBiometrics) {
        this.id = id;
        this.username = username;
        this.basketballStats = basketballStats;
        this.heightInches = heightInches;
        this.weightLbs = weightLbs;
        this.profilePic = profilePic;
        this.hideBiometrics = hideBiometrics;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<StatBasketball> getBasketballStats() {
        return basketballStats;
    }

    public void setBasketballStats(List<StatBasketball> basketballStats) {
        this.basketballStats = basketballStats;
    }

    public int getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
    }

    public int getWeightLbs() {
        return weightLbs;
    }

    public void setWeightLbs(int weightLbs) {
        this.weightLbs = weightLbs;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public boolean isHideBiometrics() {
        return hideBiometrics;
    }

    public void setHideBiometrics(boolean hideBiometrics) {
        this.hideBiometrics = hideBiometrics;
    }
}
