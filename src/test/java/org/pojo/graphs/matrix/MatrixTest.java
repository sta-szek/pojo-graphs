package org.pojo.graphs.matrix;

import org.junit.Test;
import org.pojo.test.TestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.reflect.Whitebox.getInternalState;

public class MatrixTest {

    @Test
    public void shouldCreateMatrixFormTwoDimensionalArray() {
        // given
        final long[][] matrix = {{5, 1}, {4, 2}};
        final Matrix expectedResult = new Matrix(2, 2);
        expectedResult.setValue(5, 0, 0);
        expectedResult.setValue(1, 0, 1);
        expectedResult.setValue(4, 1, 0);
        expectedResult.setValue(2, 1, 1);

        // when
        final Matrix result = new Matrix(matrix);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldPrintFormattedSquareMatrixAfterValuesSet() {
        // given
        final Matrix matrix = new Matrix(2);
        final String expectedOutput = "|\t1\t2\t|\n" +
                                      "|\t3\t4\t|";
        matrix.setValue(1, 0, 0);
        matrix.setValue(2, 0, 1);
        matrix.setValue(3, 1, 0);
        matrix.setValue(4, 1, 1);

        // when
        final String result = matrix.toString();

        // then
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldPrintFormattedSquareMatrix() {
        // given
        final Matrix matrix = new Matrix(2);
        final String expectedOutput = "|\t0\t0\t|\n" +
                                      "|\t0\t0\t|";
        // when
        final String result = matrix.toString();

        // then
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldPrintFormattedNotSquareMatrix() {
        // given
        final Matrix matrix = new Matrix(2, 3);
        final String expectedOutput = "|\t0\t0\t0\t|\n" +
                                      "|\t0\t0\t0\t|";
        // when
        final String result = matrix.toString();

        // then
        assertThat(result).isEqualTo(expectedOutput);
    }

    @Test
    public void shouldSetValue() {
        // given
        final Matrix matrix = new Matrix(2, 2);
        final long expectedResult = 1L;

        // when
        matrix.setValue(expectedResult, 1, 1);
        final long result = getInternalStateOfMatrix(matrix, 1, 1);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldGetValue() throws Exception {
        // given
        final Matrix matrix = new Matrix(2, 2);
        final long expectedResult = 1L;
        setInternalStateOfMatrix(matrix, expectedResult, 1, 1);

        // when
        final long result = matrix.getValue(1, 1);

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
    public void shouldMultiplyByNumber() {
        // given
        final Matrix matrix = createAndFillWith(2, 2, 3);
        final long multiplyNumber = 4L;
        final Matrix expectedMatrix = createAndFillWith(2 * multiplyNumber, 2, 3);

        // when
        final Matrix result = matrix.multiply(multiplyNumber);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }

    @Test
    public void shouldMultiplyByOtherMatrix1() {
        // given
        final long[][] matrix1Array = {{2, 1, 3}, {-1, 2, 4}};
        final Matrix matrix1 = new Matrix(matrix1Array);
        final long[][] matrix2Array = {{1, 3}, {2, -2}, {-1, 4}};
        final Matrix matrix2 = new Matrix(matrix2Array);
        final long[][] expectedMatrixArray = {{1, 16}, {-1, 9}};
        final Matrix expectedMatrix = new Matrix(expectedMatrixArray);

        // when
        final Matrix result = matrix1.multiply(matrix2);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }

    @Test
    public void shouldMultiplyByOtherMatrix2() {
        // given
        final long[][] matrix1Array = {{5, 1}, {-3, 3}, {4, -1}, {-1, 2}, {-0, 7}};
        final Matrix matrix1 = new Matrix(matrix1Array);
        final long[][] matrix2Array = {{1, 7, 0, -1, 9, 0, 5}, {2, -6, -1, 10, 2, -3, 1}};
        final Matrix matrix2 = new Matrix(matrix2Array);
        final long[][] expectedMatrixArray = {{7, 29, -1, 5, 47, -3, 26},
                                              {3, -39, -3, 33, -21, -9, -12},
                                              {2, 34, 1, -14, 34, 3, 19},
                                              {3, -19, -2, 21, -5, -6, -3},
                                              {14, -42, -7, 70, 14, -21, 7}};
        final Matrix expectedMatrix = new Matrix(expectedMatrixArray);

        // when
        final Matrix result = matrix1.multiply(matrix2);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }


    @Test
    public void shouldSubtractTwoMatrix() {
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
        return targetMatrix[n][m];
    }

    private void setInternalStateOfMatrix(final Matrix matrix, final long value, final int n, final int m) throws Exception {
        final long[][] targetMatrix = getInternalState(matrix, "matrix");
        targetMatrix[n][m] = value;
        TestHelper.setFinalStatic(matrix, "matrix", targetMatrix);
    }

}