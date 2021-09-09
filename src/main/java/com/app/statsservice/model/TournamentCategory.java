package com.app.statsservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="tournament_categories")
@Getter
@Setter
@NoArgsConstructor
public class TournamentCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String description;
}
