package com.app.statsservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponse {
  private String playerName;
  private String documentType;
  private String documentNumber;
  private String dateOfBirth;
  private String status;
}
