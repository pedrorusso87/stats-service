package com.app.statsservice.controller;

import com.app.statsservice.dto.AddPlayerRequest;
import com.app.statsservice.model.response.PlayersByTeamResponse;
import com.app.statsservice.service.PlayersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@AllArgsConstructor
public class PlayersController {

  private PlayersService playersService;

  @GetMapping("/{teamId}")
  public ResponseEntity<PlayersByTeamResponse> getPlayersByTeamId(@PathVariable Long teamId) {
    return new ResponseEntity<>(playersService.findPlayersByTeam(teamId), HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Object> addPlayersToTeam(@RequestBody AddPlayerRequest addPlayerRequest) {
    return new ResponseEntity<>(playersService.addPlayersToTeam(addPlayerRequest), HttpStatus.OK);
  }
}
