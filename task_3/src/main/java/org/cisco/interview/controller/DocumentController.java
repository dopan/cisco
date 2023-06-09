package org.cisco.interview.controller;

import org.cisco.interview.model.Document;
import org.cisco.interview.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class DocumentController {

    @Autowired
    DocumentService service;

    @GetMapping("/taggedContent")
    public Set<Document> getTaggedContent(@RequestParam("tag") String tagName) {
        return service.getDocumentsByTag(tagName);
    }
}