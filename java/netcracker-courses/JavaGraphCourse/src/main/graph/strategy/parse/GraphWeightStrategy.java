package main.graph.strategy.parse;

import main.graph.vertex.unweight.Vertex;

import java.util.LinkedHashSet;
import java.util.List;

public interface GraphWeightStrategy<T extends Vertex> {

    LinkedHashSet<T> parseLine(List<String> line);

    T createVertex(Integer... vertexParams);

    boolean isWeighted();
}
