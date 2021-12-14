package com.app.statsservice.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeamOwner {
  
  @JsonIgnore
  private Long id;

  private String username;

  private String email;
}
