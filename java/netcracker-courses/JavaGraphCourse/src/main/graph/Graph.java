package main.graph;

import main.graph.command.Executor;
import main.graph.strategy.direction.GraphDirectionStrategy;
import main.graph.strategy.parse.GraphWeightStrategy;
import main.graph.vertex.unweight.Vertex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Graph implements Cloneable {

    private Map<Integer, LinkedHashSet<Vertex>> graph = new HashMap<Integer, LinkedHashSet<Vertex>>();

    private GraphWeightStrategy graphWeightStrategy;

    private GraphDirectionStrategy graphDirectionStrategy;

    private Graph() {
        // private constructor
    }

    public static Graph.Builder newBuilder() {
        return new Graph().new Builder();
    }

    public Graph cloneGraph() {
        try {
            return (Graph) this.clone();
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }

    public boolean isDirected() {
        return graphDirectionStrategy.isDirected();
    }

    public boolean isWeighted() {
        return graphWeightStrategy.isWeighted();
    }

    public void addVertex(Integer vertexID) {
        if (!graph.keySet().contains(vertexID)) {
            graph.put(vertexID, new LinkedHashSet<Vertex>());
        }
    }

    public void deleteVertex(Integer vertexID) {
        graph.remove(vertexID);

        for (Map.Entry<Integer, LinkedHashSet<Vertex>> entry : graph.entrySet()) {
            Iterator<Vertex> iterator = entry.getValue().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getVertexID().equals(vertexID)) {
                    iterator.remove();
                }
            }
        }
    }

    public void addEdge(Integer sourceVertexId, Integer... vertexParams) {
        Vertex targetVertexId = graphWeightStrategy.createVertex(vertexParams);
        if (graph.keySet().contains(sourceVertexId) &&
                graph.keySet().contains(targetVertexId.getVertexID())) {
            graph.get(sourceVertexId).add(targetVertexId);
        }
    }

    public void deleteEdge(Integer sourceVertexId, Integer... vertexParams) {
        Vertex targetVertexId = graphWeightStrategy.createVertex(vertexParams);
        if (graph.keySet().contains(sourceVertexId)) {
            graph.get(sourceVertexId).remove(targetVertexId);
        }
    }

    public Map<Integer, LinkedHashSet<Vertex>> getGraph() {
        return graph;
    }

    public <T extends Vertex> Object executeCommand(Executor executor, T... params) {
        return executor.execute(this.cloneGraph(), params);
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public Builder parseFromFile(String fileName) {

            if (Graph.this.graphWeightStrategy == null || Graph.this.graphDirectionStrategy == null) {
                System.out.println("Can not parse graph from file, because the parse strategies are not defined");
                System.out.println("You'll get the last version of object");
                return this;
            }

            try (BufferedReader fstream = new BufferedReader(new FileReader(fileName))) {

                Integer size = Integer.parseInt(fstream.readLine());

                for (int index = 0; index < size; index++) {
                    List<String> data = Arrays.asList(fstream.readLine().split(" "));

                    LinkedHashSet<Vertex> connectedVertexes =
                            Graph.this.graphWeightStrategy.parseLine(data.subList(1, data.size()));

                    Graph.this.graph.put(Integer.parseInt(data.get(0)), connectedVertexes);
                }
                Graph.this.graphDirectionStrategy.transform(Graph.this);

            } catch (IOException exception) {
                System.out.println("Can not parse graph from file by reason, you'll get the last version of object");
            } finally {
                return this;
            }
        }

        public Builder setGraphParseStrategy(GraphWeightStrategy graphWeightStrategy) {
            Graph.this.graphWeightStrategy = graphWeightStrategy;
            return this;
        }

        public Builder setGraphDirectionStrategy(GraphDirectionStrategy graphDirectionStrategy) {
            Graph.this.graphDirectionStrategy = graphDirectionStrategy;
            return this;
        }

        public Graph build() {
            if (Graph.this.graphWeightStrategy != null &&
                    Graph.this.graphDirectionStrategy != null) {
                return Graph.this;
            }

            System.out.println("Mandatory fields can not be empty");
            return new Graph();
        }
    }
}
