package org.pojo.graphs;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Edge {
    private final int from;
    private final int to;
    private final EdgeType edgeType;

    public Edge(final int from, final int to) {
        this(from, to, EdgeType.UNDIRECTED);
    }

    public Edge(final int from, final int to, final EdgeType edgeType) {
        if (from < 1 || to < 1) {
            throw new InvalidEdgeDeclaration("Wierzchołki muszą być większe od 0!");
        }
        this.from = from;
        this.to = to;
        this.edgeType = edgeType;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public EdgeType getEdgeType() {
        return edgeType;
    }

    public boolean isUndirected() {
        return EdgeType.UNDIRECTED == edgeType;
    }

    @Override
    public String toString() {
        return from + edgeType.toString() + to;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Edge edge = (Edge) o;

        return new EqualsBuilder().append(from, edge.from)
                                  .append(to, edge.to)
                                  .append(edgeType, edge.edgeType)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(from)
                                    .append(to)
                                    .append(edgeType)
                                    .toHashCode();
    }
}
