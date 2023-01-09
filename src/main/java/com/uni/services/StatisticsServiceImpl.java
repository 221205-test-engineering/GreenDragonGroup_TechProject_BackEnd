package com.uni.services;

import com.uni.daos.StatBasketballDAO;
import com.uni.daos.UserDAO;
import com.uni.dtos.PlayerCard;
import com.uni.entities.ImUser;
import com.uni.entities.StatBasketball;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class StatisticsServiceImpl implements StatisticsService{

    private StatBasketballDAO statBasketballDAO;
    private UserDAO userDAO;

    public StatisticsServiceImpl(StatBasketballDAO statBasketballDAO, UserDAO userDAO) {
        this.statBasketballDAO = statBasketballDAO;
        this.userDAO = userDAO;
    }

    @Override
    public PlayerCard getPlayerCardByUserId(int id) {
        ImUser user = this.userDAO.findById(id);
        List<StatBasketball> basketballStat = this.statBasketballDAO.findAll().stream().filter(t -> t.getUserId() == id).collect(Collectors.toList());

        PlayerCard playerCard = new PlayerCard();
        playerCard.setId(user.getUserId());
        playerCard.setUsername(user.getUsername());
        playerCard.setHeightInches(user.getHeightInches());
        playerCard.setWeightLbs(user.getWeightLbs());
        playerCard.setProfilePic(user.getProfilePic());
        playerCard.setHideBiometrics(user.isHideBiometrics());
        playerCard.setBasketballStats(basketballStat);

        return playerCard;
    }

    @Override
    public List<StatBasketball> getAllBasketballStatsByGameId(int id) {
        return statBasketballDAO.findAllByGameId(id);
    }

    @Override
    public StatBasketball addOrUpdateBasketballStat(StatBasketball stat) {
        try {
            // Check if basketball stat already exists according to user_id, game_id, and team_name
            StatBasketball existingStat = statBasketballDAO.findAll().stream().filter(s ->
                    s.getUserId() == stat.getUserId() &&
                            s.getGameId() == stat.getGameId() &&
                            s.getTeamName().equals(stat.getTeamName())).findFirst().get();

            // Exists, so update with latest information
            existingStat.setPoints(stat.getPoints());
            existingStat.setFouls(stat.getFouls());

            statBasketballDAO.update(existingStat);

            return existingStat;
        } catch (NoSuchElementException e) {
            // Does not exist, so create a new one
            return statBasketballDAO.save(stat);
        }

    }
}
