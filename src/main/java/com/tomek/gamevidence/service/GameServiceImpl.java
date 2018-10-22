package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Game;
import com.tomek.gamevidence.dao.GameDao;
import com.tomek.gamevidence.dao.TeamDao;
import com.tomek.gamevidence.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

  @Autowired
  private GameDao gameDao;

  @Autowired
  private TeamDao teamDao;

  @Override
  public void setGame(Game game) {
    com.tomek.gamevidence.domain.Game domainGame = gameDao.findByAlias(game.getAlias(), com.tomek.gamevidence.domain.Game.class);
    if (domainGame == null) {
      domainGame = new com.tomek.gamevidence.domain.Game();
      domainGame.setAlias(game.getAlias());
      if (game.getWinnerTeam() != null) {
        domainGame.setWinnerTeam(teamDao.findByAlias(game.getWinnerTeam().getAlias(), Team.class));
      }
    }
    gameDao.setDomainObject(domainGame);
  }

  private Game transfromToApiGame(com.tomek.gamevidence.domain.Game domainGame) {
    Game game = new Game();
    game.setAlias(domainGame.getAlias());
    game.setWinnerTeam(TeamServiceImpl.transformToApiTeam(domainGame.getWinnerTeam()));
    return game;
  }

}
