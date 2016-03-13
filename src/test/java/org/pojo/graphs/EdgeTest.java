package org.pojo.graphs;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


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
    public void shouldReturnToString1() {
        // given
        final Edge edge = new Edge(1, 2, EdgeType.LEFT_DIRECTED);

        // when
        final String result = edge.toString();

        // then
        assertThat(result).isEqualTo("1<-2");
    }

    @Test
    public void shouldReturnToString2() {
        // given
        final Edge edge = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);

        // when
        final String result = edge.toString();

        // then
        assertThat(result).isEqualTo("1->2");
    }

    @Test
    public void shouldReturnToString3() {
        // given
        final Edge edge = new Edge(1, 2, EdgeType.UNDIRECTED);

        // when
        final String result = edge.toString();

        // then
        assertThat(result).isEqualTo("1<->2");
    }
}