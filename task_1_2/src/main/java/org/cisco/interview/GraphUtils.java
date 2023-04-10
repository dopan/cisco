package org.cisco.interview;

import org.cisco.interview.model.GNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GraphUtils {

    /**
     * Walks through Directed Acyclic Graph in Deep-First-Search manner and returns list of nodes in order they were visited.
     * Each node appears in the List exactly once (no duplicates).
     *
     * @param node - starting node
     * @return List - list of GNodes in order they were walked through
     */
    public static List<GNode> walkGraph(GNode node) {
        Set<GNode> visited = new HashSet<>();
        List<GNode> result = new ArrayList<>();
        deepFirstSearch(node, visited, result);
        return result;
    }

    /**
     * Return all possible paths in a given Directed Acyclic Graph.
     *
     * @param node - starting node
     * @return List - list of all possible paths represented by list of GNodes
     */
    public static List<List<GNode>> paths(GNode node) {
        List<List<GNode>> allPaths = new ArrayList<>();
        if (node == null) {
            return allPaths;
        }
        List<GNode> currentPath = new ArrayList<>();
        currentPath.add(node);
        pathsHelper(node, currentPath, allPaths);
        return allPaths;
    }

    /**
     * Helper method for walk through DAG. Checks if node was already visited and if not,
     * visits the node and recursively calls method for it's every child.
     *
     * @param node    - current node
     * @param visited - set of already visited nodes
     * @param result  - list of nodes in order they were visited
     */
    private static void deepFirstSearch(GNode node, Set<GNode> visited, List<GNode> result) {
        if (!visited.contains(node)) {
            visited.add(node);
            result.add(node);
            for (GNode child : node.getChildren()) {
                deepFirstSearch(child, visited, result);
            }
        }
    }

    /**
     * Helper method for searching possible paths. If current node is leaf, we have found path.
     * If node is not leaf, add node to current path and recursively calls method for all children.
     * When backtracking (all children were processed, and we go back up the graph),
     * it's important to remove current node (we are returning through) from current path.
     *
     * @param node
     * @param currentPath
     * @param allPaths
     */
    private static void pathsHelper(GNode node, List<GNode> currentPath, List<List<GNode>> allPaths) {
        if (node.getChildren().isEmpty()) {
            allPaths.add(new ArrayList<>(currentPath));
            return;
        }
        for (GNode child : node.getChildren()) {
            currentPath.add(child);
            pathsHelper(child, currentPath, allPaths);
            currentPath.remove(currentPath.size() - 1);
        }
    }


}
