package org.cisco.interview;

import org.cisco.interview.model.GNode;
import org.cisco.interview.model.GNodeImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GraphUtilsTest {

    @Test
    void testSingleNode() {
        GNode node = new GNodeImpl("A", new ArrayList<>());
        Assertions.assertEquals("A", nodesListAsString(GraphUtils.walkGraph(node)));

        Assertions.assertEquals(new ArrayList<>(List.of(new ArrayList<>(List.of(
                        new GNodeImpl("A", new ArrayList<>()))))),
                GraphUtils.paths(node));
    }

    @Test
    void testLinearDAG() {
        GNode linerGraph = new GNodeImpl("A", new ArrayList<>(List.of(
                new GNodeImpl("B", new ArrayList<>(List.of(new GNodeImpl("C", new ArrayList<>())))))
        ));
        Assertions.assertEquals("ABC", nodesListAsString(GraphUtils.walkGraph(linerGraph)));
        List<List<GNode>> result = GraphUtils.paths(linerGraph);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("ABC", nodesListAsString(result.get(0)));
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
        Assertions.assertEquals("ABDEGHCF", nodesListAsString(GraphUtils.walkGraph(a)));

        // paths
        List<List<GNode>> result = GraphUtils.paths(a);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("ABD", nodesListAsString(result.get(0)));
        Assertions.assertEquals("ABEG", nodesListAsString(result.get(1)));
        Assertions.assertEquals("ABEH", nodesListAsString(result.get(2)));
        Assertions.assertEquals("ACF", nodesListAsString(result.get(3)));
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
        Assertions.assertEquals("ABCEFGHIJD", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        Assertions.assertEquals(7, result.size());
        Assertions.assertEquals("AB", nodesListAsString(result.get(0)));
        Assertions.assertEquals("ACE", nodesListAsString(result.get(1)));
        Assertions.assertEquals("ACF", nodesListAsString(result.get(2)));
        Assertions.assertEquals("ACGH", nodesListAsString(result.get(3)));
        Assertions.assertEquals("ACGI", nodesListAsString(result.get(4)));
        Assertions.assertEquals("ACGJ", nodesListAsString(result.get(5)));
        Assertions.assertEquals("AD", nodesListAsString(result.get(6)));
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
        Assertions.assertEquals("ABEFDGC", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        Assertions.assertEquals(10, result.size());
        Assertions.assertEquals("ABEF", nodesListAsString(result.get(0)));
        Assertions.assertEquals("ADBEF", nodesListAsString(result.get(1)));
        Assertions.assertEquals("ADEF", nodesListAsString(result.get(2)));
        Assertions.assertEquals("ADF", nodesListAsString(result.get(3)));
        Assertions.assertEquals("ADGF", nodesListAsString(result.get(4)));
        Assertions.assertEquals("ACDBEF", nodesListAsString(result.get(5)));
        Assertions.assertEquals("ACDEF", nodesListAsString(result.get(6)));
        Assertions.assertEquals("ACDF", nodesListAsString(result.get(7)));
        Assertions.assertEquals("ACDGF", nodesListAsString(result.get(8)));
        Assertions.assertEquals("ACGF", nodesListAsString(result.get(9)));
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
        Assertions.assertEquals("ABEFDC", nodesListAsString(GraphUtils.walkGraph(a)));

        //paths
        List<List<GNode>> result = GraphUtils.paths(a);
        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("ABEF", nodesListAsString(result.get(0)));
        Assertions.assertEquals("ABD", nodesListAsString(result.get(1)));
        Assertions.assertEquals("ABC", nodesListAsString(result.get(2)));
        Assertions.assertEquals("AC", nodesListAsString(result.get(3)));
    }

    private String nodesListAsString(List<GNode> result) {
        StringBuilder namesOrder = new StringBuilder();
        for (GNode node : result) {
            namesOrder.append(node.getName());
        }
        return namesOrder.toString();
    }
}
