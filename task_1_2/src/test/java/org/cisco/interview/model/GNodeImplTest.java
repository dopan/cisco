package org.cisco.interview.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GNodeImplTest {
    @Test
    void testCreatingNodeWithNullValues() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new GNodeImpl(null, new ArrayList<>()),
                "IllegalArgumentException expected."
        );
        assertTrue(thrown.getMessage().contentEquals("Name and children must not be null."));
        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> new GNodeImpl("A", null),
                "IllegalArgumentException expected."
        );
        assertTrue(thrown2.getMessage().contentEquals("Name and children must not be null."));
    }

    @Test
    void testGNode() {
        GNode node = new GNodeImpl("A", new ArrayList<>());
        assertEquals("A", node.getName());
        assertEquals(0, node.getChildren().size());
        node.getChildren().add(new GNodeImpl("B", new ArrayList<>()));
        assertEquals(1, node.getChildren().size());
        assertEquals("B", node.getChildren().get(0).getName());
    }
}
