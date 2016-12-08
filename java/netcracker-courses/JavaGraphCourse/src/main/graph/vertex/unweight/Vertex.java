package main.graph.vertex.unweight;


public class Vertex {

    protected Integer vertexID;

    protected Integer weight;

    protected Vertex(Integer vertexID) {
        this.vertexID = vertexID;
        this.weight = 1;
    }

    public static final Vertex create(Integer vertexID) {
        return new Vertex(vertexID);
    }

    public Integer getVertexID() {
        return vertexID;
    }

    @Override
    public String toString() {
        return vertexID.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vertex vertex = (Vertex) o;
        return vertexID.equals(vertex.getVertexID());
    }

    @Override
    public int hashCode() {
        return vertexID.hashCode();
    }
}
