package org.pojo.graphs;


import java.util.Arrays;
import java.util.Optional;

public enum EdgeType {
    LEFT_DIRECTED("<-"),
    RIGHT_DIRECTED("->"),
    UNDIRECTED("<->");

    private final String direction;

    EdgeType(final String direction) {
        this.direction = direction;
    }

    public static EdgeType getByDirection(final String direction) {
        final Optional<EdgeType> first = Arrays.stream(EdgeType.values())
                                               .filter(edgeType -> edgeType.direction.equals(direction))
                                               .findFirst();
        return first.orElse(UNDIRECTED);
    }

    public boolean isUndirected() {
        return this.equals(UNDIRECTED);
    }

    @Override
    public String toString() {
        return direction;
    }
}
