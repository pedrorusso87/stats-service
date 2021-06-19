package com.app.statsservice.controller;

import com.app.statsservice.dto.CreateMatchRequest;
import com.app.statsservice.service.MatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/match/create")
public class MatchController {

  private MatchService matchService;

  public MatchController(MatchService matchService) {
    this.matchService = matchService;
  }
  @PostMapping()
  public ResponseEntity createMatch(@RequestBody CreateMatchRequest matchRequest) {
    matchService.createMatch(matchRequest);
    return new ResponseEntity(HttpStatus.OK);
  }
}
