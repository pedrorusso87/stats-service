package com.app.statsservice.service;

import com.app.statsservice.model.entities.DocumentType;
import com.app.statsservice.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentTypeService {

  @Autowired
  private DocumentTypeRepository documentTypeRepository;

  public DocumentTypeService(DocumentTypeRepository documentTypeRepository) {
    this.documentTypeRepository = documentTypeRepository;
  }

  public List<DocumentType> getAllDocumentTypes() {
    return this.documentTypeRepository.findAll();
  }
}
