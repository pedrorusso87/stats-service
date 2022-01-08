package com.app.statsservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long id;

  @Column
  private String name;

  @Column
  private Date dateOfBirth;

  @Column
  private String documentNumber;

  @Column
  private String status;

  @ManyToOne()
  @JoinColumn(name="document_type_id")
  @JsonIgnore
  private DocumentType documentType;

  @ManyToOne()
  @JoinColumn(name="team_id")
  @JsonIgnore
  private Team team;
}
