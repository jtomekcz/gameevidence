package com.tomek.gamevidence.dao;

import com.tomek.gamevidence.domain.Game;

import java.util.List;

public interface GameDao extends AbstractDao {

  List<Game> getAllGames();
}
