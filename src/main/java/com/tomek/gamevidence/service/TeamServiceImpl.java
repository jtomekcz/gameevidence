package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.PlayersList;
import com.tomek.gameevidence.api.Team;
import com.tomek.gamevidence.dao.PlayerDao;
import com.tomek.gamevidence.dao.TeamDao;
import com.tomek.gamevidence.domain.Player;
import com.tomek.gamevidence.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamServiceImpl implements TeamService {

  private final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

  @Autowired
  private TeamDao teamDao;

  @Autowired
  private PlayerDao playerDao;

  @Transactional(readOnly = true)
  @Override
  public List<Team> getAllTeams() {
    List<Team> retVal = new ArrayList<>();
    List<com.tomek.gamevidence.domain.Team> domainTeams = teamDao.getAllTeams();
    domainTeams.forEach(team -> {
      retVal.add(transformToApiTeam(team));
    });
    return retVal;
  }

  @Transactional
  @Override
  public void setTeam(Team team) {
    com.tomek.gamevidence.domain.Team domainTeam = teamDao.findByAlias(team.getAlias(), com.tomek.gamevidence.domain.Team.class);
    if (domainTeam == null) {
      domainTeam = new com.tomek.gamevidence.domain.Team();
      domainTeam.setAlias(team.getAlias());
    }
    domainTeam.setWinnersCount(team.getWinnersCount());
    domainTeam.setLostsCount(team.getLostsCount());
    domainTeam.setMaxPlayersCount(team.getMaxPlayersCount());
    if (!CollectionUtils.isEmpty(team.getPlayers())) {
      List<Player> players = new ArrayList<>();
      team.getPlayers().forEach(player -> {
        players.add(playerDao.findByAlias(player.getAlias(), Player.class));
      });
      domainTeam.setPlayers(players);
    }
    teamDao.setDomainObject(domainTeam);
  }

  @Transactional(readOnly = true)
  @Override
  public Team findByAlias(String alias) {
    return transformToApiTeam(teamDao.findByAlias(alias, com.tomek.gamevidence.domain.Team.class));
  }

  @Transactional
  @Override
  public void deleteTeam(String alias) {
    com.tomek.gamevidence.domain.Team teamToDelete = teamDao.findByAlias(alias, com.tomek.gamevidence.domain.Team.class);
    teamToDelete.getValidity().setValid(false);
  }

  @Transactional
  @Override
  public void addPlayer(String teamAlias, String plyerAlias) {
    Player player = playerDao.findByAlias(plyerAlias, Player.class);

    if (player == null) {
      LOGGER.error("Cannot find player - {}", plyerAlias);
      throw new ClientException("");
    }
    com.tomek.gamevidence.domain.Team team = teamDao.findByAlias(teamAlias, com.tomek.gamevidence.domain.Team.class);
    player.setTeam(team);
  }

  /**
   * Transform {@link com.tomek.gamevidence.domain.Team} to {@link Team}
   */
  public static Team transformToApiTeam(com.tomek.gamevidence.domain.Team domainTeam) {
    Team team = new Team();
    team.setAlias(domainTeam.getAlias());
    team.setWinnersCount(domainTeam.getWinnersCount());
    team.setLostsCount(domainTeam.getLostsCount());
    team.setMaxPlayersCount(domainTeam.getMaxPlayersCount());
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
