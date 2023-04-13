package org.cisco.interview.model;

import java.util.ArrayList;
import java.util.List;

public class Tag {

    String name;
    List<Tag> subTags;

    List<Document> documents;

    public Tag() {
        documents = new ArrayList<>();
    }

    public Tag(String name, List<Tag> subTags, List<Document> documents) {
        this.name = name;
        this.subTags = subTags;
        this.documents = documents;
    }

    public String getName() {
        return name;
    }

    public List<Tag> getSubTags() {
        return subTags;
    }

    public List<Document> getDocuments() {
        return documents;
    }
}
