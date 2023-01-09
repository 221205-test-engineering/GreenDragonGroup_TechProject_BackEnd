package com.uni.services;

import com.uni.daos.TeamDAO;
import com.uni.daos.TeamRequestDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.PasswordMismatchException;
import com.uni.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class RegistrationServiceImpl implements RegistrationService{

    private TeamDAO teamDAO;
    private UserDAO userDao;
    private TeamRequestDAO teamRequestDAO;

    public RegistrationServiceImpl(TeamDAO teamDAO, UserDAO userDao, TeamRequestDAO teamRequestDAO) {
        this.teamDAO = teamDAO;
        this.userDao = userDao;
        this.teamRequestDAO = teamRequestDAO;
    }

    /*
        Team
    */
    @Override
    public Team registerTeam(Team team) {
        return this.teamDAO.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return this.teamDAO.findAll();
    }

    /*
        User
     */
    @Override
    public ImUser getUserFromLoginCredentials(LoginCredentials loginCredentials) {
        ImUser imUser = this.userDao.getByUsername(loginCredentials.getUsername());

        if(!imUser.getPassword().equals(loginCredentials.getPassword())){
            throw new PasswordMismatchException();
        }
        return imUser;
    }

    @Override
    public ImUser registerUser(ImUser registrationInfo) {
        registrationInfo.setRole("player");
        ImUser user = userDao.save(registrationInfo);

        return user;
    }

    @Override
    public ImUser updateUser(ImUser updateInfo) {
        userDao.update(updateInfo);

        return updateInfo;
    }

    @Override
    public void updateRole(int id, String role) {
        userDao.updateRole(id, role);
    }

    @Override
    public List<TeamRequest> filterTeamRequestsByPlayer(int playerId) {
        return this.teamRequestDAO.findAll().stream().filter(t -> t.getRequesterId() == playerId).collect(Collectors.toList());
    }

    @Override
    public Team getTeamByTeamName(String teamName) {
        return this.teamDAO.findAll().stream().filter(t -> t.getName().equals(teamName)).findFirst().get();
    }

    @Override
    public List<ImUser> retrievePlayersByTeam(String teamName) {
        return this.userDao.retrieveUserByTeam(teamName);
    }

    @Override
    public List<ImUser> retrieveAllUsers() {
        return this.userDao.findAll();
    }

    /*
        Team Requests
     */
    @Override
    public List<TeamRequest> getAllTeamRequests() {
        return this.teamRequestDAO.findAll();
    }

    @Override
    public List<TeamRequest> filterTeamRequestsByTeam(String team) {
        return this.teamRequestDAO.findAll().stream().filter(t -> t.getTeamName().equals(team)).collect(Collectors.toList());
    }

    @Override
    public TeamRequest createRequest(TeamRequest teamRequest) {
        teamRequest.setTeamRequestStatus("pending");
        return this.teamRequestDAO.save(teamRequest);
    }

    @Override
    public TeamRequest approveRequest(int requestId) {

        TeamRequest teamRequest = null;

        for(TeamRequest t : teamRequestDAO.findAll()){
            if(t.getTeamRequestId() == requestId){
                teamRequest = t;
            }
        }
        if(teamRequest == null){
            throw new ResourceNotFoundException(requestId, TeamRequest.class);
        }
        teamRequest.setTeamRequestStatus("accepted");
        teamRequestDAO.update(teamRequest);

        return teamRequest;
    }

    @Override
    public TeamRequest denyRequest(int requestId) {
        TeamRequest teamRequest = null;

        for(TeamRequest t : teamRequestDAO.findAll()){
            if(t.getTeamRequestId() == requestId){
                teamRequest = t;
            }
        }
        if(teamRequest == null){
            throw new ResourceNotFoundException(requestId, TeamRequest.class);
        }
        teamRequest.setTeamRequestStatus("denied");
        teamRequestDAO.update(teamRequest);

        return teamRequest;
    }

}
