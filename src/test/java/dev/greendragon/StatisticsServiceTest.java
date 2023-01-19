package dev.greendragon;

import com.uni.daos.StatBasketballDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.PlayerCard;
import com.uni.entities.ImUser;
import com.uni.entities.StatBasketball;
import com.uni.services.StatisticsService;
import com.uni.services.StatisticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.TooManyActualInvocations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class StatisticsServiceTest {
    private static  StatisticsServiceImpl statisticsService;
    private static StatBasketballDAO statBasketballDAO;
    private static UserDAO userDAO;

    List<StatBasketball> mockStatList = Stream.of(
            new StatBasketball(1, 1, 1, "Mock Team 1", 1, 1, 1,1),
            new StatBasketball(2, 2, 1, "Mock Team 2", 2, 2, 2,2),
            new StatBasketball(3, 3, 1, "Mock Team 3", 3, 3, 3,3)

    ).collect(Collectors.toList());

    @BeforeEach
    public void setup(){
        statBasketballDAO = mock(StatBasketballDAO.class);
        userDAO = mock(UserDAO.class);
        statisticsService = new StatisticsServiceImpl(statBasketballDAO, userDAO);
    }

    @DisplayName("Getting player card by user id")
    @Test
    public void getPlayerCardByUserId(){
        ImUser mockUser = new ImUser(1, "mockUser", "password", "player", 60, 180, "url", false);
        when(userDAO.findById(anyInt())).thenReturn(mockUser);
        PlayerCard playerCard = statisticsService.getPlayerCardByUserId(1);
        verify(userDAO, times(1)).findById(1);
        assertEquals("mockUser", playerCard.getUsername());
    }



    @DisplayName("Get all basketball stats by game id")
    @Test
    public void getAllBasketballStatsByGameId(){

        // create mock stat list


        // specify the mock behaviour
        when(statBasketballDAO.findAllByGameId(anyInt())).thenReturn(mockStatList);

        // calling the method in the service clas
        List<StatBasketball> statList = statisticsService.getAllBasketballStatsByGameId(1);

        //verifying if the method was invoked & if the result list is of correct size
        verify(statBasketballDAO, times(1)).findAllByGameId(1);
        assertEquals(3, statList.size());
    }

    @DisplayName("Update existing basketball stat")
    @Test
    public void updateBasketballStat(){
        // if stat exist, statBasketballDAO.update(existingStat) will be called


        StatBasketball statUpdate = new StatBasketball(3, 3, 1, "Mock Team 3", 99, 3, 3,999);

        when(statBasketballDAO.findAll()).thenReturn(mockStatList);
        StatBasketball updatedStat = statisticsService.addOrUpdateBasketballStat(statUpdate);

        verify(statBasketballDAO, times(1)).findAll();
        verify(statBasketballDAO,times(1)).update(any());
        verify(statBasketballDAO,times(0)).save(any());

        assertEquals(99, updatedStat.getPoints());
        assertEquals(999, updatedStat.getFouls());

    }

    @DisplayName("Add new basketball stat")
    @Test
    public void addBasketballStat(){
        // if not exist, statBasketballDAO.save(stat) will be called

        StatBasketball newStat = new StatBasketball();
        newStat.setUserId(5);
        newStat.setGameId(1);
        newStat.setTeamName("The Splash");
        newStat.setPoints(0);
        newStat.setRebounds(1);
        newStat.setAssists(2);
        newStat.setFouls(3);


        // throw NoSuchElementException to simulate no existing stat found
        when(statBasketballDAO.findAll()).thenThrow(new NoSuchElementException());
        StatBasketball newReturnedStat = statisticsService.addOrUpdateBasketballStat(newStat);

        verify(statBasketballDAO, times(1)).findAll();
        verify(statBasketballDAO,times(0)).update(any());
        verify(statBasketballDAO, times(1)).save((any()));

    }


}
