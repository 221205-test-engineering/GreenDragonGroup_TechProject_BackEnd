package com.uni.services;

import com.uni.daos.GameRequestDAO;
import com.uni.entities.GameRequest;

import java.util.List;

public class GameRequestImpl implements GameRequestService{

    private GameRequestDAO gameRequestDAO;

    public GameRequestImpl(GameRequestDAO gameRequestDAO) { this.gameRequestDAO = gameRequestDAO; }

    @Override
    public GameRequest createRequest(GameRequest gameRequest) {
        return this.gameRequestDAO.save(gameRequest);
    }

    @Override
    public boolean deleteRequest(int gameId, int userId) {
        return this.gameRequestDAO.delete(gameId, userId);
    }

    @Override
    public List<GameRequest> getAllGamesAndReferees() { return this.gameRequestDAO.findAll(); }
}
