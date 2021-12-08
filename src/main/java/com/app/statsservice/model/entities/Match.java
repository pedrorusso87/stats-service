package com.app.statsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="matches")
@Getter
@Setter
@NoArgsConstructor
public class Match {
// TODO: Maybe this mapping needs to change in order to also add the teams id?
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String venue;

  @Column
  private Date date;

  @Column
  private String winnerTeam;

  @Column
  private String loserTeam;

  @Column
  private String result;

  public Match(String venue, Date date, String winnerTeamId, String looserTeamId, String result) {
    this.venue = venue;
    this.date = date;
    this.winnerTeam = winnerTeamId;
    this.loserTeam = looserTeamId;
    this.result = result;
  }
}
