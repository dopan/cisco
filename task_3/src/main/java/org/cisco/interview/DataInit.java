package org.cisco.interview;

import jakarta.annotation.PostConstruct;
import org.cisco.interview.model.Document;
import org.cisco.interview.service.DocumentService;
import org.cisco.interview.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInit {
    @Autowired
    private DocumentService documentService;

    @Autowired
    private TagService tagService;

    @PostConstruct
    public void wireUpDocumentsToTags() {
        for (Document d : documentService.getDocuments()) {
            for (String tag : d.getTags()) {
                tagService.getTagMap().get(tag).getDocuments().add(d);
            }
        }
    }
}

