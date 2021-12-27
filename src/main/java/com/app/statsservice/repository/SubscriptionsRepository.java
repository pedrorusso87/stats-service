package com.app.statsservice.repository;

import com.app.statsservice.model.entities.TeamSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionsRepository extends JpaRepository<TeamSubscription, Long> {
}
