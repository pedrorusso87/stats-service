package com.app.statsservice.dto;

import com.app.statsservice.model.TournamentCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTournamentRequest {

  private String name;
  private boolean finished;
  private TournamentCategory tournamentCategory;
}
