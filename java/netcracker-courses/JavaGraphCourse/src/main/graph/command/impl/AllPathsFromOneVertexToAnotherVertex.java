package main.graph.command.impl;

import main.context.BeanContextHolder;
import main.graph.Graph;
import main.graph.command.Executor;
import main.graph.util.GraphUtils;
import main.graph.vertex.unweight.Vertex;

//II 13
public class AllPathsFromOneVertexToAnotherVertex implements Executor {

    @Override
    public Object execute(Graph graph, Vertex... vertexes) {
        if (vertexes.length < 2) {
            throw new IllegalArgumentException("No arguments given. Here must be two arguments");
        }

        GraphUtils graphUtils = BeanContextHolder.getInstance(GraphUtils.class);

        return graphUtils.getAllPathsFromOneToAnother(graph,
                vertexes[0].getVertexID(), vertexes[1].getVertexID());
    }
}
