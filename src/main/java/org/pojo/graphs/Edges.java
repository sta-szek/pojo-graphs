package org.pojo.graphs;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Edges {

    private final List<Edge> edges = new ArrayList<>();

    public Edges(final List<Edge> edges) {
        edges.forEach(this::add);
    }

    public Edges(final Edge... edges) {
        Arrays.stream(edges)
              .forEach(this::add);
    }

    public Edges() {}

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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Edges edges1 = (Edges) o;

        return new EqualsBuilder().append(edges, edges1.edges)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(edges)
                                    .toHashCode();
    }

    public int getNumberOfEdges() {
        final List<Integer> listOfEdges = edges.stream()
                                               .map(this::toEdgePair)
                                               .map(Arrays::stream)
                                               .flatMap(IntStream::boxed)
                                               .distinct()
                                               .collect(Collectors.toList());

        final Optional<Integer> min = listOfEdges.stream()
                                                 .min(Integer::compare);

        final Optional<Integer> max = listOfEdges.stream()
                                                 .max(Integer::compare);

        if (min.isPresent() && max.isPresent()) {
            for (int i = min.get(); i < max.get(); i++) {
                if (!listOfEdges.contains(i)) {
                    listOfEdges.add(i);
                }
            }
        }
        listOfEdges.sort(Long::compare);
        return listOfEdges.size();
    }

    public Stream<Edge> stream() {
        return edges.stream();
    }

    private int[] toEdgePair(final Edge edge) {
        return new int[]{edge.getFrom(), edge.getTo()};
    }

    private void removeAndAddNewUndirected(final Edge edge) {
        edges.remove(edge);
        sortAndAdd(new Edge(edge.getFrom(), edge.getTo(), EdgeType.UNDIRECTED));
    }

    private void sortAndAdd(final Edge edge) {
        final int from = edge.getFrom() <= edge.getTo()
                         ? edge.getFrom()
                         : edge.getTo();
        final int to = edge.getFrom() > edge.getTo()
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
