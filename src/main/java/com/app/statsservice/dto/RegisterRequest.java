package com.app.statsservice.dto;

import com.app.statsservice.exception.validators.ValidEmail;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegisterRequest {

  @NotEmpty
  private String username;

  @NotEmpty
  private String password;

  @NotEmpty
  @ValidEmail
  private String email;
}
