package org.pojo.graphs;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.pojo.graphs.matrix.Matrix;

import java.util.List;

public class Graph {

    private final Edges edges;

    public Graph(final Edges edges) {
        this.edges = edges;
    }

    public Graph(final List<Edge> edges) {
        this.edges = new Edges(edges);
    }

    @Override
    public String toString() {
        return edges.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Graph graph = (Graph) o;

        return new EqualsBuilder().append(edges, graph.edges)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(edges)
                                    .toHashCode();
    }

    public Matrix calculateAdjacencyMatrix() {
        final int numberOfEdges = edges.getNumberOfEdges();
        final Matrix adjacencyMatrix = new Matrix(numberOfEdges);
        edges.stream()
             .forEach(edge -> {
                 switch (edge.getEdgeType()) {
                     case LEFT_DIRECTED:
                         adjacencyMatrix.setValue(1, edge.getFrom() - 1, edge.getTo() - 1);
                         break;
                     case RIGHT_DIRECTED:
                         adjacencyMatrix.setValue(1, edge.getTo() - 1, edge.getFrom() - 1);
                         break;
                     case UNDIRECTED:
                         adjacencyMatrix.setValue(1, edge.getFrom() - 1, edge.getTo() - 1);
                         adjacencyMatrix.setValue(1, edge.getTo() - 1, edge.getFrom() - 1);
                         break;
                 }
             });
        return adjacencyMatrix;
    }

}
