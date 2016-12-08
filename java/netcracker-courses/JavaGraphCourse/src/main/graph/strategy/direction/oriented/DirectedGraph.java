package main.graph.strategy.direction.oriented;

import main.graph.Graph;
import main.graph.strategy.direction.GraphDirectionStrategy;

public class DirectedGraph implements GraphDirectionStrategy {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public void transform(Graph graph) {
        return;
    }
}
