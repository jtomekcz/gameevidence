package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Player;

import java.util.List;

public interface PlayerService {

  /**
   * Gets all active {@link Player}'s
   * @return list of active {@link Player}'s as {@link List}
   */
  List<Player> getAllPlayers();

  /**
   * Creates/ updates given {@link Player}
   * @param player new {@link Player} to persist
   */
  void setPlayer(Player player);

  /**
   * Gets {@link Player} according to its alias
   * @param alias {@link Player#alias} as {@link String}
   * @return given {@link Player}
   */
  Player findByAlias(String alias);

  /**
   * Delete given {@link Player}.<br/>
   * Given record of this entity will be set as not valid - {@link com.tomek.gamevidence.domain.Player#validity}.
   * @param alias of player to delete
   */
  void deletePlayer(String alias);
}
