package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.assertj.core.util.Lists;
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
        String result = edgesToOptimize.toString();

        // then
        assertThat(result).isEqualTo(expectedString);
    }


    private Object getEdgesForOptimization() {
        return new Object[][]{{"1<->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(2, 1, EdgeType.RIGHT_DIRECTED))},
                              {"1<->2", new Edges(new Edge(1, 2, EdgeType.UNDIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(2, 1, EdgeType.LEFT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1->2", new Edges(new Edge(1, 2, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.RIGHT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED))},
                              {"1<-2", new Edges(new Edge(2, 1, EdgeType.RIGHT_DIRECTED))},

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