package com.uni.controllers;

import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.services.RegistrationService;
import io.javalin.http.Context;

import java.util.List;

public class UserController {

    private RegistrationService registrationService;

    public UserController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public void retrieveAllUsers(Context ctx) {
        List<ImUser> users = registrationService.retrieveAllUsers();
        ctx.json(users);
    }

    public void updateRole(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ImUser roleInformation = ctx.bodyAsClass(ImUser.class);
        String role = roleInformation.getRole();

        registrationService.updateRole(id, role);
        ctx.status(204);
    }

    public void register(Context ctx) {
        ImUser registrationInfo = ctx.bodyAsClass(ImUser.class);

        ImUser registeredUser = registrationService.registerUser(registrationInfo);

        ctx.sessionAttribute("user", registeredUser);
        ctx.status(201);
        ctx.json(registeredUser);
    }

    public void update(Context ctx) {
        ImUser updateInfo = ctx.bodyAsClass(ImUser.class);

        ImUser loggedInUser = ctx.sessionAttribute("user");

        updateInfo.setUserId(loggedInUser.getUserId());
        updateInfo.setRole(loggedInUser.getRole());

        ImUser user = registrationService.updateUser(updateInfo);
        ctx.json(user);
    }

    public void login(Context ctx){
        LoginCredentials credentials = ctx.bodyAsClass(LoginCredentials.class);
        ImUser user = registrationService.getUserFromLoginCredentials(credentials);
        ctx.sessionAttribute("user",user);
        ctx.json(user);
    };

    public void logout(Context ctx) {
        ctx.req.getSession().invalidate();
    }


    public void retrievePlayersByTeam(Context context) {
        String teamName = context.pathParam("teamname");

        context.json(registrationService.retrievePlayersByTeam(teamName));
    }
}
