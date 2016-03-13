package org.pojo.graphs;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class EdgeTypeTest {

    @Test
    public void shouldReturnToString1() {
        // given

        // when
        final String result = EdgeType.LEFT_DIRECTED.toString();

        // then
        assertThat(result).isEqualTo("<-");
    }

    @Test
    public void shouldReturnToString2() {
        // given

        // when
        final String result = EdgeType.RIGHT_DIRECTED.toString();

        // then
        assertThat(result).isEqualTo("->");
    }

    @Test
    public void shouldReturnToString3() {
        // given

        // when
        final String result = EdgeType.UNDIRECTED.toString();

        // then
        assertThat(result).isEqualTo("<->");
    }
}