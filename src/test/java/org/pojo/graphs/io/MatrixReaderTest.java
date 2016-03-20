package org.pojo.graphs.io;

import org.junit.Test;
import org.pojo.graphs.matrix.Matrix;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;


public class MatrixReaderTest {

    @Test
    public void shouldReadMatrixFromFile() throws URISyntaxException, IOException {
        // given
        final URI uri = this.getClass()
                            .getResource("/matrix/Matrix.txt")
                            .toURI();
        final Path path = new File(uri).toPath();
        final MatrixReader matrixReader = new MatrixReader();
        final Matrix expectedMatrix = new Matrix(new int[][]{{0, 1, 0, 1},
                                                             {0, 1, 0, 1},
                                                             {0, 1, 0, 1},
                                                             {0, 1, 0, 1}});

        // when
        final Matrix result = matrixReader.read(path);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }

    @Test
    public void shouldThrowException_IfFileIsEmpty() throws URISyntaxException, IOException {
        // given
        final URI uri = this.getClass()
                            .getResource("EmptyFile.txt")
                            .toURI();
        final Path path = new File(uri).toPath();
        final MatrixReader matrixReader = new MatrixReader();

        // when
        final Throwable result = catchThrowable(() -> matrixReader.read(path));

        // then
        assertThat(result).isInstanceOf(InvalidMatrixFileException.class);
    }

    @Test
    public void shouldThrowException_IfDimensionsAreDifferent() throws URISyntaxException, IOException {
        // given
        final URI uri = this.getClass()
                            .getResource("/matrix/InvalidMatrix.txt")
                            .toURI();
        final Path path = new File(uri).toPath();
        final MatrixReader matrixReader = new MatrixReader();

        // when
        final Throwable result = catchThrowable(() -> matrixReader.read(path));

        // then
        assertThat(result).isInstanceOf(InvalidMatrixFileException.class);
    }
}