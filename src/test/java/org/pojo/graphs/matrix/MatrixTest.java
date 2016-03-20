package org.pojo.graphs.matrix;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pojo.helpers.Matrices;
import org.pojo.helpers.TestHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.powermock.reflect.Whitebox.getInternalState;

@RunWith(JUnitParamsRunner.class)
public class MatrixTest {

    @Test
    public void shouldThrowException_WhenMultiplyConditionFails() {
        // given
        final Matrix matrix1 = new Matrix(1, 2);
        final Matrix matrix2 = new Matrix(3, 4);

        // when
        final Throwable result = catchThrowable(() -> matrix1.multiply(matrix2));

        // then
        assertThat(result).isInstanceOf(InvalidOperationException.class);
    }

    @Test
    @Parameters(method = "getMatricesForAddOrSubConditionFail")
    public void shouldThrowException_WhenAddOrSubConditionFails(final Matrix matrix1, final Matrix matrix2) {
        // given

        // when
        final Throwable result = catchThrowable(() -> matrix1.add(matrix2));

        // then
        assertThat(result).isInstanceOf(InvalidOperationException.class);
    }

    @Test
    @Parameters(method = "getOutOfRangeMatrixValues")
    public void shouldThrowException_WhenSettingValueOutOfRange(final int row, final int column) {
        // given
        final Matrix matrix = new Matrix(1, 1);

        // when
        final Throwable result = catchThrowable(() -> matrix.setValue(1, row, column));

        // then
        assertThat(result).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @Parameters(method = "getOutOfRangeMatrixValues")
    public void shouldThrowException_WhenGettingValueOutOfRange(final int row, final int column) {
        // given
        final Matrix matrix = new Matrix(1, 1);

        // when
        final Throwable result = catchThrowable(() -> matrix.getValue(row, column));

        // then
        assertThat(result).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    public void shouldCreateMatrixFormTwoDimensionalArray() {
        // given
        final int[][] matrix = {{5, 1}, {4, 2}};
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
        final int expectedResult = 1;

        // when
        matrix.setValue(expectedResult, 1, 1);
        final int result = getInternalStateOfMatrix(matrix, 1, 1);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void shouldGetValue() throws Exception {
        // given
        final Matrix matrix = new Matrix(2, 2);
        final int expectedResult = 1;
        setInternalStateOfMatrix(matrix, expectedResult, 1, 1);

        // when
        final int result = matrix.getValue(1, 1);

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
        final int multiplyNumber = 4;
        final Matrix expectedMatrix = createAndFillWith(2 * multiplyNumber, 2, 3);

        // when
        final Matrix result = matrix.multiply(multiplyNumber);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }

    @Test
    public void shouldReturnSameHashCodes() {
        // given
        final Matrix matrix1 = new Matrix(5);
        final Matrix matrix2 = new Matrix(5);

        // when
        final int result1 = matrix1.hashCode();
        final int result2 = matrix2.hashCode();

        // then
        assertThat(result1).isEqualTo(result2);
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

    @Test
    public void shouldThrowException_WhenMatricesSizesAreDifferent() {
        // given
        final Matrix matrix1 = createAndFillWith(1, 3, 2);
        final Matrix matrix2 = createAndFillWith(2, 2, 3);

        // when
        final Throwable result = catchThrowable(() -> matrix1.sub(matrix2));

        // then
        assertThat(result).isInstanceOf(InvalidOperationException.class);
    }

    @Test
    @Parameters(method = "getMatricesForMultiplications")
    public void shouldMultiplyByOtherMatrix(final int[][] matrixArray1, final int[][] matrixArray2, final int[][] expectedMatrixArray) {
        // given
        final Matrix matrix1 = new Matrix(matrixArray1);
        final Matrix matrix2 = new Matrix(matrixArray2);
        final Matrix expectedMatrix = new Matrix(expectedMatrixArray);

        // when
        final Matrix result = matrix1.multiply(matrix2);

        // then
        assertThat(result).isEqualTo(expectedMatrix);
    }

    @Test
    @Parameters(method = "getObjectForEqual")
    public void shouldEqual(final Matrix matrix1, final Matrix matrix2) {
        // given

        // when
        final boolean result = matrix1.equals(matrix2);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @Parameters(method = "getObjectForUnequal")
    public void shouldNotEqual(final Matrix matrix1, final Matrix matrix2) {
        // given

        // when
        final boolean result = matrix1.equals(matrix2);

        // then
        assertThat(result).isFalse();
    }

    private Object getMatricesForAddOrSubConditionFail() {
        return new Object[][]{{new Matrix(1, 2), new Matrix(1, 3)},
                              {new Matrix(1, 2), new Matrix(2, 2)}};
    }

    private Object getOutOfRangeMatrixValues() {
        return new Object[][]{{1, 1},
                              {-10, 1},
                              {0, -30},
                              {0, 1}};
    }

    private Object getObjectForEqual() {
        final Matrix matrix = new Matrix(5);
        return new Object[][]{{new Matrix(4), new Matrix(4)},
                              {matrix, matrix}};
    }

    private Object getObjectForUnequal() {
        return new Object[][]{{new Matrix(1, 2), null},
                              {new Matrix(5, 5), new Matrix(5, 4)}};
    }

    private Object getMatricesForMultiplications() {
        return new Object[][]{{Matrices.MATRIX_1A, Matrices.MATRIX_1B, Matrices.MATRIX_1A_X_MATRIX_1B},
                              {Matrices.MATRIX_2A, Matrices.MATRIX_2B, Matrices.MATRIX_2A_X_MATRIX_2B}};
    }

    private Matrix createAndFillWith(final int value, final int n, final int m) {
        final Matrix matrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix.setValue(value, i, j);
            }
        }
        return matrix;
    }

    private int getInternalStateOfMatrix(final Matrix matrix, final int n, final int m) {
        final int[][] targetMatrix = getInternalState(matrix, "matrix");
        return targetMatrix[n][m];
    }

    private void setInternalStateOfMatrix(final Matrix matrix, final int value, final int n, final int m) throws Exception {
        final int[][] targetMatrix = getInternalState(matrix, "matrix");
        targetMatrix[n][m] = value;
        TestHelper.setFinalStatic(matrix, "matrix", targetMatrix);
    }

}