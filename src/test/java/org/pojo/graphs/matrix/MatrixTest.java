package org.pojo.graphs.matrix;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.pojo.test.TestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.reflect.Whitebox.getInternalState;

public class MatrixTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog()
                                                                  .muteForSuccessfulTests();


    @Test
    public void shouldPrintFormattedSquareMatrixAfterValuesSet() {
        // given
        final Matrix matrix = new Matrix(2);
        final String expectedOutput = "|\t1\t2\t|\n" +
                                      "|\t3\t4\t|";
        matrix.setValue(1,1,1);
        matrix.setValue(2,1,2);
        matrix.setValue(3,2,1);
        matrix.setValue(4,2,2);
        // when
        matrix.print();

        // then
        assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldPrintFormattedSquareMatrix() {
        // given
        final Matrix matrix = new Matrix(2);
        final String expectedOutput = "|\t0\t0\t|\n" +
                                      "|\t0\t0\t|";
        // when
        matrix.print();

        // then
        assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldPrintFormattedNotSquareMatrix() {
        // given
        final Matrix matrix = new Matrix(2, 3);
        final String expectedOutput = "|\t0\t0\t0\t|\n" +
                                      "|\t0\t0\t0\t|";
        // when
        matrix.print();

        // then
        assertThat(systemOutRule.getLog()).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldSetValue() {
        // given
        final Matrix matrix = new Matrix(2, 2);
        final long expectedResult = 1L;

        // when
        matrix.setValue(expectedResult, 2, 2);
        final long result = getInternalStateOfMatrix(matrix, 2, 2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldGetValue() throws Exception {
        // given
        final Matrix matrix = new Matrix(2, 2);
        final long expectedResult = 1L;
        setInternalStateOfMatrix(matrix, expectedResult, 2, 2);

        // when
        final long result = matrix.getValue(2, 2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldAddTwoMatrix() {
        // given
        final Matrix matrix1 = createAndFillWith(1, 2, 3);
        final Matrix matrix2 = createAndFillWith(2, 2, 3);
        final Matrix expectedResult = createAndFillWith(3, 2, 3);

        // when
        final Matrix result = matrix1.add(matrix2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldSubTwoMatrix() {
        // given
        final Matrix matrix1 = createAndFillWith(1, 2, 3);
        final Matrix matrix2 = createAndFillWith(2, 2, 3);
        final Matrix expectedResult = createAndFillWith(-1, 2, 3);

        // when
        final Matrix result = matrix1.sub(matrix2);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private Matrix createAndFillWith(final long value, final int n, final int m) {
        final Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.setValue(value, i, j);
            }
        }
        return matrix;
    }

    private long getInternalStateOfMatrix(final Matrix matrix, final int n, final int m) {
        final long[][] targetMatrix = getInternalState(matrix, "matrix");
        return targetMatrix[n - 1][m - 1];
    }

    private void setInternalStateOfMatrix(final Matrix matrix, final long value, final int n, final int m) throws Exception {
        final long[][] targetMatrix = getInternalState(matrix, "matrix");
        targetMatrix[n - 1][m - 1] = value;
        TestHelper.setFinalStatic(matrix, "matrix", targetMatrix);
    }

}