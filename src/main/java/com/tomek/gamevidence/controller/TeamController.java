package com.tomek.gamevidence.controller;

import com.tomek.gameevidence.api.Team;
import com.tomek.gamevidence.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teams")
public class TeamController {

  @Autowired
  private TeamService teamService;

  @GetMapping("/all-teams")
  public ResponseEntity<List<Team>> getAllPlayers() {
    return ResponseEntity.ok(teamService.getAllTeams());
  }

  @PostMapping("/set-team")
  public ResponseEntity<Void> createPlayer(@RequestBody Team team) {
    teamService.setTeam(team);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/find-team/{alias}")
  public ResponseEntity<Team> findByAlias(@PathVariable("alias") String alias) {
    return ResponseEntity.ok(teamService.findByAlias(alias));
  }

  @DeleteMapping("/delete-team/{alias}")
  public ResponseEntity<Void> deleteTeam(@PathVariable("alias") String alias) {
    teamService.deleteTeam(alias);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/add-player/{teamAlias}/{playerAlias}")
  public ResponseEntity<Void> addPlayer(
          @PathVariable("teamAlias") String teamAlias,
          @PathVariable("playerAlias") String playerAlias) {
    teamService.addPlayer(teamAlias, playerAlias);
    return ResponseEntity.noContent().build();
  }
}
