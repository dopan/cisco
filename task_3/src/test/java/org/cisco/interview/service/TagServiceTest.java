package org.cisco.interview.service;

import org.cisco.interview.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class TagServiceTest {

    @Autowired
    TagService tagService;

    @Test
    void testTagNameSize() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Tag("asdasdabnskdjbaskjdbaskjdbaskdbakdfbsakhbfdshkbfadshfdsajhbfjsahdbfdsjbfsadfdsfdsf", null, null),
                "Tag name cannot be null and length is limited to maximum 50 characters."
        );
    }
    @Test
    void testTagsLoaded() {
       assertFalse(tagService.getTagMap().isEmpty());
    }
}
