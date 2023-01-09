package com.uni.services;

import com.uni.dtos.PlayerCard;
import com.uni.entities.StatBasketball;

import java.util.List;

public interface StatisticsService {

    PlayerCard getPlayerCardByUserId(int id);

    List<StatBasketball> getAllBasketballStatsByGameId(int id);

    StatBasketball addOrUpdateBasketballStat(StatBasketball stat);
}
