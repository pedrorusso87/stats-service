package com.app.statsservice.controller;

import com.app.statsservice.model.entities.Player;
import com.app.statsservice.model.response.PlayersByTeamResponse;
import com.app.statsservice.service.PlayersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayersController {

  private PlayersService playersService;

  @GetMapping("/{teamId}")
  public ResponseEntity<PlayersByTeamResponse> getPlayersByTeamId(@PathVariable Long teamId) {
    return new ResponseEntity<>(playersService.findPlayersByTeam(teamId), HttpStatus.OK);
  }
}
