package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Player;
import com.tomek.gamevidence.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

  private final PlayerDao playerDao;

  @Autowired
  public PlayerServiceImpl(PlayerDao playerDao) {
    this.playerDao = playerDao;
  }

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
    com.tomek.gamevidence.domain.Player domainPlayer = new com.tomek.gamevidence.domain.Player();
    domainPlayer.setAlias(player.getAlias());
    domainPlayer.setValid(true);
    domainPlayer.setValidFrom(Instant.now());
    domainPlayer.setValidTo(Instant.MAX);
    playerDao.insert(domainPlayer);
  }

  @Transactional(readOnly = true)
  @Override
  public Player findByAlias(String alias) {
    return null;
  }

  @Transactional
  @Override
  public void deletePlayer(Player player) {

  }

  /**
   * Transform {@link com.tomek.gamevidence.domain.Player} to {@link Player}
   * @param domainPlayer
   * @return
   */
  private Player transformToApiPlayer(com.tomek.gamevidence.domain.Player domainPlayer) {
    Player player = new Player();
    player.setAlias(domainPlayer.getAlias());
    return player;
  }
}
