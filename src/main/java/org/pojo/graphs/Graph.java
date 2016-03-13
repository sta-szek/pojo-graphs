package org.pojo.graphs;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

    private List<Edge> edges = new ArrayList<>();

    public Graph(final List<Edge> edges) {
        this.edges = edges;
    }

    public Graph(final Path path) throws IOException {
        Files.lines(path)
             .forEach(this::convertAndAddToEdges);
    }

    @Override
    public String toString() {
        return edges.stream()
                    .map(Edge::toString)
                    .collect(Collectors.joining("\n"));
    }

    private void convertAndAddToEdges(final String line) {
        final Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            final long from = Long.parseLong(matcher.group(FROM));
            final EdgeType edgeType = EdgeType.getByDirection(matcher.group(DIRECTION));
            final long to = Long.parseLong(matcher.group(TO));
            final Edge edge = new Edge(from, to, edgeType);
            edges.add(edge);
        } else {
            throw new InvalidGraphFileException("Plik zawiera nieprawidłowe wpisy");
        }
    }
}
