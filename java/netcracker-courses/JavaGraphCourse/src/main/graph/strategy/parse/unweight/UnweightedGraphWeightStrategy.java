package main.graph.strategy.parse.unweight;

import main.graph.strategy.parse.GraphWeightStrategy;
import main.graph.vertex.unweight.Vertex;

import java.util.LinkedHashSet;
import java.util.List;

public class UnweightedGraphWeightStrategy implements GraphWeightStrategy<Vertex> {

    @Override
    public LinkedHashSet<Vertex> parseLine(List<String> stringVertexes) {
        LinkedHashSet<Vertex> vertexes = new LinkedHashSet<>();

        for (String value : stringVertexes) {
            vertexes.add(Vertex.create(Integer.parseInt(value)));
        }

        return vertexes;
    }

    @Override
    public Vertex createVertex(Integer... params) {
        return (params.length >= 1) ? Vertex.create(params[0]) : null;
    }

    @Override
    public boolean isWeighted() {
        return false;
    }
}
