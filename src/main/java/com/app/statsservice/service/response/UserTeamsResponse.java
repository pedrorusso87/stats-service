package com.app.statsservice.service.response;

import com.app.statsservice.model.response.TeamOwner;
import com.app.statsservice.model.response.UserTeam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserTeamsResponse {
  private TeamOwner teamOwner;
  private List<UserTeam> teamsList;
}
