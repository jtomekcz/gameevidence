package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Player;

import java.util.List;

public interface PlayerDao extends AbstractDao {

  /**
   * Gets all active {@link Player}'s
   * @return list of active {@link Player}'s as {@link List}
   */
  List<Player> getAllPlayers();

}
