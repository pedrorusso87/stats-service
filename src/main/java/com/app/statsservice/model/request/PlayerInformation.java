package com.app.statsservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerInformation {

  private String playerName;

  private Date dateOfBirth;

  private String documentNumber;

  private Long documentTypeId;
}
