package com.example.auditlogsmongodb.resource;

import com.example.auditlogsmongodb.repository.DocumentService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/audit")
@RestController
public class AuditLogResource {

    Logger logger = LoggerFactory.getLogger(AuditLogResource.class);

    DocumentService documentService;

    public AuditLogResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public List<Document> getEvents() {
        return documentService.getDocuments();
    }

    @GetMapping("/{entityName}")
    public List<Document> getEvents(@PathVariable("entityName") String entityName) {
        logger.info("Path param entityName #######" +entityName);
        return documentService.getDocuments(entityName);
    }

}
