package dev.greendragon;

import com.uni.daos.GameRequestDAO;
import com.uni.entities.GameRequest;
import com.uni.exceptions.DatabaseConnectionException;
import com.uni.services.GameRequestImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class GameRequestServiceTest {

    public static GameRequestImpl gameRequestService;

    public static GameRequestDAO gameDAO;
//
//    @BeforeAll
//    public static void setup(){
//        gameDAO = mock(GameRequestDAO.class);
//        gameRequestService = new GameRequestImpl(gameDAO);
//    }

    @BeforeEach
    public void setup(){
        gameDAO = mock(GameRequestDAO.class);
        gameRequestService = new GameRequestImpl(gameDAO);
    }

    @Test
    public void createRequest(){
        gameRequestService.createRequest(new GameRequest());
        verify(gameDAO).save(any(GameRequest.class));
    }

    @Test
    public void deleteRequest(){
        gameRequestService.deleteRequest(1,2);
        verify(gameDAO).delete(anyInt(), anyInt());
    }

    @Test
    public void getAllGamesAndReferees(){
        gameRequestService.getAllGamesAndReferees();
        verify(gameDAO, times(1)).findAll();
    }

    @Test
    public void createRequestNegativeTest(){
        when(gameDAO.save(any(GameRequest.class))).thenThrow(DatabaseConnectionException.class);
        try {
            gameRequestService.createRequest(new GameRequest());
        }catch(DatabaseConnectionException e){
            verify(gameDAO).save(any(GameRequest.class));
        }
    }

    @Test
    public void deleteRequestNegativeTest(){
        when(gameDAO.delete(anyInt(), anyInt())).thenThrow(DatabaseConnectionException.class);
        try {
            gameRequestService.deleteRequest(1,2);
        }catch(DatabaseConnectionException e){
            verify(gameDAO).delete(anyInt(), anyInt());
        }
    }

}
