package com.app.statsservice.repository;

import com.app.statsservice.model.entities.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
}
