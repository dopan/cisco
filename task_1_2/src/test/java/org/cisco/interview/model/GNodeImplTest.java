package org.cisco.interview.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GNodeImplTest {
    @Test
    void testCreatingNodeWithNullValues() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> new GNodeImpl(null, new ArrayList<>()),
                "IllegalArgumentException expected."
        );
        Assertions.assertTrue(thrown.getMessage().contentEquals("Name and children must not be null."));
        IllegalArgumentException thrown2 = assertThrows(
                IllegalArgumentException.class,
                () -> new GNodeImpl("A", null),
                "IllegalArgumentException expected."
        );
        Assertions.assertTrue(thrown2.getMessage().contentEquals("Name and children must not be null."));
    }

    @Test
    void testGNode() {
        GNode node = new GNodeImpl("A", new ArrayList<>());
        Assertions.assertEquals("A", node.getName());
        Assertions.assertEquals(0, node.getChildren().size());
        node.getChildren().add(new GNodeImpl("B", new ArrayList<>()));
        Assertions.assertEquals(1, node.getChildren().size());
        Assertions.assertEquals("B", node.getChildren().get(0).getName());
    }
}
