package com.app.statsservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayersByTeamResponse {
  private List<PlayerResponse> playerList;
  private String teamName;
}
