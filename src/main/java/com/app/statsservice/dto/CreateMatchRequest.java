package com.app.statsservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMatchRequest {
  private Long id;
  private String date;
  private String result;
  private String username;
}
