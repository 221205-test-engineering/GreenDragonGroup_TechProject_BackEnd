package com.uni.controllers;

import com.uni.entities.GameRequest;
import com.uni.services.GameRequestService;
import io.javalin.http.Context;

public class GameRequestController {
    private GameRequestService gameRequestService;

    public GameRequestController(GameRequestService gameRequestService) { this.gameRequestService = gameRequestService; }

    public void createGameRequest(Context context){
        GameRequest gameRequest = context.bodyAsClass(GameRequest.class);
        context.status(201);
        GameRequest savedGameRequest = gameRequestService.createRequest(gameRequest);
        context.json(savedGameRequest);
    }

    public void deleteGameRequest(Context context){
        GameRequest gameRequest = context.bodyAsClass(GameRequest.class);
        if (gameRequestService.deleteRequest(gameRequest.getGameId(), gameRequest.getUserId())) {
            context.status(204);
        } else {
            context.status(404);
        }
    }
    public void retrieveAllRefereeAndGames(Context context) { context.json(gameRequestService.getAllGamesAndReferees()); }
}
