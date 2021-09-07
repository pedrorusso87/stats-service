package com.app.statsservice.service;

import com.app.statsservice.dto.CreateTournamentRequest;
import com.app.statsservice.model.Tournament;
import com.app.statsservice.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

  private TournamentRepository tournamentRepository;

  public TournamentService(TournamentRepository tournamentRepository) {
    this.tournamentRepository = tournamentRepository;
  }

  public void createTournament(CreateTournamentRequest tournamentRequest) {
    Tournament tournament = new Tournament();
    tournament.setName(tournamentRequest.getName());
    tournamentRepository.save(tournament);
  }

  public List<Tournament> getAllTournaments() {
    return tournamentRepository.findAll();
  }
}
