package com.app.statsservice.controller;

import com.app.statsservice.dto.CreateMatchRequest;
import com.app.statsservice.model.entities.Match;
import com.app.statsservice.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

  private MatchService matchService;

  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }

  @PostMapping("/create")
  public ResponseEntity createMatch(@RequestBody CreateMatchRequest matchRequest) {
    matchService.createMatch(matchRequest);
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<Match>> getAllMatches() {
    return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<Match> getSingleMatch(@PathVariable @RequestBody Long id) {
    return new ResponseEntity<>(matchService.getSingleMatch(id), HttpStatus.OK);
  }
}
