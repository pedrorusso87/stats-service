package com.app.statsservice.dto;

import com.app.statsservice.model.entities.Team;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class CreateMatchRequest {

  @NotNull
  private Long matchId;

  @NotNull
  private String winnerTeam;

  @NotNull
  private String loserTeam;

  @NotNull
  private Date date;

  @NotEmpty
  private String result;

  private String venue;
}
