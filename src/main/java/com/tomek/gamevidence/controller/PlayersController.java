package com.tomek.gamevidence.controller;

import com.tomek.gameevidence.api.Player;
import com.tomek.gamevidence.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/players")
public class PlayersController {

  @Autowired
  private PlayerService playerService;

  @GetMapping("/all-players")
  public ResponseEntity<List<Player>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAllPlayers());
  }

  @PostMapping("/create-player")
  public ResponseEntity<Void> createPlayer(@RequestBody Player player) {
    playerService.insert(player);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/find-player/{alias}")
  public ResponseEntity<Player> findByAlias(@PathVariable("alias") String alias) {
    return ResponseEntity.ok(playerService.findByAlias(alias));
  }

  @DeleteMapping("/delete-player/{alias}")
  public ResponseEntity<Void> deletePlayer(@PathVariable("alias") String alias) {
    playerService.deletePlayer(alias);
    return ResponseEntity.noContent().build();
  }
}
