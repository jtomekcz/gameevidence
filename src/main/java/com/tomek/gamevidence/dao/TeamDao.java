package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Team;

import java.util.List;

public interface TeamDao extends AbstractDao {

  /**
   * Gets all active {@link com.tomek.gamevidence.domain.Team}'s
   * @return list of active {@link com.tomek.gamevidence.domain.Team}'s as {@link List}
   */
  List<Team> getAllTeams();
}
