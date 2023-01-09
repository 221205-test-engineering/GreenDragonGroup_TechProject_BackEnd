package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.SeasonCreationException;
import com.uni.entities.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeasonDAO implements CrudDAO<Season> {

    private static SeasonDAO seasonDAO = null;

    public static SeasonDAO getSingleton(){

        if(seasonDAO == null){
            seasonDAO = new SeasonDAO();
        }
        return seasonDAO;
    }

    private SeasonDAO() {}

    @Override
    public Season save(Season season) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "insert into season (title) values (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, season.getTitle());

            ps.executeUpdate();

            return season;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new SeasonCreationException();
        }
    }

    @Override
    public List<Season> findAll(){

        try(Connection conn = ConnectionUtil.getConnection()) {

            String sql = "select * from season";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Season> seasons = new ArrayList();

            while (rs.next()){
                Season season = new Season(rs.getString("title"));
                seasons.add(season);
            }
            return seasons;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }

    }

    @Override
    public void update(Season season) {

    }
}
