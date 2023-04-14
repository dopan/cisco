package org.cisco.interview.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cisco.interview.model.Document;
import org.cisco.interview.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class DocumentService {

    @Autowired
    private TagService tagService;

    private Set<Document> documents;

    public DocumentService(@Value("${docs.filename}") String docsFileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        documents = new HashSet<>();
        // load documents from file and assign to tags
        JsonNode rootNode = objectMapper.readTree(
                new File(Paths.get("src", "main", "resources", docsFileName).toUri()));
        for (JsonNode tagNode : rootNode) {
            Document d = objectMapper.treeToValue(tagNode, Document.class);
            documents.add(d);
        }
    }

    public Set<Document> getDocumentsByTag(String tagName) {
        Tag tag = tagService.getTagMap().get(tagName);
        if (tag == null) {
            return Collections.emptySet();
        }
        Set<Document> documents = new HashSet<>(tag.getDocuments());
        for (Tag subTag : tag.getSubTags()) {
            documents.addAll(getDocumentsByTag(subTag.getName()));
        }
        return documents;
    }

    public Set<Document> getDocuments() {
        return documents;
    }
}
