package dev.greendragon;

import com.uni.daos.StatBasketballDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.PlayerCard;
import com.uni.entities.ImUser;
import com.uni.entities.StatBasketball;
import com.uni.services.StatisticsService;
import com.uni.services.StatisticsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.verification.TooManyActualInvocations;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @BeforeEach
    public void setup(){
        statBasketballDAO = mock(StatBasketballDAO.class);
        userDAO = mock(UserDAO.class);
        statisticsService = new StatisticsServiceImpl(statBasketballDAO, userDAO);
    }


    @Test
    public void getPlayerCardByUserId(){
        when(userDAO.findById(anyInt())).thenReturn(new ImUser(1, "mockUser", "password", "player", 60, 180, "url", false));
        PlayerCard playerCard = statisticsService.getPlayerCardByUserId(1);
        verify(userDAO, times(1)).findById(1);
        assertEquals("mockUser", playerCard.getUsername());
    }

    @Test
    public void getAllBasketballStatsByGameId(){

        // create mock stat list
        List<StatBasketball> mockStatList = Stream.of(
                new StatBasketball(1, 1, 1, "Mock Team 1", 1, 1, 1,1),
                new StatBasketball(2, 2, 1, "Mock Team 2", 2, 2, 2,2)
        ).collect(Collectors.toList());

        // specify the mock behaviour
        when(statBasketballDAO.findAllByGameId(anyInt())).thenReturn(mockStatList);

        // calling the method in the service clas
        List<StatBasketball> statList = statisticsService.getAllBasketballStatsByGameId(1);

        //verifying if the method was invoked & if the result list is of correct size
        verify(statBasketballDAO, times(1)).findAllByGameId(1);
        assertEquals(2, statList.size());
    }

    // Work in progress!
    // still need to figure out how to test EITHER update() or save() is invoked
    @Test
    public void addOrUpdateBasketballStat(){
        // if stat exist, statBasketballDAO.update(existingStat) will be called
        // if not exist, statBasketballDAO.save(stat) will be called

        List<StatBasketball> mockAllStatList = Stream.of(
                new StatBasketball(1, 1, 1, "Mock Team 1", 1, 1, 1,1),
                new StatBasketball(2, 2, 1, "Mock Team 2", 2, 2, 2,2),
                new StatBasketball(3, 3, 2, "Mock Team 3", 3, 3, 3,3)
        ).collect(Collectors.toList());

        StatBasketball existingStat = new StatBasketball(3, 3, 2, "Mock Team 3", 10, 3, 3,10);

        when(statBasketballDAO.findAll()).thenReturn(mockAllStatList);

        StatBasketball resultStat = statisticsService.addOrUpdateBasketballStat(existingStat);


        verify(statBasketballDAO).findAll();
        verify(statBasketballDAO).update(any());
        assertEquals(10, resultStat.getPoints());

        // this would fail since save() is not called
        // verify(statBasketballDAO).save((any()));

    }
}
