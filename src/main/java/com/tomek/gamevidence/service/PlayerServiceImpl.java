package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Player;
import com.tomek.gamevidence.dao.PlayerDao;
import com.tomek.gamevidence.exception.ClientException;
import com.tomek.gamevidence.exception.Errors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final Logger LOGGER = LoggerFactory.getLogger(PlayerServiceImpl.class);

  @Autowired
  private PlayerDao playerDao;

  @Transactional(readOnly = true)
  @Override
  public List<Player> getAllPlayers() {
    List<Player> retVal = new ArrayList<>();
    List<com.tomek.gamevidence.domain.Player> allPlayers = playerDao.getAllPlayers();
    allPlayers.forEach(player -> {
      retVal.add(transformToApiPlayer(player));
    });
    return retVal;
  }

  @Transactional
  @Override
  public void insert(Player player) {
    com.tomek.gamevidence.domain.Player findedPlayer = playerDao.findByAlias(player.getAlias(), com.tomek.gamevidence.domain.Player.class);
    if (findedPlayer != null) {
      LOGGER.error("For given alias {} exists record.", player.getAlias());
      throw new ClientException(Errors.ERR_UNIQUE_RECORD);
    }
    com.tomek.gamevidence.domain.Player domainPlayer = new com.tomek.gamevidence.domain.Player();
    domainPlayer.setAlias(player.getAlias());
    playerDao.insert(domainPlayer);
  }

  @Transactional(readOnly = true)
  @Override
  public Player findByAlias(String alias) {
    return transformToApiPlayer(playerDao.findByAlias(alias, com.tomek.gamevidence.domain.Player.class));
  }

  @Transactional
  @Override
  public void deletePlayer(String alias) {
    com.tomek.gamevidence.domain.Player playerToDelete = playerDao.findByAlias(alias, com.tomek.gamevidence.domain.Player.class);
    playerToDelete.getValidity().setValid(false);
  }

  /**
   * Transform {@link com.tomek.gamevidence.domain.Player} to {@link Player}
   * @param domainPlayer
   * @return
   */
  public static Player transformToApiPlayer(com.tomek.gamevidence.domain.Player domainPlayer) {
    if (domainPlayer == null) {
      return null;
    }
    Player player = new Player();
    player.setAlias(domainPlayer.getAlias());
    player.setWinnersCount(domainPlayer.getWinnersCount());
    player.setLostsCount(domainPlayer.getLostsCount());
    return player;
  }
}
