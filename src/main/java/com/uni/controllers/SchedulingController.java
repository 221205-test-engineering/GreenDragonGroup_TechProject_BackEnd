package com.uni.controllers;

import com.uni.daos.GameDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;
import com.uni.services.SchedulingService;
import com.uni.services.SchedulingServiceImpl;
import io.javalin.http.Context;
import io.javalin.plugin.openapi.annotations.*;

public class SchedulingController {


    private SchedulingService schedulingService = new SchedulingServiceImpl(VenueDAO.getSingleton(), GameDAO.getSingleton(), SeasonDAO.getSingleton());

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    public void getAllVenues(Context context){
        context.json(schedulingService.getAllVenues());
    }

    public void scheduleGame(Context context){
        Game incomingGame = context.bodyAsClass(Game.class);
        Game scheduledGame = schedulingService.scheduleGame(incomingGame);
        context.status(201);
        context.json(scheduledGame);
    }

    public void scheduleSeason(Context context) {
        Season incomingSeason = context.bodyAsClass(Season.class);
        Season scheduledSeason = schedulingService.scheduleSeason(incomingSeason);
        context.status(201);
        context.json(scheduledSeason);
    }

    public void getAllGames(Context context){
        context.json(schedulingService.getAllGames());
    }

    public void getAllSeasons(Context context){
        context.json(schedulingService.getAllSeasons());
    }
}
