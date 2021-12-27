package com.app.statsservice.repository;

import com.app.statsservice.model.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamsRepository extends JpaRepository<Team, Long> {

  Optional<Team> findTeamByNameAndUserId(String name, Long id);

  Optional<List<Team>> findTeamByIdIn(List<Long> id);

  @Query("select ts.teamId, roles.roleName from TeamSubscription ts " +
      "join UserRole roles on ts.roleId = roles.id where\n" +
      "user_id = ?1")
  Optional<List<Object[]>> findTeamsSubscriptions(Long userId);
}
