package com.uni.app;

import com.uni.controllers.*;
import com.uni.daos.*;
import com.uni.services.*;
import io.javalin.Javalin;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
        });


        //DAOs
        GameDAO gameDAO = GameDAO.getSingleton();
        SeasonDAO seasonDAO = SeasonDAO.getSingleton();
        TeamDAO teamDAO = TeamDAO.getSingleton();
        TeamRequestDAO teamRequestDAO = TeamRequestDAO.getSingleton();
        UserDAO userDAO = UserDAO.getSingleton();
        VenueDAO venueDAO = VenueDAO.getSingleton();
        StatBasketballDAO statBasketballDAO = StatBasketballDAO.getSingleton();
        GameRequestDAO gameRequestDAO = GameRequestDAO.getSingleton();

        //Services
        RegistrationService registrationService = new RegistrationServiceImpl(teamDAO,userDAO,teamRequestDAO);
        SchedulingService schedulingService = new SchedulingServiceImpl(venueDAO,gameDAO,seasonDAO);
        StatisticsService statisticsService = new StatisticsServiceImpl(statBasketballDAO,userDAO);
        GameRequestService gameRequestService = new GameRequestImpl(gameRequestDAO);

        //Controllers
        SchedulingController schedulingController = new SchedulingController(schedulingService);
        TeamController teamController = new TeamController(registrationService);
        UserController userController = new UserController(registrationService);
        TeamRequestController teamRequestController = new TeamRequestController(registrationService);
        StatisticsController statisticsController = new StatisticsController(statisticsService);
        GameRequestController gameRequestController = new GameRequestController(gameRequestService);

        app.post("/login", userController::login);
        app.post("/logout", userController::logout);
        app.post("/users", userController::register);
        app.put("/users", userController::update);
        app.get("/users", userController::retrieveAllUsers);
        app.patch("/users/{id}/role", userController::updateRole);
        app.get("/teams/{teamname}/users", userController::retrievePlayersByTeam);

        app.post("/teams", teamController::registerTeam);
        app.get("/teams", teamController::retrieveAllTeams);
        app.get("/teams/{teamname}", teamController::retrieveTeamByTeamName);

        app.get("/venues", schedulingController::getAllVenues);

        app.post("/games", schedulingController::scheduleGame);
        app.get("/games", schedulingController::getAllGames);

        app.get("/seasons", schedulingController::getAllSeasons);
        app.post("/seasons", schedulingController::scheduleSeason);

        app.get("/teamrequests", teamRequestController::getAllTeamRequests);
        app.post("/teamrequests", teamRequestController::createTeamRequest);
        app.patch("/teamrequests/{id}/approve", teamRequestController::approveRequest);
        app.patch("/teamrequests/{id}/deny", teamRequestController::denyRequest);

        app.get("/playercards/{id}", statisticsController::getPLayerCardById);
        app.get("/games/{gameid}/statbasketball", statisticsController::getAllBasketballStatsByGameId);
        app.post("/statbasketball", statisticsController::addOrUpdateBasketballStat);

        app.get("/referee-and-games-lookup", gameRequestController::retrieveAllRefereeAndGames);
        app.post("/game-requests/apply", gameRequestController::createGameRequest);
        app.delete("/game-requests/delete", gameRequestController::deleteGameRequest);

        app.start(7000);

    }
}
