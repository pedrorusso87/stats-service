package com.app.statsservice.service;

import com.app.statsservice.dto.CreateMatchRequest;
import com.app.statsservice.exception.MatchNotFoundException;
import com.app.statsservice.model.entities.Match;
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

    matchRepository.save(buildMatch(matchRequest));
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

  private Match buildMatch(CreateMatchRequest request) {
    Match match = new Match(request.getVenue(),request.getDate(),
        request.getWinnerTeam(), request.getLoserTeam(), request.getResult());
    return match;
  }
}
