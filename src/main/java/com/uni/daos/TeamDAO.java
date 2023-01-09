package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.TeamCreationException;
import com.uni.entities.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO implements CrudDAO<Team> {

    private static TeamDAO teamDAO = null;

    public static TeamDAO getSingleton(){

        if(teamDAO == null){
            teamDAO = new TeamDAO();
        }

        return teamDAO;
    }

    private TeamDAO() {}


    @Override
    public Team save(Team team) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "insert into team values (?,?,?::team_status,?::sport)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,team.getName());
            ps.setInt(2,team.getCaptain());
            ps.setString(3,team.getTeamStatus());
            ps.setString(4,team.getSport());
            ps.executeUpdate();
            return team;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new TeamCreationException();
        }
    }

    @Override
    public List<Team> findAll() {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from team";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Team> teams = new ArrayList();

            while(rs.next()){
                Team team = new Team();
                team.setName(rs.getString("name"));
                team.setCaptain(rs.getInt("captain"));
                team.setTeamStatus(rs.getString("team_status"));
                team.setSport(rs.getString("sport"));
                teams.add(team);
            }

            return teams;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void update(Team team) {

    }
}
