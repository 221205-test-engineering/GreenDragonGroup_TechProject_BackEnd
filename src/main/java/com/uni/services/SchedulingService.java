package com.uni.services;

import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.entities.Venue;

import java.util.List;

public interface SchedulingService {

    List<Venue> getAllVenues();
    List<Game> getAllGames();
    List<Season> getAllSeasons();
    Game scheduleGame(Game game);
    Season scheduleSeason(Season incomingSeason);
}
