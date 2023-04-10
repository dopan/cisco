# Task 1 & 2

To compile project and run test with maven `mvn clean test`.

## Assumptions
From requirements:
- graphs are acyclic
- node.getChildren() is not null and has no null entries

Further assumptions:
- graph is directed (children have no link to parent)
- from starting node there exists path to every node in the graph (no disconnected parts of graph)

Therefore, the most general data structure that fits into these assumptions is Directed Acyclic Graph.

## Solution
`GraphUtils.java` contains both methods `List<GNode> walkGraph(GNode node)` and `List<List<GNode>> paths(GNode node)`.

`List<GNode> walkGraph(GNode node)` uses Depth-First-Search over Breadth-First-Search because:
- it is easier to understand and implement (follows directions of a graph)
- have the same liner time complexity and in most cases better memory complexity (even though in the worst case it's linear as well)
- in Direct Acyclic Graphs DFS has no disadvantage compared to BFS (cannot run into cycle issues)

`List<List<GNode>> paths(GNode node)`

## Tests
Tests are located in `GraphUtilsTest.java` testing following data structures:
- single node
- linear graph
- binary tree
- tree
- Directed Acyclic Graph 1
![Alt text](/docs/dag1.png)
- Directed Acyclic Graph 2
![Alt text](/docs/dag2.png)









