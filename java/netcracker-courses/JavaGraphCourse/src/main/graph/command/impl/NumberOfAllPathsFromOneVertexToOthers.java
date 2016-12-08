package main.graph.command.impl;

import main.context.BeanContextHolder;
import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.util.GraphUtils;
import main.graph.vertex.unweight.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//II 12
public class NumberOfAllPathsFromOneVertexToOthers implements Executor {

    private Map<Integer, Boolean> used;

    @Override
    public Object execute(Graph graph, Vertex... vertexes) {
        if (vertexes.length == 0) {
            throw new IllegalArgumentException("No arguments given. Here must be one argument");
        }

        GraphUtils graphUtils = BeanContextHolder.getInstance(GraphUtils.class);

        List<ArrayList<Integer>> allPathsFromOneVertexToAnotherVertex = new ArrayList<ArrayList<Integer>>();

        for (Integer vertex : graph.getGraph().keySet()) {
            if (!vertex.equals(vertexes[0].getVertexID())) {
                allPathsFromOneVertexToAnotherVertex.addAll(graphUtils.getAllPathsFromOneToAnother(graph,
                        vertexes[0].getVertexID(), vertex)
                );
            }
        }

        return allPathsFromOneVertexToAnotherVertex.size();
    }
}

