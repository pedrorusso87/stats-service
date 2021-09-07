package com.app.statsservice.controller;

import com.app.statsservice.dto.CreateTournamentRequest;
import com.app.statsservice.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

  private TournamentService tournamentService;

  public TournamentController(TournamentService tournamentService) {
    this.tournamentService = tournamentService;
  }

  @PostMapping("/create")
  public ResponseEntity createTournament(@RequestBody CreateTournamentRequest tournamentRequest) {
    tournamentService.createTournament(tournamentRequest);
    return new ResponseEntity(HttpStatus.OK);
  }
}
