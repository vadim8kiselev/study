package main.graph.command.impl;

import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.vertex.unweight.Vertex;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

//I 14
public class NotConnectedVertexes implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... params) {
        Integer currentVertexID = params[0].getVertexID();

        Set<Integer> foreignVertexes = new LinkedHashSet<Integer>();

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> entry : graph.getGraph().entrySet()) {
            if (!entry.getKey().equals(currentVertexID) && !vertexContains(entry.getValue(), currentVertexID)) {
                foreignVertexes.add(entry.getKey());
            }
        }

        return foreignVertexes;
    }

    private boolean vertexContains(LinkedHashSet<Vertex> list, Integer vertexID) {
        for (Vertex item : list) {
            if (item.getVertexID().equals(vertexID)) {
                return true;
            }
        }
        return false;
    }
}
