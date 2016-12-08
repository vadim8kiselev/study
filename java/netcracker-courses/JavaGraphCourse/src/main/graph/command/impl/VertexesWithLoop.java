package main.graph.command.impl;

import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.vertex.unweight.Vertex;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//I 11
public class VertexesWithLoop implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... params) {
        Set<Vertex> vertexesWithLoop = new LinkedHashSet<Vertex>();

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> entry : graph.getGraph().entrySet()) {
            for (Vertex item : entry.getValue()) {
                if (item.getVertexID().equals(entry.getKey())) {
                    vertexesWithLoop.add(item);
                }
            }
        }

        return vertexesWithLoop;
    }
}
