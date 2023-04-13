package org.cisco.interview.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cisco.interview.model.Document;
import org.cisco.interview.model.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

@Service
public class TagToDocsService {
    private Map<String, Tag> tagMap;

    public TagToDocsService(@Value("${tags.filename}") String tagsFileName,
                            @Value("${docs.filename}") String docsFileName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Load Tags from file recursively
        JsonNode rootNode = objectMapper.readTree(
                new File(Paths.get("src", "main", "resources", tagsFileName).toUri()));
        tagMap = new HashMap<>();
        for (JsonNode tagNode : rootNode) {
            Tag tag = objectMapper.treeToValue(tagNode, Tag.class);
            tagMap.put(tag.getName(), tag);
            addSubTags(tag);
        }

        // load documents from file and assign to tags
        rootNode = objectMapper.readTree(
                new File(Paths.get("src", "main", "resources", docsFileName).toUri()));
        for (JsonNode tagNode : rootNode) {
            Document document = objectMapper.treeToValue(tagNode, Document.class);
            for (String tag : document.getTags()) {
                tagMap.get(tag).getDocuments().add(document);
            }
        }
    }

    public Set<Document> getDocumentsByTag(String tagName) {
        Tag tag = tagMap.get(tagName);
        if (tag == null) {
            return Collections.emptySet();
        }
        Set<Document> documents = new HashSet<>(tag.getDocuments());
        for (Tag subTag : tag.getSubTags()) {
            documents.addAll(getDocumentsByTag(subTag.getName()));
        }
        return documents;
    }

    private void addSubTags(Tag tag) {
        for (Tag subTag : tag.getSubTags()) {
            tagMap.put(subTag.getName(), subTag);
            addSubTags(subTag);
        }
    }
}
