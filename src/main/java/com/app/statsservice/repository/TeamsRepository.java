package com.app.statsservice.repository;

import com.app.statsservice.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Match, Long> {
}
