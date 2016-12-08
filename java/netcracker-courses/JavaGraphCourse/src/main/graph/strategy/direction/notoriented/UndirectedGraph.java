package main.graph.strategy.direction.notoriented;

import main.graph.Graph;
import main.graph.strategy.direction.GraphDirectionStrategy;
import main.graph.vertex.unweight.Vertex;
import main.graph.vertex.weight.WeightedVertex;

import java.util.LinkedHashSet;
import java.util.Map;

public class UndirectedGraph implements GraphDirectionStrategy {

    @Override
    public boolean isDirected() {
        return false;
    }

    @Override
    public void transform(Graph graph) {
        Map<Integer, LinkedHashSet<Vertex>> graphStructure = graph.getGraph();

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> entry : graphStructure.entrySet()) {
            for (Vertex item : entry.getValue()) {
                if (graph.isWeighted()) {
                    graphStructure.get(item.getVertexID())
                            .add(WeightedVertex.create(entry.getKey(), ((WeightedVertex) item).getWeight()));
                } else {
                    graphStructure.get(item.getVertexID())
                            .add(Vertex.create(entry.getKey()));
                }
            }
        }
    }
}
