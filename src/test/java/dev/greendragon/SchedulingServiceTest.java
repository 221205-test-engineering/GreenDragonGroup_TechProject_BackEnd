package dev.greendragon;

import com.uni.daos.GameDAO;
import com.uni.daos.VenueDAO;
import com.uni.daos.SeasonDAO;
import com.uni.entities.Game;
import com.uni.entities.Season;
import com.uni.services.SchedulingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class SchedulingServiceTest
{
	public static GameDAO gameDAO;
	public static SeasonDAO seasonDAO;
	public static VenueDAO venueDAO;

	public static SchedulingServiceImpl service;

	@BeforeEach
	public void setup()
	{
		gameDAO = mock(GameDAO.class);
		seasonDAO = mock(SeasonDAO.class);
		venueDAO = mock(VenueDAO.class);
		service = new SchedulingServiceImpl(venueDAO, gameDAO, seasonDAO);
	}

	@Test
	public void getAllGames()
	{
		service.getAllGames();
		verify(gameDAO, times(1)).findAll();
	}

	@Test
	public void getAllVenues()
	{
		service.getAllVenues();
		verify(venueDAO, times(1)).findAll();
	}

	@Test
	public void getAllSeasons()
	{
		service.getAllSeasons();
		verify(seasonDAO, times(1)).findAll();
	}

	@Test
	public void scheduleGame()
	{
		service.scheduleGame(new Game());
		verify(gameDAO, times(1)).save(any(Game.class));
	}

	@Test
	public void scheduleSeason()
	{
		service.scheduleSeason(new Season());
		verify(seasonDAO, times(1)).save(any(Season.class));
	}
}
