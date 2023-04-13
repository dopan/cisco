package org.cisco.interview.service;

import org.cisco.interview.model.Document;
import org.cisco.interview.model.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TagToDocsServiceTest {

    @Autowired
    TagToDocsService tagsToDocsService;

    @Test
    void testNonExistingTag() {
        Set<Document> docs = tagsToDocsService.getDocumentsByTag("aaa");
        assertEquals(0, docs.size());
    }

    @Test
    void testTagNameSize() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new Tag("asdasdabnskdjbaskjdbaskjdbaskdbakdfbsakhbfdshkbfadshfdsajhbfjsahdbfdsjbfsadfdsfdsf", null, null),
                "Tag name cannot be null and length is limited to maximum 50 characters."
        );
    }

    @Test
    void testDocumentByTag() {
        Set<Document> docs = tagsToDocsService.getDocumentsByTag("carnivore");
        assertEquals(2, docs.size());
        assertTrue(docs.contains(new Document("https://en.wikipedia.org/wiki/Venus_flytrap")));
        assertTrue(docs.contains(new Document("Petr")));

        docs = tagsToDocsService.getDocumentsByTag("animals");
        assertEquals(3, docs.size());
        assertTrue(docs.contains(new Document("https://en.wikipedia.org/wiki/Cattle")));
        assertTrue(docs.contains(new Document("https://en.wikipedia.org/wiki/Dog")));
        assertTrue(docs.contains(new Document("https://en.wikipedia.org/wiki/Finch")));

        docs = tagsToDocsService.getDocumentsByTag("humans");
        assertEquals(4, docs.size());
        assertTrue(docs.contains(new Document("Jirka")));
        assertTrue(docs.contains(new Document("Petr")));
        assertTrue(docs.contains(new Document("Honza")));

        // Subtag for humans is carnivore -- which is also subtag for Plants
        // When we query humans - it queries also sub-tag 'carnivore' which matches the plant as well - known bug:-)
        assertTrue(docs.contains(new Document("https://en.wikipedia.org/wiki/Venus_flytrap")));
    }
}
