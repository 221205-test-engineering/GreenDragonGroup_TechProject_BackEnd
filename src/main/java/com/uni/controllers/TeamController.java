package com.uni.controllers;


import com.uni.entities.Team;
import com.uni.services.RegistrationService;
import io.javalin.http.Context;


public class TeamController {

    private RegistrationService registrationService;

    public TeamController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void registerTeam(Context context){
        Team team = context.bodyAsClass(Team.class);
        registrationService.registerTeam(team);
        context.status(204);
    }

    public void retrieveAllTeams(Context context){
        context.json(registrationService.getAllTeams());
    }

    public void retrieveTeamByTeamName(Context context) {
        String teamName = context.pathParam("teamname");

        context.json(registrationService.getTeamByTeamName(teamName));
    }
}
