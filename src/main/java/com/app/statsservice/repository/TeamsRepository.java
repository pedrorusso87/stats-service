package com.app.statsservice.repository;

import com.app.statsservice.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Team, Long> {
  Optional<List<Team>> findTeamsByUserId(Long userId);

  Optional<Team> findTeamByNameAndUserId(String name, Long id);
}
