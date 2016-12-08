package main.graph.strategy.parse.weight;

import main.graph.strategy.parse.GraphWeightStrategy;
import main.graph.vertex.weight.WeightedVertex;

import java.util.LinkedHashSet;
import java.util.List;

public class WeightedGraphWeightStrategy implements GraphWeightStrategy<WeightedVertex> {

    @Override
    public LinkedHashSet<WeightedVertex> parseLine(List<String> stringVertexes) {
        LinkedHashSet<WeightedVertex> vertexes = new LinkedHashSet<>();

        for (String value : stringVertexes) {
            String[] data = value.split("-");
            vertexes.add(WeightedVertex.create(Integer.parseInt(data[0]), Integer.parseInt(data[1])));
        }

        return vertexes;
    }

    @Override
    public WeightedVertex createVertex(Integer... params) {
        return (params.length >= 2) ? WeightedVertex.create(params[0], params[1]) : null;
    }

    @Override
    public boolean isWeighted() {
        return true;
    }
}
