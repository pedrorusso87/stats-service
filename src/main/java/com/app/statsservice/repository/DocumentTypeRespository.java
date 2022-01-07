package com.app.statsservice.repository;

import com.app.statsservice.model.entities.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRespository extends JpaRepository<DocumentType, Long> {
}
