package com.app.statsservice.model.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

  // user id of the team owner
  @OneToOne (cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;
}
