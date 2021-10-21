package com.app.statsservice.repository;

import com.app.statsservice.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Team, Long> {
}
