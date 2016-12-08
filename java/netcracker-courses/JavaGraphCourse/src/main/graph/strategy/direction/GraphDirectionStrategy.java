package main.graph.strategy.direction;

import main.graph.Graph;

public interface GraphDirectionStrategy {

    boolean isDirected();

    void transform(Graph graph);
}
