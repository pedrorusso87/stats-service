package com.app.statsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tournaments")
@Getter
@Setter
@NoArgsConstructor
public class Tournament {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  @Column
  private boolean finished;

  // Many tournaments are going to have one tournament category
  @ManyToOne(cascade = CascadeType.MERGE)
  @JoinColumn(name = "tournament_id")
  private TournamentCategory tournamentCategory;
}
