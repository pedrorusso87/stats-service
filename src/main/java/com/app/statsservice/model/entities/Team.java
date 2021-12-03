package com.app.statsservice.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="teams")
@Getter
@Setter
@NoArgsConstructor
public class Team {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String name;

  // This field will be used to reflect if the team is 'ACTIVE' or 'DELETED'
  @Column
  private String status;

  //The date the record was added to the database
  @Column
  @Temporal(TemporalType.DATE)
  private Date dateCreated;

  //The date the team was founded
  @Column
  @Temporal(TemporalType.DATE)
  private Date foundationDate;

  // The team owner
  @ManyToOne()
  @JoinColumn(name="user_id")
  @JsonIgnore
  private User user;
}
