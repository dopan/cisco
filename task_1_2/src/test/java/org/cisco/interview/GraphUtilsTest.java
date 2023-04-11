package org.cisco.interview;

import org.cisco.interview.model.GNode;
import org.cisco.interview.model.GNodeImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphUtilsTest {

    @Test
    void testSingleNode() {
        GNode node = new GNodeImpl("A", new ArrayList<>());
        assertEquals("A", nodesListAsString(GraphUtils.walkGraph(node)));

        assertEquals(new ArrayList<>(List.of(new ArrayList<>(List.of(
                        new GNodeImpl("A", new ArrayList<>()))))),
                GraphUtils.paths(node));
    }

    @Test
    void testLinearDAG() {
        GNode linerGraph = new GNodeImpl("A", new ArrayList<>(List.of(
                new GNodeImpl("B", new ArrayList<>(List.of(new GNodeImpl("C", new ArrayList<>())))))
        ));
        assertEquals("ABC", nodesListAsString(GraphUtils.walkGraph(linerGraph)));
        List<List<GNode>> result = GraphUtils.paths(linerGraph);
        assertEquals(1, result.size());
        assertEquals("ABC", nodesListAsString(result.get(0)));
    }

    @Test
    void testBinaryTree() {
        //build test data
        GNode a = new GNodeImpl("A", new ArrayList<>());
        GNode b = new GNodeImpl("B", new ArrayList<>());
        GNode c = new GNodeImpl("C", new ArrayList<>());
        GNode d = new GNodeImpl("D", new ArrayList<>());
        GNode e = new GNodeImpl("E", new ArrayList<>());
        GNode f = new GNodeImpl("F", new ArrayList<>());
        GNode g = new GNodeImpl("G", new ArrayList<>());
        GNode h = new GNodeImpl("H", new ArrayList<>());
        a.getChildren().add(b);
        a.getChildren().add(c);
        b.getChildren().add(d);
        b.getChildren().add(e);
        c.getChildren().add(f);
        e.getChildren().add(g);
        e.getChildren().add(h);

        // walk
        assertEquals("ABDEGHCF", nodesListAsString(GraphUtils.walkGraph(a)));

        // paths
        List<List<GNode>> result = GraphUtils.paths(a);
        assertEquals(4, result.size());
        assertEquals("ABD", nodesListAsString(result.get(0)));
        assertEquals("ABEG", nodesListAsString(result.get(1)));
        assertEquals("ABEH", nodesListAsString(result.get(2)));
        assertEquals("ACF", nodesListAsString(result.get(3)));
    }

    @Test
    void testTree() {
        GNode a = new GNodeImpl("A", new ArrayList<>());
        GNode b = new GNodeImpl("B", new ArrayList<>());
        GNode c = new GNodeImpl("C", new ArrayList<>());
        GNode d = new GNodeImpl("D", new ArrayList<>());
        GNode e = new GNodeImpl("E", new ArrayList<>());
        GNode f = new GNodeImpl("F", new ArrayList<>());
        GNode g = new GNodeImpl("G", new ArrayList<>());
        GNode h = new GNodeImpl("H", new ArrayList<>());
        GNode i = new GNodeImpl("I", new ArrayList<>());
        GNode j = new GNodeImpl("J", new ArrayList<>());
        a.getChildren().add(b);
        a.getChildren().add(c);
        a.getChildren().add(d);
        c.getChildren().add(e);
        c.getChildren().add(f);
        c.getChildren().add(g);
        g.getChildren().add(h);
        g.getChildren().add(i);
        g.getChildren().add(j);

        //walk
        assertEquals("ABCEFGHIJD", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        assertEquals(7, result.size());
        assertEquals("AB", nodesListAsString(result.get(0)));
        assertEquals("ACE", nodesListAsString(result.get(1)));
        assertEquals("ACF", nodesListAsString(result.get(2)));
        assertEquals("ACGH", nodesListAsString(result.get(3)));
        assertEquals("ACGI", nodesListAsString(result.get(4)));
        assertEquals("ACGJ", nodesListAsString(result.get(5)));
        assertEquals("AD", nodesListAsString(result.get(6)));
    }

    @Test
    void testDirectedAcyclicGraph() {
        GNode a = new GNodeImpl("A", new ArrayList<>());
        GNode b = new GNodeImpl("B", new ArrayList<>());
        GNode c = new GNodeImpl("C", new ArrayList<>());
        GNode d = new GNodeImpl("D", new ArrayList<>());
        GNode e = new GNodeImpl("E", new ArrayList<>());
        GNode f = new GNodeImpl("F", new ArrayList<>());
        GNode g = new GNodeImpl("G", new ArrayList<>());
        a.getChildren().add(b);
        a.getChildren().add(d);
        a.getChildren().add(c);
        b.getChildren().add(e);
        c.getChildren().add(d);
        c.getChildren().add(g);
        d.getChildren().add(b);
        d.getChildren().add(e);
        d.getChildren().add(f);
        d.getChildren().add(g);
        e.getChildren().add(f);
        g.getChildren().add(f);

        //walk
        assertEquals("ABEFDGC", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        assertEquals(10, result.size());
        assertEquals("ABEF", nodesListAsString(result.get(0)));
        assertEquals("ADBEF", nodesListAsString(result.get(1)));
        assertEquals("ADEF", nodesListAsString(result.get(2)));
        assertEquals("ADF", nodesListAsString(result.get(3)));
        assertEquals("ADGF", nodesListAsString(result.get(4)));
        assertEquals("ACDBEF", nodesListAsString(result.get(5)));
        assertEquals("ACDEF", nodesListAsString(result.get(6)));
        assertEquals("ACDF", nodesListAsString(result.get(7)));
        assertEquals("ACDGF", nodesListAsString(result.get(8)));
        assertEquals("ACGF", nodesListAsString(result.get(9)));
    }

    @Test
    void testDirectedAcyclicGraph2() {
        GNode a = new GNodeImpl("A", new ArrayList<>());
        GNode b = new GNodeImpl("B", new ArrayList<>());
        GNode c = new GNodeImpl("C", new ArrayList<>());
        GNode d = new GNodeImpl("D", new ArrayList<>());
        GNode e = new GNodeImpl("E", new ArrayList<>());
        GNode f = new GNodeImpl("F", new ArrayList<>());

        a.getChildren().add(b);
        a.getChildren().add(c);
        b.getChildren().add(e);
        b.getChildren().add(d);
        b.getChildren().add(c);
        e.getChildren().add(f);

        //walk
        assertEquals("ABEFDC", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        assertEquals(4, result.size());
        assertEquals("ABEF", nodesListAsString(result.get(0)));
        assertEquals("ABD", nodesListAsString(result.get(1)));
        assertEquals("ABC", nodesListAsString(result.get(2)));
        assertEquals("AC", nodesListAsString(result.get(3)));
    }

    private String nodesListAsString(List<GNode> result) {
        StringBuilder namesOrder = new StringBuilder();
        for (GNode node : result) {
            namesOrder.append(node.getName());
        }
        return namesOrder.toString();
    }
}
