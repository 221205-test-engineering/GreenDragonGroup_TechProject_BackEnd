package com.uni.services;

import com.uni.daos.GameDAO;
import com.uni.daos.SeasonDAO;
import com.uni.daos.VenueDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;

import java.util.List;

public class SchedulingServiceImpl implements SchedulingService{

    private VenueDAO venueDAO;
    private GameDAO gameDAO;
    private SeasonDAO seasonDAO;

    public SchedulingServiceImpl(VenueDAO venueDAO, GameDAO gameDAO, SeasonDAO seasonDAO){
        this.venueDAO = venueDAO;
        this.gameDAO = gameDAO;
        this.seasonDAO = seasonDAO;
    }

    @Override
    public List<Venue> getAllVenues() {
        return this.venueDAO.findAll();
    }

    @Override
    public List<Game> getAllGames() {
        return this.gameDAO.findAll();
    }

    @Override
    public List<Season> getAllSeasons() {
        return this.seasonDAO.findAll();
    }

    @Override
    public Game scheduleGame(Game game) {
        return this.gameDAO.save(game);
    }

    @Override
    public Season scheduleSeason(Season incomingSeason) {
        return this.seasonDAO.save(incomingSeason);
    }
}
