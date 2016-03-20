package org.pojo.graphs;


import java.util.Arrays;

public enum EdgeType {
    LEFT_DIRECTED("<-"),
    RIGHT_DIRECTED("->"),
    UNDIRECTED("<->");

    private final String direction;

    EdgeType(final String direction) {
        this.direction = direction;
    }

    public static EdgeType getByDirection(final String direction) {
        return Arrays.stream(EdgeType.values())
                     .filter(edgeType -> edgeType.direction.equals(direction))
                     .findFirst()
                     .orElse(UNDIRECTED);

    }

    public boolean isUndirected() {
        return UNDIRECTED == this;
    }

    @Override
    public String toString() {
        return direction;
    }
}
