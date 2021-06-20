package com.app.statsservice.service;

import com.app.statsservice.dto.CreateMatchRequest;
import com.app.statsservice.exception.MatchNotFoundException;
import com.app.statsservice.model.Match;
import com.app.statsservice.repository.MatchRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

  private AuthService authService;
  private MatchRepository matchRepository;

  public MatchService(AuthService authService, MatchRepository matchRepository) {
    this.authService = authService;
    this.matchRepository = matchRepository;
  }
  public void createMatch(CreateMatchRequest matchRequest) {
    Match match = new Match();
    match.setDate(matchRequest.getDate());
    match.setResult(matchRequest.getResult());
    User username = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("No user logged in"));
    match.setUserName(username.getUsername());
    matchRepository.save(match);
  }

  public List<Match> getAllMatches() {
    List<Match> matches = matchRepository.findAll();

    /*
    * Implement this when we know what do we want to return to de UI
    */
    //return matches.stream().map(this::mapFromMatchtoObject()).collect(toList());
    return matches;
  }

  public Match getSingleMatch(Long id) {
    Match match = matchRepository.findById(id).orElseThrow(() -> new MatchNotFoundException("For id" + id));
    return match;
  }

  /* private Object mapFromMatchtoObject(Match match) {

  }*/
}
