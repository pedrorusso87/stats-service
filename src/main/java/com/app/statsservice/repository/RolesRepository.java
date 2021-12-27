package com.app.statsservice.repository;

import com.app.statsservice.model.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<UserRole, Long> {

  @Query("select roles.id from UserRole roles " +
      "where roles.roleName='team_owner'")
  Optional<Long> findTeamOwnerRoleId();
}
