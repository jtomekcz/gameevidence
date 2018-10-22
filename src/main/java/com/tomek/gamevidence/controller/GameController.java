package com.tomek.gamevidence.controller;

import com.tomek.gameevidence.api.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public class GameController {

  public ResponseEntity<Void> setGame(@RequestBody Game game) {
    return ResponseEntity.noContent().build();
  }
}
