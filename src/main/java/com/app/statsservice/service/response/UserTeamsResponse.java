package com.app.statsservice.service.response;

import com.app.statsservice.model.entities.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserTeamsResponse {
  private Long userId;
  private List<Team> teamsList;
}
