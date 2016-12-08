package main.graph.command;

import main.graph.Graph;
import main.graph.vertex.unweight.Vertex;

public interface Executor {

    Object execute(Graph graph, Vertex... params);
}
