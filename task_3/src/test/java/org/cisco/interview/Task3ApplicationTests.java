package org.cisco.interview;

import org.cisco.interview.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class Task3ApplicationTests {

    @Autowired
    TagService tagService;

    @Test
    void contextLoads() {
    }

    @Test
    void testDocsToTagsGotWiredUp() {
        assertFalse(tagService.getTagMap().get("humans").getDocuments().isEmpty());
    }

}
