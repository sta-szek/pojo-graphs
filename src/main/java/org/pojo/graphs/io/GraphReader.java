package org.pojo.graphs.io;


import org.pojo.graphs.Edge;
import org.pojo.graphs.EdgeType;
import org.pojo.graphs.Graph;
import org.pojo.graphs.InvalidGraphFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GraphReader {

    private static final String FROM = "FROM";
    private static final String TO = "TO";
    private static final String DIRECTION = "DIRECTION";
    private static final String REGEX = "(?<" + FROM + ">\\d)(?<" + DIRECTION + ">(<\\-)|(\\->)|(<\\->)|( ))(?<" + TO + ">\\d)";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public Graph read(final Path path) throws IOException {
        final List<Edge> edges = Files.lines(path)
                                      .map(this::toEdge)
                                      .collect(Collectors.toList());
        return new Graph(edges);
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
