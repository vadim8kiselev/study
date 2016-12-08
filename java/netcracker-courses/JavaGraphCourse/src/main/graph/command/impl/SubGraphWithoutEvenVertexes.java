package main.graph.command.impl;

import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.vertex.unweight.Vertex;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

// Ib 9
public class SubGraphWithoutEvenVertexes implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... params) {

        for (Integer vertexWithEvenDegree : getVertexesWithEvenDegrees(graph.getGraph(), graph.isDirected())) {
            graph.deleteVertex(vertexWithEvenDegree);
        }

        return graph.getGraph();
    }

    private Set<Integer> getVertexesWithEvenDegrees(Map<Integer, LinkedHashSet<Vertex>> graph, boolean isDirected) {

        Set<Integer> vertexesWithEvenDegree = new HashSet<Integer>();

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> item : graph.entrySet()) {
            if (getVertexDegree(graph, item.getKey(), isDirected) % 2 == 0) {
                vertexesWithEvenDegree.add(item.getKey());
            }
        }

        return vertexesWithEvenDegree;
    }

    private int getVertexDegree(Map<Integer, LinkedHashSet<Vertex>> graph, Integer vertexID, boolean isDirected) {
        int degree = 0;
        degree += graph.get(vertexID).size();

        if (isDirected) {
            for (Map.Entry<Integer, LinkedHashSet<Vertex>> item : graph.entrySet()) {
                if (!item.getKey().equals(vertexID)) {
                    for (Vertex vertex : item.getValue()) {
                        if (vertex.getVertexID().equals(vertexID)) {
                            degree += 1;
                        }
                    }
                }
            }
        }

        return degree;
    }
}
