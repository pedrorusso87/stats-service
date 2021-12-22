package com.app.statsservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="team_subscriptions")
@Getter
@Setter
@NoArgsConstructor
public class TeamSubscription {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long userId;

  @Column
  private Long teamId;

  @Column
  private Long roleId;
}
