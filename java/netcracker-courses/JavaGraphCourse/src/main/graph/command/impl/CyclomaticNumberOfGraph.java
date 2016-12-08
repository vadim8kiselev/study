package main.graph.command.impl;

import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.vertex.unweight.Vertex;

import java.util.LinkedHashSet;
import java.util.Map;

//II 11
public class CyclomaticNumberOfGraph implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... params) {
        Map<Integer, LinkedHashSet<Vertex>> grapStructure = graph.getGraph();
        Integer cyclomaticNumber = 0;

        cyclomaticNumber -= graph.getGraph().size();

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> item : grapStructure.entrySet()) {
            cyclomaticNumber += item.getValue().size();
        }

        return cyclomaticNumber + 1;
    }
}
