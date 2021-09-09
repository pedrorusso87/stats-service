package com.app.statsservice.service;

import com.app.statsservice.dto.CreateTournamentRequest;
import com.app.statsservice.model.Tournament;
import com.app.statsservice.model.TournamentCategory;
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
    tournament.setFinished(tournamentRequest.isFinished());
    TournamentCategory category = new TournamentCategory();
    category.setId(tournamentRequest.getTournamentCategory().getId());
    category.setDescription(tournamentRequest.getTournamentCategory().getDescription());
    tournament.setTournamentCategory(category);
    tournamentRepository.save(tournament);
  }

  public List<Tournament> getTournaments() {
    return tournamentRepository.findAll();
    //return tournaments;
  }

  public List<Tournament> getAllTournaments() {
    return tournamentRepository.findAll();
  }
}
