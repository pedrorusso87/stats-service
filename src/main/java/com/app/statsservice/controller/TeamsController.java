package com.app.statsservice.controller;

import com.app.statsservice.dto.AddTeamRequest;
import com.app.statsservice.model.entities.Team;
import com.app.statsservice.service.TeamsService;
import com.app.statsservice.service.response.TeamsResponse;
import com.app.statsservice.service.response.UserTeamsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {

  private TeamsService teamsService;

  public TeamsController(TeamsService teamsService) {
    this.teamsService = teamsService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<TeamsResponse>> getAllTeams() {
    return new ResponseEntity<>(teamsService.getTeams(), HttpStatus.OK);
  }

  @GetMapping("/{username}")
  public ResponseEntity<UserTeamsResponse> getTeamsByUser(@PathVariable String username) {
    return new ResponseEntity<>(teamsService.getTeamsByUserId(username), HttpStatus.OK);
  }

  @PostMapping("/add")
  public ResponseEntity<Team> addTeam(@Valid @RequestBody AddTeamRequest addTeamRequest) {
    Team newTeam = teamsService.saveTeam(addTeamRequest);
    return new ResponseEntity<>(newTeam, HttpStatus.OK);
  }
}
