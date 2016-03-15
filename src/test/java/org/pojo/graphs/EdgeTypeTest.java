package org.pojo.graphs;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EdgeTypeTest {

    @Test
    @Parameters(method = "getObjectsToTest")
    public void shouldHaveValidDirection(final EdgeType edgeType, final String expectedDirection) {
        // given

        // when
        final String result = edgeType.toString();

        // then
        assertThat(result).isEqualTo(expectedDirection);
    }

    private Object getObjectsToTest() {
        return new Object[][]{{EdgeType.LEFT_DIRECTED, "<-"},
                              {EdgeType.UNDIRECTED, "<->"},
                              {EdgeType.RIGHT_DIRECTED, "->"}};
    }
}