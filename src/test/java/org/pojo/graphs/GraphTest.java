package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(JUnitParamsRunner.class)
public class GraphTest {

    @Test
    public void shouldCreateGraphFromFile() throws IOException, URISyntaxException {
        // given
        final URI uri = this.getClass()
                            .getResource("/graphs/SmallGraph.txt")
                            .toURI();
        final Path path = new File(uri).toPath();
        final String expectedGraph = "1->2\n" +
                                     "2<-3\n" +
                                     "3<->4\n" +
                                     "4<->5";
        // when
        final Graph graph = new Graph(path);

        // then
        assertThat(graph.toString()).isEqualTo(expectedGraph);
    }

    @Test
    public void shouldThrowException_WhenWrongDeclaration() throws IOException, URISyntaxException {
        // given
        final URI uri = this.getClass()
                            .getResource("/graphs/WrongDeclaration.txt")
                            .toURI();
        final Path path = new File(uri).toPath();

        // when
        final Throwable result = catchThrowable(() -> new Graph(path));

        // then
        assertThat(result).isInstanceOf(InvalidGraphFileException.class);
    }

    @Test
    public void shouldReturnSameHashCodes(){
        // given
        Graph graph1 = new Graph(new Edges());
        Graph graph2 = new Graph(new Edges());

        // when
        int result1 = graph1.hashCode();
        int result2 = graph2.hashCode();

        // then
        assertThat(result1).isEqualTo(result2);
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
        final Edges edges2 = new Edges(new Edge(1,2));
        return new Object[][]{{new Graph(edges1), null},
                              {new Graph(edges1), new Graph(edges2)}};
    }
}