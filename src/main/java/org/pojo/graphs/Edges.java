package org.pojo.graphs;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Edges {

    private final List<Edge> edges = new ArrayList<>();

    public Edges(final List<Edge> edges) {
        edges.forEach(this::add);
    }

    public void add(final Edge edge) {
        if (alreadyAdded(edge)) {
            return;
        }
        final Optional<Edge> similarEdge = findSimilarEdge(edge);
        final Optional<Edge> invertedEdge = findInvertedEdge(edge);

        if (similarEdge.isPresent()) {
            final Edge similar = similarEdge.get();
            removeAndAddNewUndirected(similar);
        } else if (invertedEdge.isPresent()) {
            final Edge inverted = invertedEdge.get();
            if (isUndirected(inverted) || isUndirected(edge) || haveSameTypes(edge, inverted)) {
                removeAndAddNewUndirected(inverted);
            }
        } else {
            sortAndAdd(edge);
        }
    }

    @Override
    public String toString() {
        return edges.stream()
                    .map(Edge::toString)
                    .collect(Collectors.joining("\n"));
    }

    private void removeAndAddNewUndirected(final Edge edge) {
        edges.remove(edge);
        sortAndAdd(new Edge(edge.getFrom(), edge.getTo(), EdgeType.UNDIRECTED));
    }

    private void sortAndAdd(final Edge edge) {
        final long from = edge.getFrom() <= edge.getTo()
                          ? edge.getFrom()
                          : edge.getTo();
        final long to = edge.getFrom() > edge.getTo()
                        ? edge.getFrom()
                        : edge.getTo();
        EdgeType edgeType = edge.getEdgeType();
        if (from != edge.getFrom()) {
            if (edge.getEdgeType()
                    .equals(EdgeType.LEFT_DIRECTED)) {
                edgeType = EdgeType.RIGHT_DIRECTED;
            } else if (edge.getEdgeType()
                           .equals(EdgeType.RIGHT_DIRECTED)) {
                edgeType = EdgeType.LEFT_DIRECTED;
            }
        }
        final Edge newEdge = new Edge(from, to, edgeType);
        edges.add(newEdge);
    }

    private boolean haveSameTypes(final Edge edge, final Edge inverted) {
        return inverted.getEdgeType()
                       .equals(edge.getEdgeType());
    }

    private boolean alreadyAdded(final Edge edge) {return edges.contains(edge);}

    private Optional<Edge> findSimilarEdge(final Edge edge) {
        return edges.stream()
                    .filter(each -> each.getFrom() == edge.getFrom())
                    .filter(each -> each.getTo() == edge.getTo())
                    .findFirst();
    }

    private Optional<Edge> findInvertedEdge(final Edge edge) {
        return edges.stream()
                    .filter(each -> each.getFrom() == edge.getTo())
                    .filter(each -> each.getTo() == edge.getFrom())
                    .findFirst();
    }

    private boolean isUndirected(final Edge similar) {
        return similar.getEdgeType()
                      .isUndirected();
    }
}
