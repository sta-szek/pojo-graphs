package org.pojo.graphs.io;

import org.junit.Test;
import org.pojo.graphs.Graph;
import org.pojo.graphs.InvalidGraphFileException;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class GraphReaderTest {

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
        final GraphReader graphReader = new GraphReader();

        // when
        final Graph graph = graphReader.read(path);

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
        final GraphReader graphReader = new GraphReader();

        // when
        final Throwable result = catchThrowable(() -> graphReader.read(path));

        // then
        assertThat(result).isInstanceOf(InvalidGraphFileException.class);
    }
}