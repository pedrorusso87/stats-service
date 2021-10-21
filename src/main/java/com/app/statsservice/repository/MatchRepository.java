package com.app.statsservice.repository;

import com.app.statsservice.model.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
