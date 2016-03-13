package org.pojo.graphs;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Edge {
    private final long from;
    private final long to;
    private final EdgeType edgeType;

    public Edge(final long from, final long to) {
        this.from = from;
        this.to = to;
        this.edgeType = EdgeType.UNDIRECTED;
    }

    public Edge(final long from, final long to, final EdgeType edgeType) {
        this.from = from;
        this.to = to;
        this.edgeType = edgeType;
    }

    public long getFrom() {
        return from;
    }

    public long getTo() {
        return to;
    }

    public EdgeType getEdgeType() {
        return edgeType;
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
