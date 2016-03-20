package org.pojo.graphs;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.pojo.graphs.matrix.Matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Graph {

    private static final String FROM = "FROM";
    private static final String TO = "TO";
    private static final String DIRECTION = "DIRECTION";
    private static final String REGEX = "(?<" + FROM + ">\\d)(?<" + DIRECTION + ">(<\\-)|(\\->)|(<\\->)|( ))(?<" + TO + ">\\d)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final Edges edges;

    public Graph(final Edges edges) {
        this.edges = edges;
    }

    public Graph(final List<Edge> edges) {
        this.edges = new Edges(edges);
    }

    public Graph(final Path path) throws IOException {
        final List<Edge> edges = Files.lines(path)
                                      .map(this::toEdge)
                                      .collect(Collectors.toList());
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

    private Edge toEdge(final String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            final int from = Integer.parseInt(matcher.group(FROM));
            final EdgeType edgeType = EdgeType.getByDirection(matcher.group(DIRECTION));
            final int to = Integer.parseInt(matcher.group(TO));
            return new Edge(from, to, edgeType);

        } else {
            throw new InvalidGraphFileException("Plik zawiera nieprawidłowe wpisy");
        }
    }
}
