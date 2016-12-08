package main;

import main.graph.Graph;
import main.graph.command.CommandFactory;
import main.graph.strategy.direction.GraphDirectionStrategy;
import main.graph.strategy.direction.notoriented.UndirectedGraph;
import main.graph.strategy.direction.oriented.DirectedGraph;
import main.graph.strategy.parse.GraphWeightStrategy;
import main.graph.strategy.parse.unweight.UnweightedGraphWeightStrategy;
import main.graph.strategy.parse.weight.WeightedGraphWeightStrategy;
import main.graph.vertex.unweight.Vertex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class Main {

    private static final GraphWeightStrategy unweightedGraphWeightStrategy = new UnweightedGraphWeightStrategy();
    private static final GraphWeightStrategy weightedGraphWeightStrategy = new WeightedGraphWeightStrategy();

    private static final GraphDirectionStrategy directedGraph = new DirectedGraph();
    private static final GraphDirectionStrategy unirectedGraph = new UndirectedGraph();

    public static void main(String[] args) {

        Graph graph = Graph.newBuilder()
                .setGraphParseStrategy(unweightedGraphWeightStrategy)
                .setGraphDirectionStrategy(directedGraph)
                .parseFromFile("input.txt")
                .build();

        // I 2
        //Integer numberOfOutgoingDegreeOfVertex = (Integer) graph.executeCommand(CommandFactory.getCommand("I 2"), Vertex.create(1));
        //System.out.println("numberOfOutgoingDegreeOfVertex = " + numberOfOutgoingDegreeOfVertex);

        // I 11
        //Set<WeightedVertex> vertexesWithLoop = (Set<WeightedVertex>) graph.executeCommand(CommandFactory.getCommand("I 11"));
        //System.out.println("vertexesWithLoop = " + vertexesWithLoop);

        // I 14
        //Set<Integer> notConnectedVertexes = (Set<Integer>) graph.executeCommand(CommandFactory.getCommand("I 14"), Vertex.create(1));
        //System.out.println("notConnectedVertexes = " + notConnectedVertexes);

        // Ib 9
        //Map<Integer, LinkedHashSet<Vertex>> subGraph = (Map<Integer, LinkedHashSet<Vertex>>) graph.executeCommand(CommandFactory.getCommand("Ib 9"));
        //printGraph(subGraph);

        // II 11
        Integer cyclomaticNumberOfGraph = (Integer) graph.executeCommand(CommandFactory.getCommand("II 11"));
        System.out.println("cyclomaticNumberOfGraph = " + cyclomaticNumberOfGraph);

        // II 12
        Integer numberOfAllPathsFromOneVertexToOthers = (Integer) graph.executeCommand(CommandFactory.getCommand("II 12"), Vertex.create(1));
        System.out.println("numberOfAllPathsFromOneVertexToOthers = " + numberOfAllPathsFromOneVertexToOthers);

        // II 13
        List<ArrayList<Integer>> allPathsFromOneVertexToAnotherVertex = (List<ArrayList<Integer>>) graph.executeCommand(CommandFactory.getCommand("II 13"), Vertex.create(1), Vertex.create(3));
        for (ArrayList<Integer> inner : allPathsFromOneVertexToAnotherVertex) {
            System.out.println(inner);
        }

    }

    private static void printGraph(Map<Integer, LinkedHashSet<Vertex>> graph) {
        for (Map.Entry<Integer, LinkedHashSet<Vertex>> item : graph.entrySet()) {
            System.out.println(item.getKey() + " : " + item.getValue());
        }
    }
}
