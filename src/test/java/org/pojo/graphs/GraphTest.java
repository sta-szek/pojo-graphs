package org.pojo.graphs;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


public class GraphTest {

    @Test
    public void shouldCreateGraphFromFile() throws IOException, URISyntaxException {
        // given
        final URI uri = this.getClass()
                            .getResource("/graphs/SmallGraph")
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
}