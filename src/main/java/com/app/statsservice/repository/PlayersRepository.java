package com.app.statsservice.repository;

import com.app.statsservice.model.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayersRepository extends JpaRepository<Player, Long> {
  Optional<List<Player>> findPlayersByTeamId(Long teamId);
}
