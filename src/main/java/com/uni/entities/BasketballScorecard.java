package com.uni.entities;

import java.util.List;

public class BasketballScorecard {

    private List<StatBasketball> scores;

    public BasketballScorecard(List<StatBasketball> scores) {
        this.scores = scores;
    }

    public List<StatBasketball> getScores() {
        return scores;
    }

    public void setScores(List<StatBasketball> scores) {
        this.scores = scores;
    }
}
