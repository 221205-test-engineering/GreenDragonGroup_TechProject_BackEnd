package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.entities.TeamRequest;
import com.uni.exceptions.DatabaseConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamRequestDAO implements CrudDAO<TeamRequest>{

    private static TeamRequestDAO teamRequestDAO = null;

    public static TeamRequestDAO getSingleton(){

        if(teamRequestDAO == null){
            teamRequestDAO = new TeamRequestDAO();
        }
        return teamRequestDAO;
    }

    private TeamRequestDAO() {}

    @Override
    public TeamRequest save(TeamRequest teamRequest) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "insert into team_requests values (default,?,?,?::team_request_status)";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,teamRequest.getTeamName());
            ps.setInt(2, teamRequest.getRequesterId());
            ps.setString(3,teamRequest.getTeamRequestStatus());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int key = rs.getInt("request_id");
            teamRequest.setTeamRequestId(key);

            return teamRequest;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }

    }

    @Override
    public List<TeamRequest> findAll() {
        try(Connection connection = ConnectionUtil.getConnection()){

            String sql = "select * from team_requests";

            List<TeamRequest> requests = new ArrayList();

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                TeamRequest request = new TeamRequest();
                request.setTeamRequestId(rs.getInt("request_id"));
                request.setTeamName(rs.getString("team"));
                request.setRequesterId(rs.getInt("user_id"));
                request.setTeamRequestStatus(rs.getString("status"));
                requests.add(request);
            }

            return requests;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void update(TeamRequest teamRequest) {

        try(Connection conn = ConnectionUtil.getConnection()){
            String sql  = "update team_requests set team=?, user_id=?, status=?::team_request_status where request_id =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, teamRequest.getTeamName());
            ps.setInt(2,teamRequest.getRequesterId());
            ps.setString(3,teamRequest.getTeamRequestStatus());
            ps.setInt(4, teamRequest.getTeamRequestId());
            ps.executeUpdate();


        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}

