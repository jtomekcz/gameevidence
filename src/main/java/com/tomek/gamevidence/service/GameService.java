package com.tomek.gamevidence.service;

import com.tomek.gameevidence.api.Game;

public interface GameService {

  /**
   * Creates/ update given game
   * @param game
   */
  void setGame(Game game);
}
