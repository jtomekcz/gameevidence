package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Player;

import java.util.List;

public interface PlayerDao {

  /**
   * Gets all active {@link Player}'s
   * @return list of active {@link Player}'s as {@link List}
   */
  List<Player> getAllPlayers();

  /**
   * Creates new {@link Player}
   * @param player new {@link Player} to persist
   * @return persisted {@link Player}
   */
  Player insert(Player player);

  /**
   * Gets {@link Player} according to its alias
   * @param alias {@link Player#alias} as {@link String}
   * @return given {@link Player}
   */
  Player findByAlias(String alias);

  /**
   * Delete given {@link Player}.<br/>
   * Given record of this entity will be set as not valid - {@link Player#valid} will be set on <code>false</code> and
   * {@link Player#validTo} will be set on current time.
   * @param player {@link Player} to detele
   */
  void deletePlayer(Player player);
}
