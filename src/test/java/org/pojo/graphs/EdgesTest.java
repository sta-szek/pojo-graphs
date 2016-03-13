package org.pojo.graphs;

import org.assertj.core.util.Lists;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EdgesTest {

    @Test
    public void shouldOptimizeEdges1() {
        // given
        final Edge edge = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);
        final String expectedString = "1->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge, edge));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges2() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);
        final Edge edge2 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        final String expectedString = "1<->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges3() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.UNDIRECTED);
        final Edge edge2 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        final String expectedString = "1<->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges4() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);
        final Edge edge2 = new Edge(2, 1, EdgeType.LEFT_DIRECTED);
        final String expectedString = "1->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges5() {
        // given
        final Edge edge1 = new Edge(2, 1, EdgeType.RIGHT_DIRECTED);
        final Edge edge2 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        final String expectedString = "1<-2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges6() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.UNDIRECTED);
        final Edge edge2 = new Edge(2, 1, EdgeType.LEFT_DIRECTED);
        final Edge edge3 = new Edge(2, 1, EdgeType.RIGHT_DIRECTED);
        final Edge edge4 = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);
        final String expectedString = "1<->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2, edge3, edge4));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges7() {
        // given
        final Edge edge = new Edge(2, 1, EdgeType.RIGHT_DIRECTED);
        final String expectedString = "1<-2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldOptimizeEdges8() {
        // given
        final Edge edge1 = new Edge(1, 2, EdgeType.RIGHT_DIRECTED);
        final Edge edge2 = new Edge(2, 1, EdgeType.LEFT_DIRECTED);
        final Edge edge3 = new Edge(1, 2, EdgeType.LEFT_DIRECTED);
        final Edge edge4 = new Edge(2, 1, EdgeType.RIGHT_DIRECTED);
        final Edge edge5 = new Edge(1, 2, EdgeType.UNDIRECTED);
        final Edge edge6 = new Edge(2, 1, EdgeType.UNDIRECTED);
        final String expectedString = "1<->2";

        // when
        final Edges edges = new Edges(Lists.newArrayList(edge1, edge2, edge3, edge4, edge5, edge6));

        // then
        assertThat(edges.toString()).isEqualTo(expectedString);
    }
}