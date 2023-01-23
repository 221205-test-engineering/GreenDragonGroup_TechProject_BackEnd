package dev.greendragon;

import com.uni.daos.TeamDAO;
import com.uni.daos.TeamRequestDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.LoginCredentials;
import com.uni.entities.ImUser;
import com.uni.entities.Team;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.PasswordMismatchException;
import com.uni.exceptions.ResourceNotFoundException;
import com.uni.exceptions.TeamCreationException;
import com.uni.services.RegistrationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class RegistrationServiceTest {

    public static TeamDAO teamDAO;
    public static TeamRequestDAO teamRequestDAO;
    public static UserDAO userDAO;

    public static RegistrationServiceImpl registrationService;

    @BeforeEach
    public void setup(){
        teamDAO = mock(TeamDAO.class);
        teamRequestDAO = mock(TeamRequestDAO.class);
        userDAO = mock(UserDAO.class);
        registrationService = new RegistrationServiceImpl(teamDAO, userDAO, teamRequestDAO);
    }

    @Test
    public void registerTeam() {
        registrationService.registerTeam(new Team());
        verify(teamDAO, times(1)).save(any(Team.class));
    }

    @Test
    public void registerTeamNegativeTest() {
        when(teamDAO.save(any(Team.class))).thenThrow(TeamCreationException.class);
        try {
            registrationService.registerTeam(new Team());
            Assertions.fail("Should not have run without an exception being thrown");
        }catch(TeamCreationException e){
            verify(teamDAO, times(1)).save(any(Team.class));
        }
    }

    @Test
    public void getAllTeams(){
        registrationService.getAllTeams();
        verify(teamDAO).findAll();
    }

    @Test
    public void getAllTeamRequests(){
        registrationService.getAllTeamRequests();
        verify(teamRequestDAO).findAll();
    }

    @Test
    public void registerUser(){
        registrationService.registerUser(new ImUser());
        verify(userDAO).save(any(ImUser.class));
    }

    @Test
    public void getUserFromLoginCredentials(){
        LoginCredentials login = new LoginCredentials("user", "pass");
        ImUser fakeUser = new ImUser(1,"user", "pass", "troll", 1, 1, "", false);
        ImUser returnedUser = new ImUser();
        when(userDAO.getByUsername(anyString())).thenReturn(fakeUser);
        try {
            returnedUser = registrationService.getUserFromLoginCredentials(login);
        } catch(NullPointerException e){
            e.printStackTrace();
        }
        verify(userDAO).getByUsername(anyString());
        Assertions.assertEquals(fakeUser, returnedUser);
    }

    @Test
    public void getUserFromLoginCredentialsIncorrectPassword(){
        LoginCredentials login = new LoginCredentials("user", "passport");
        ImUser fakeUser = new ImUser(1,"user", "pass", "troll", 1, 1, "", false);
        ImUser returnedUser = new ImUser();
        when(userDAO.getByUsername(anyString())).thenReturn(fakeUser);
        try {
            returnedUser = registrationService.getUserFromLoginCredentials(login);
            Assertions.fail("Should have created a password mismatch exception");
        } catch(PasswordMismatchException e){
            verify(userDAO).getByUsername(anyString());
            Assertions.assertNotEquals(fakeUser, returnedUser);
        }
    }


    @Test
    public void approveRequest(){
        List<TeamRequest> a = List.of(new TeamRequest(1,"fakeTeam", 3, "status"));
        when(teamRequestDAO.findAll()).thenReturn(a);
        try {
            registrationService.approveRequest(1);
        }catch(ResourceNotFoundException e){
            Assertions.fail("Request not found");
        }
        verify(teamRequestDAO).findAll();
        verify(teamRequestDAO).update(any());
    }

    @Test
    public void approveNullRequest(){
        List<TeamRequest> a = List.of(new TeamRequest(1,"fakeTeam", 3, "status"));
        when(teamRequestDAO.findAll()).thenReturn(a);
        try {
            registrationService.approveRequest(99);
        }catch(ResourceNotFoundException e){
            verify(teamRequestDAO).findAll();
        }
    }

    @Test
    public void denyRequest(){
        List<TeamRequest> a = List.of(new TeamRequest(1,"fakeTeam", 3, "status"));
        when(teamRequestDAO.findAll()).thenReturn(a);
        try {
            registrationService.denyRequest(1);
        }catch(ResourceNotFoundException e){
            Assertions.fail("Request not found");
        }
        verify(teamRequestDAO).findAll();
        verify(teamRequestDAO).update(any());
    }

    @Test
    public void denyNullRequest(){
        List<TeamRequest> a = List.of(new TeamRequest(1,"fakeTeam", 3, "status"));
        when(teamRequestDAO.findAll()).thenReturn(a);
        try {
            registrationService.denyRequest(99);
        }catch(ResourceNotFoundException e){
            verify(teamRequestDAO).findAll();
        }
    }

    @Test
    public void filterTeamRequestByTeam(){
        TeamRequest t = mock(TeamRequest.class);
        when(t.getTeamName()).thenReturn("fakeTeam"); //changing to non matching string with filterBy string produces array size 0 and that produces failed assertion
        List<TeamRequest> a = List.of(t);
        when(teamRequestDAO.findAll()).thenReturn(a);
        List<TeamRequest> results = registrationService.filterTeamRequestsByTeam("fakeTeam");
        verify(t).getTeamName();
        verify(teamRequestDAO).findAll();
        Assertions.assertEquals(1, results.size());
    }

    @Test
    public void createRequest(){
        registrationService.createRequest(new TeamRequest());
        verify(teamRequestDAO).save(any(TeamRequest.class));
    }

    @Test
    public void retrieveAllUsers(){
        registrationService.retrieveAllUsers();
        verify(userDAO).findAll();
    }

    @Test
    public void retrievePlayersByTeam(){
        registrationService.retrievePlayersByTeam("teamName");
        verify(userDAO).retrieveUserByTeam(anyString());
    }

    @Test
    public void updateUser(){
        registrationService.updateUser(new ImUser());
        verify(userDAO).update(any(ImUser.class));
    }

    @Test
    public void updateRole() {
        registrationService.updateRole(1, "two");
        verify(userDAO).updateRole(anyInt(), anyString());
    }

    @Test
    public void filterTeamRequestsByPlayer() {
        TeamRequest m = mock(TeamRequest.class);
        when(m.getRequesterId()).thenReturn(99);
        List<TeamRequest> a = new ArrayList<>(){{
            add(m);
        }};
        when(teamRequestDAO.findAll()).thenReturn(a);
        List<TeamRequest> resultList = registrationService.filterTeamRequestsByPlayer(99);
        verify(m).getRequesterId();
        verify(teamRequestDAO).findAll();
        Assertions.assertEquals(1, resultList.size());

    }

    @Test
    public void getTeamByTeamName() {
        Team t = mock(Team.class);
        when(t.getName()).thenReturn("allStars");
        when(teamDAO.findAll()).thenReturn(
                new ArrayList<Team>(){{
                    add(t);
                }}
        );
        Team result = registrationService.getTeamByTeamName("allStars");
        verify(teamDAO).findAll();
        verify(t).getName();
        Assertions.assertEquals("allStars", result.getName());

    }
}
