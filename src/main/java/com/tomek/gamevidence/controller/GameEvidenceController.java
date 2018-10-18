package com.tomek.gamevidence.controller;

import com.tomek.gameevidence.api.Player;
import com.tomek.gamevidence.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class GameEvidenceController {

  @Autowired
  private PlayerService playerService;

  @GetMapping("/all-players")
  public ResponseEntity<List<Player>> getAllPlayers() {
    return ResponseEntity.ok(playerService.getAllPlayers());
  }

  @PostMapping("create-player")
  public ResponseEntity<Void> createPlayer(@RequestBody Player player) {
    playerService.insert(player);
    return ResponseEntity.noContent().build();
  }
}
