package com.app.statsservice.dto;

import com.app.statsservice.model.request.PlayerInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddPlayerRequest {

  @NotEmpty
  private List<PlayerInformation> playerInformationList;

  @NotEmpty
  private Long teamId;
}
