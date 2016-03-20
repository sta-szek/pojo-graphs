package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@RunWith(JUnitParamsRunner.class)
public class EdgeTest {

    @Test
    public void shouldThrowException_WhenFirstEdgeIsLessThanOne(){
        // given

        // when
        Throwable result = catchThrowable(() -> new Edge(0, 1));

        // then
        assertThat(result).isInstanceOf(InvalidEdgeDeclaration.class);
    }

    @Test
    public void shouldThrowException_WhenSecongEdgeIsLessThanOne(){
        // given

        // when
        Throwable result = catchThrowable(() -> new Edge(1, 0));

        // then
        assertThat(result).isInstanceOf(InvalidEdgeDeclaration.class);
    }

    @Test
    public void shouldCreateUndirectedEdge() {
        // given

        // when
        final Edge result = new Edge(1, 2);

        // then
        assertThat(result.getEdgeType()).isEqualTo(EdgeType.UNDIRECTED);
    }

    @Test
    public void shouldReturnSameHashCodes() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        final Edge edge2 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);

        // when
        final int result1 = edge1.hashCode();
        final int result2 = edge2.hashCode();

        // then
        assertThat(result1).isEqualTo(result2);
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

    @Test
    @Parameters(method = "getObjectsForUndirectedTest")
    public void shouldReturnTrue_IfEdgeIsUndirected_FalseOtherwise(final Edge edge, final boolean expectedString) {
        // given

        // when
        final boolean result = edge.isUndirected();

        // then
        assertThat(result).isEqualTo(expectedString);
    }

    @Test
    @Parameters(method = "getObjectForEqual")
    public void shouldEqual(final Edge edge1, final Edge edge2) {
        // given

        // when
        final boolean result = edge1.equals(edge2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters(method = "getObjectForUnequal")
    public void shouldNotEqual(final Edge edge1, final Edge edge2) {
        // given

        // when
        final boolean result = edge1.equals(edge2);

        // then
        assertThat(result).isFalse();
    }

    private Object getObjectsForUndirectedTest() {
        return new Object[][]{{new Edge(1, 2, EdgeType.LEFT_DIRECTED), false},
                              {new Edge(1, 2, EdgeType.UNDIRECTED), true},
                              {new Edge(1, 2, EdgeType.RIGHT_DIRECTED), false}};
    }

    private Object getObjectsToTest() {
        return new Object[][]{{new Edge(1, 2, EdgeType.LEFT_DIRECTED), "1<-2"},
                              {new Edge(1, 2, EdgeType.UNDIRECTED), "1<->2"},
                              {new Edge(1, 2, EdgeType.RIGHT_DIRECTED), "1->2"}};
    }

    private Object getObjectForEqual() {
        final Edge edge = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        return new Object[][]{{new Edge(1, 2, EdgeType.LEFT_DIRECTED), new Edge(1, 2, EdgeType.LEFT_DIRECTED)},
                              {edge, edge}};
    }

    private Object getObjectForUnequal() {
        return new Object[][]{{new Edge(1, 2, EdgeType.LEFT_DIRECTED), null},
                              {new Edge(1, 2, EdgeType.LEFT_DIRECTED), new Edge(2, 1, EdgeType.LEFT_DIRECTED)}};
    }


}