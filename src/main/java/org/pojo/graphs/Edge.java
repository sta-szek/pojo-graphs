package org.pojo.graphs;


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
}
