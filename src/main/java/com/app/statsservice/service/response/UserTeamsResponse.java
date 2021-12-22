package com.app.statsservice.service.response;

import com.app.statsservice.model.response.UserTeam;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserTeamsResponse {
  private List<UserTeam> teamsList;
}
