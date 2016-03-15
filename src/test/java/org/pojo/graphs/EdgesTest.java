package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EdgesTest {

    @Test
    @Parameters(method = "getEdgesForOptimization")
    public void shouldOptimizeEdgesWhenCreatingNewObject(final String expectedString, final Edges edgesToOptimize) {
        // given

        // when
        final String result = edgesToOptimize.toString();

        // then
        assertThat(result).isEqualTo(expectedString);
    }

    @Test
    public void shouldReturnSameHashCodes() {
        // given
        final Edges edge1 = new Edges();
        final Edges edge2 = new Edges();

        // when
        final int result1 = edge1.hashCode();
        final int result2 = edge2.hashCode();

        // then
        assertThat(result1).isEqualTo(result2);
    }

    @Test
    @Parameters(method = "getObjectForEqual")
    public void shouldEqual(final Edges edges1, final Edges edges2) {
        // given

        // when
        final boolean result = edges1.equals(edges2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters(method = "getObjectForUnequal")
    public void shouldNotEqual(final Edges edges1, final Edges edges2) {
        // given

        // when
        final boolean result = edges1.equals(edges2);

        // then
        assertThat(result).isFalse();
    }

    private Object getObjectForEqual() {
        final Edges edges = new Edges();
        return new Object[][]{{new Edges(), new Edges()},
                              {edges, edges}};
    }

    private Object getObjectForUnequal() {
        return new Object[][]{{new Edges(), null},
                              {new Edges(), new Edges(new Edge(1, 2))}};
    }

    private Object getEdgesForOptimization() {
        return new Object[][]{{"1<->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(2, 1, EdgeType.RIGHT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.UNDIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(2, 1, EdgeType.LEFT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.RIGHT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.UNDIRECTED), new Edge(2, 1, EdgeType.LEFT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.LEFT_DIRECTED), new Edge(2, 1, EdgeType.UNDIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.LEFT_DIRECTED), new Edge(2, 1, EdgeType.LEFT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED))},
                              {"1->2", new Edges(new Edge(2, 1, EdgeType.LEFT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(2, 1, EdgeType.UNDIRECTED))},

                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.UNDIRECTED),
                                                  new Edge(2, 1, EdgeType.LEFT_DIRECTED),
                                                  new Edge(2, 1, EdgeType.RIGHT_DIRECTED),
                                                  new Edge(1, 2, EdgeType.RIGHT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED),
                                                  new Edge(2, 1, EdgeType.LEFT_DIRECTED),
                                                  new Edge(1, 2, EdgeType.LEFT_DIRECTED),
                                                  new Edge(2, 1, EdgeType.RIGHT_DIRECTED))}};
    }
}