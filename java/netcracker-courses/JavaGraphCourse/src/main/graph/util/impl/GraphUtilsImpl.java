package main.graph.util.impl;

import main.context.Bean;
import main.graph.Graph;
import main.graph.util.GraphUtils;
import main.graph.vertex.unweight.Vertex;

import java.util.*;

public class GraphUtilsImpl extends Bean implements GraphUtils {

    private static final GraphUtilsImpl instance = new GraphUtilsImpl();

    private GraphUtilsImpl() {
        // private constructor
    }

    public static GraphUtilsImpl getInstance() {
        return instance;
    }

    public List<ArrayList<Integer>> getAllPathsFromOneToAnother(Graph graph, Integer from, Integer to) {
        List<ArrayList<Integer>> base = new ArrayList<ArrayList<Integer>>();
        Map<Integer, Boolean> used = new HashMap<>();

        for (Integer vertex : graph.getGraph().keySet()) {
            used.put(vertex, Boolean.FALSE);
        }

        calculateAllPaths(graph, base, new ArrayList<Integer>(Arrays.asList(from)), used, from, to);

        return base;
    }

    private void calculateAllPaths(Graph graph, List<ArrayList<Integer>> base, ArrayList<Integer> tmp, Map<Integer, Boolean> used, Integer current, Integer to) {
        if (current.equals(to)) {
            base.add(new ArrayList<Integer>(tmp));
        } else {
            for (Vertex vertex : graph.getGraph().get(current)) {
                if (used.get(vertex.getVertexID()) == Boolean.FALSE) {
                    used.put(vertex.getVertexID(), Boolean.TRUE);
                    tmp.add(vertex.getVertexID());

                    calculateAllPaths(graph, base, tmp, used, vertex.getVertexID(), to);

                    used.put(vertex.getVertexID(), Boolean.FALSE);
                    tmp.remove(tmp.size() - 1);
                }
            }
        }
    }
}
