package com.app.statsservice.dto;

import com.app.statsservice.model.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddTeamRequest {

  @NotEmpty
  private String teamName;

  @NotNull
  private Date dateCreated;

  @NotNull
  private Date foundationDate;

  private User teamOwner;
}
