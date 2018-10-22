package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Team;

import java.util.List;

public interface TeamService {

  /**
   * Gets all active {@link com.tomek.gamevidence.domain.Team}'s
   * @return list of active {@link com.tomek.gamevidence.domain.Team}'s as {@link List}
   */
  List<Team> getAllTeams();

  /**
   * Creates/updates given {@link Team}
   * @param team {@link Team} to persist
   */
  void setTeam(Team team);

  /**
   * Gets {@link Team} according to its alias
   * @param alias {@link Team#alias} as {@link String}
   * @return given {@link Team}
   */
  Team findByAlias(String alias);

  /**
   * Delete given {@link Team}.<br/>
   * Given record of this entity will be set as not valid - {@link com.tomek.gamevidence.domain.Team#validity}.
   * @param alias of team to delete
   */
  void deleteTeam(String alias);

  /**
   * Find players according to its alias and than add to given team. In process is also check {@link com.tomek.gamevidence.domain.Team#maxPlayersCount}
   * @param teamAlias
   * @param plyerAlias
   */
  void addPlayer(String teamAlias, String plyerAlias);
}
