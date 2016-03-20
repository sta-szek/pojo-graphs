package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pojo.graphs.io.GraphReader;
import org.pojo.graphs.matrix.Matrix;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class GraphTest {

    @Test
    public void shouldReturnSameHashCodes() {
        // given
        final Graph graph1 = new Graph(new Edges());
        final Graph graph2 = new Graph(new Edges());

        // when
        final int result1 = graph1.hashCode();
        final int result2 = graph2.hashCode();

        // then
        assertThat(result1).isEqualTo(result2);
    }

    @Test
    public void shouldCalculateAdjacencyMatrix() throws URISyntaxException, IOException {
        // given
        final URI uri = this.getClass()
                            .getResource("/graphs/UndirectedGraph.txt")
                            .toURI();
        final GraphReader graphReader = new GraphReader();
        final Graph graph = graphReader.read(Paths.get(uri));
        final Matrix expectedAdjacencyMatrix = new Matrix(new int[][]{{0, 1, 1, 0, 1},
                                                                      {1, 0, 1, 1, 1},
                                                                      {1, 1, 0, 1, 0},
                                                                      {0, 1, 1, 0, 1},
                                                                      {1, 1, 0, 1, 0}});
        // when
        final Matrix adjacencyMatrix = graph.calculateAdjacencyMatrix();

        // then
        assertThat(adjacencyMatrix).isEqualTo(expectedAdjacencyMatrix);
    }

    @Test
    @Parameters(method = "getObjectForEqual")
    public void shouldEqual(final Graph graph1, final Graph graph2) {
        // given

        // when
        final boolean result = graph1.equals(graph2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters(method = "getObjectForUnequal")
    public void shouldNotEqual(final Graph graph1, final Graph graph2) {
        // given

        // when
        final boolean result = graph1.equals(graph2);

        // then
        assertThat(result).isFalse();
    }

    private Object getObjectForEqual() {
        final Edges edges = new Edges();
        final Graph graph = new Graph(edges);
        return new Object[][]{{new Graph(edges), new Graph(edges)},
                              {graph, graph}};
    }

    private Object getObjectForUnequal() {
        final Edges edges1 = new Edges();
        final Edges edges2 = new Edges(new Edge(1, 2));
        return new Object[][]{{new Graph(edges1), null},
                              {new Graph(edges1), new Graph(edges2)}};
    }
}