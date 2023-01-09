package com.uni.controllers;

import com.uni.dtos.PlayerCard;
import com.uni.entities.StatBasketball;
import com.uni.services.StatisticsService;
import io.javalin.http.Context;

public class StatisticsController {

    private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public void getPLayerCardById(Context context){
        int id = Integer.parseInt(context.pathParam("id"));
        PlayerCard playerCard = this.statisticsService.getPlayerCardByUserId(id);
        context.json(playerCard);
    }

    public void getAllBasketballStatsByGameId(Context context) {
        int gameId = Integer.parseInt(context.pathParam("gameid"));

        context.json(statisticsService.getAllBasketballStatsByGameId(gameId));
    }

    public void addOrUpdateBasketballStat(Context context) {
        StatBasketball stat = context.bodyAsClass(StatBasketball.class);

        context.json(statisticsService.addOrUpdateBasketballStat(stat));
    }


}
