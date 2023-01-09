package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.entities.StatBasketball;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatBasketballDAO implements CrudDAO<StatBasketball>{

    private static StatBasketballDAO statBasketballDAO = null;

    public static StatBasketballDAO getSingleton(){

        if(statBasketballDAO == null){
            statBasketballDAO = new StatBasketballDAO();
        }

        return statBasketballDAO;
    }

    private StatBasketballDAO() {}




    @Override
    public StatBasketball save(StatBasketball statBasketball) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "insert into stat_basketball values (default,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, statBasketball.getUserId());
            ps.setInt(2, statBasketball.getGameId());
            ps.setString(3,statBasketball.getTeamName());
            ps.setInt(4,statBasketball.getPoints());
            ps.setInt(5,statBasketball.getRebounds());
            ps.setInt(6, statBasketball.getAssists());
            ps.setInt(7, statBasketball.getFouls());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();

            int id = rs.getInt("s_basketball_id");
            statBasketball.setStatBasketballId(id);
            return statBasketball;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }

    }

    @Override
    public List<StatBasketball> findAll() {

        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from stat_basketball";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<StatBasketball> stats = new ArrayList();

            while (rs.next()){
                StatBasketball stat = new StatBasketball();

                stat.setStatBasketballId(rs.getInt("s_basketball_id"));
                stat.setUserId(rs.getInt("user_id"));
                stat.setGameId(rs.getInt("game_id"));
                stat.setTeamName(rs.getString("team_name"));
                stat.setPoints(rs.getInt("points"));
                stat.setRebounds(rs.getInt("rebounds"));
                stat.setAssists(rs.getInt("assists"));
                stat.setFouls(rs.getInt("fouls"));

                stats.add(stat);
            }

            return stats;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void update(StatBasketball statBasketball) {

        try(Connection conn = ConnectionUtil.getConnection()){

            String sql = "update stat_basketball set user_id = ?, game_id = ?, team_name = ?, points = ?, rebounds = ?, assists = ?, fouls = ? where s_basketball_id = ? ";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, statBasketball.getUserId());
            ps.setInt(2,statBasketball.getGameId());
            ps.setString(3, statBasketball.getTeamName());
            ps.setInt(4, statBasketball.getPoints());
            ps.setInt(5, statBasketball.getRebounds());
            ps.setInt(6, statBasketball.getAssists());
            ps.setInt(7, statBasketball.getFouls());
            ps.setInt(8, statBasketball.getStatBasketballId());

            ps.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }

    }

    public List<StatBasketball> findAllByGameId(int gameId) {
        try(Connection connection = ConnectionUtil.getConnection()){
            String sql = "select * from stat_basketball where game_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, gameId);
            ResultSet rs = ps.executeQuery();

            List<StatBasketball> stats = new ArrayList();

            while (rs.next()){
                StatBasketball stat = new StatBasketball();

                stat.setStatBasketballId(rs.getInt("s_basketball_id"));
                stat.setUserId(rs.getInt("user_id"));
                stat.setGameId(rs.getInt("game_id"));
                stat.setTeamName(rs.getString("team_name"));
                stat.setPoints(rs.getInt("points"));
                stat.setRebounds(rs.getInt("rebounds"));
                stat.setAssists(rs.getInt("assists"));
                stat.setFouls(rs.getInt("fouls"));

                stats.add(stat);
            }

            return stats;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }
}
