package com.uni.daos;

import com.uni.datautils.ConnectionUtil;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.exceptions.SeasonCreationException;
import com.uni.entities.Venue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO implements CrudDAO<Venue> {

    private static VenueDAO venueDAO = null;

    public static VenueDAO getSingleton(){

        if(venueDAO == null){
            venueDAO = new VenueDAO();
        }
        return venueDAO;
    }

    private VenueDAO() {}

    @Override
    public Venue save(Venue venue) {
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "insert into venue (title) values (?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, venue.getTitle());

            ps.executeUpdate();

            return venue;
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new SeasonCreationException();
        }
    }

    @Override
    public List<Venue> findAll() {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from venue";
            List<Venue> venues = new ArrayList();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Venue venue = new Venue(rs.getString("title"));
                venues.add(venue);
            }

            return venues;

        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DatabaseConnectionException();
        }
    }

    @Override
    public void update(Venue venue) {

    }
}
