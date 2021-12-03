package com.app.statsservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTeam {
  private Long id;
  private String name;
  private String status;
  private String foundationDate;
  private String dateCreated;
}
