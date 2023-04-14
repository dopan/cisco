package org.cisco.interview.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cisco.interview.model.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class TagService {
    private Map<String, Tag> tagMap;

    public TagService(@Value("${tags.filename}") String tagsFileName) throws IOException {
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
    }

    public Map<String, Tag> getTagMap() {
        return tagMap;
    }

    private void addSubTags(Tag tag) {
        for (Tag subTag : tag.getSubTags()) {
            tagMap.put(subTag.getName(), subTag);
            addSubTags(subTag);
        }
    }
}
