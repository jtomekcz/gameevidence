package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.PlayersList;
import com.tomek.gameevidence.api.Team;
import com.tomek.gamevidence.dao.PlayerDao;
import com.tomek.gamevidence.dao.TeamDao;
import com.tomek.gamevidence.domain.Player;
import com.tomek.gamevidence.exception.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {

  @Autowired
  private TeamDao teamDao;

  @Autowired
  private PlayerDao playerDao;

  @Override
  public List<Team> getAllTeams() {
    List<Team> retVal = new ArrayList<>();
    List<com.tomek.gamevidence.domain.Team> domainTeams = teamDao.getAllTeams();
    domainTeams.forEach(team -> {
      retVal.add(transformToApiTeam(team));
    });
    return retVal;
  }

  @Override
  public void insert(Team team) {
    com.tomek.gamevidence.domain.Team domainTeam = new com.tomek.gamevidence.domain.Team();
    domainTeam.setAlias(team.getAlias());
    if (!CollectionUtils.isEmpty(team.getPlayers())) {
      List<Player> players = new ArrayList<>();
      team.getPlayers().forEach(player -> {
        players.add(playerDao.findByAlias(player.getAlias(), Player.class));
      });
      domainTeam.setPlayers(players);
    }
    teamDao.insert(domainTeam);
  }

  @Override
  public Team findByAlias(String alias) {
    return transformToApiTeam(teamDao.findByAlias(alias, com.tomek.gamevidence.domain.Team.class));
  }

  @Override
  public void deleteTeam(String alias) {
    com.tomek.gamevidence.domain.Team teamToDelete = teamDao.findByAlias(alias, com.tomek.gamevidence.domain.Team.class);
    teamToDelete.getValidity().setValid(false);
  }

  @Override
  public void addPlayer(String teamAlias, String plyerAlias) {
    Player player = playerDao.findByAlias(plyerAlias, Player.class);
    com.tomek.gamevidence.domain.Team team = teamDao.findByAlias(teamAlias, com.tomek.gamevidence.domain.Team.class);
    if (team.getPlayers() != null && team.getPlayers().size() == team.getMaxPlayersCount()) {
      throw new ClientException("");
    }
    team.getPlayers().add(player);
  }

  /**
   * Transform {@link com.tomek.gamevidence.domain.Team} to {@link Team}
   */
  private Team transformToApiTeam(com.tomek.gamevidence.domain.Team domainTeam) {
    Team team = new Team();
    team.setAlias(domainTeam.getAlias());
    team.setWinnersCount(domainTeam.getWinnersCount());
    team.setLostsCount(domainTeam.getLostsCount());
    if (!CollectionUtils.isEmpty(domainTeam.getPlayers())) {
      PlayersList players = new PlayersList();
      domainTeam.getPlayers().forEach(player -> {
        players.add(PlayerServiceImpl.transformToApiPlayer(player));
      });
      team.setPlayers(players);
    }
    return team;
  }
}
