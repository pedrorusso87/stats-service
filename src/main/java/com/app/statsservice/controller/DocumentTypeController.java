package com.app.statsservice.controller;

import com.app.statsservice.model.entities.DocumentType;
import com.app.statsservice.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentTypeController {

  @Autowired
  private DocumentTypeService documentTypeService;

  public DocumentTypeController(DocumentTypeService documentTypeService) {
    this.documentTypeService = documentTypeService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<DocumentType>> getAllDocumentTypes() {
    return new ResponseEntity<>(this.documentTypeService.getAllDocumentTypes(), HttpStatus.OK);
  }
}
