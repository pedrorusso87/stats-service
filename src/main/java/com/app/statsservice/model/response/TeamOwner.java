package com.app.statsservice.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamOwner {
  private Long id;
  private String name;
  private String email;
}
