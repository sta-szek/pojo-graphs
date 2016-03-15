package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EdgeTest {

    @Test
    public void shouldCreateUndirectedEdge() {
        // given

        // when
        final Edge result = new Edge(1, 2);

        // then
        assertThat(result.getEdgeType()).isEqualTo(EdgeType.UNDIRECTED);
    }

    @Test
    @Parameters(method = "getObjectsToTest")
    public void shouldHaveValidDirection(final Edge edge, final String expectedString) {
        // given

        // when
        final String result = edge.toString();

        // then
        assertThat(result).isEqualTo(expectedString);
    }


    private Object getObjectsToTest() {
        return new Object[][]{{new Edge(1, 2, EdgeType.LEFT_DIRECTED), "1<-2"},
                              {new Edge(1, 2, EdgeType.UNDIRECTED), "1<->2"},
                              {new Edge(1, 2, EdgeType.RIGHT_DIRECTED), "1->2"}};
    }
}