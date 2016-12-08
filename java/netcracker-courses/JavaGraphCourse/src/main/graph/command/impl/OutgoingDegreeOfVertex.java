package main.graph.command.impl;

import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.vertex.unweight.Vertex;

//I 2
public class OutgoingDegreeOfVertex implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... params) {
        Integer currentVertexID = params[0].getVertexID();
        return graph.getGraph().get(currentVertexID).size();
    }
}
