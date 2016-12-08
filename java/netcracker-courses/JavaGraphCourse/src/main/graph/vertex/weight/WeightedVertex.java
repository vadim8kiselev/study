package main.graph.vertex.weight;


import main.graph.vertex.unweight.Vertex;

public class WeightedVertex extends Vertex {

    private WeightedVertex(Integer vertexID, Integer weight) {
        super(vertexID);
        this.weight = weight;
    }

    public static WeightedVertex create(Integer vertexID, Integer weight) {
        return new WeightedVertex(vertexID, weight);
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return vertexID.toString() + "-" + weight.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        WeightedVertex vertex = (WeightedVertex) o;
        return vertexID.equals(vertex.getVertexID()) && weight.equals(vertex.getWeight());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 31 * weight.hashCode();
    }
}
