package com.uni.entities;

public class ImUser {

    private int userId;
    private String username;
    private String password;
    private String role;
    private int heightInches;
    private int weightLbs;
    private String profilePic;
    private boolean hideBiometrics;



    public ImUser(int userId, String username, String password, String role, int heightInches, int weightLbs, String profilePic, boolean hideBiometrics) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.heightInches = heightInches;
        this.weightLbs = weightLbs;
        this.profilePic = profilePic;
        this.hideBiometrics = hideBiometrics;
    }

    public ImUser() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "ImUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", heightInches=" + heightInches +
                ", weightLbs=" + weightLbs +
                ", profilePic='" + profilePic + '\'' +
                ", hideBiometrics=" + hideBiometrics +
                '}';
    }
}
