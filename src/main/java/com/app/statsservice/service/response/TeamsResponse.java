package com.app.statsservice.service.response;

import com.app.statsservice.model.response.TeamOwner;
import lombok.*;

@Data
@NoArgsConstructor
public class TeamsResponse {
  private Long teamId;
  private TeamOwner teamOwner;
  private String teamName;
}
