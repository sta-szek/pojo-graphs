package org.pojo.graphs.matrix;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Matrix {

    private final int rows;
    private final int columns;

    private final long[][] matrix;

    public Matrix(final int squareSize) {
        this(squareSize, squareSize);
    }

    public Matrix(final int rows, final int columns) {
        matrix = new long[rows][columns];
        this.rows = rows;
        this.columns = columns;
    }

    public Matrix(final long[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.columns = matrix[0].length;
    }

    public void setValue(final long value, final int row, final int column) {
        if (row > this.rows || column > this.columns) {
            throw new UnsupportedOperationException("Nie można ustawić wartości dla pola spoza zakresu macierzy!");
        }
        matrix[row][column] = value;
    }

    public long getValue(final int row, final int column) {
        if (row > this.rows || column > this.columns) {
            throw new UnsupportedOperationException("Nie można odczytać wartości dla pola spoza zakresu macierzy!");
        }
        return matrix[row][column];
    }

    public Matrix add(final Matrix matrix) {
        checkAddOrSubPreconditions(matrix);
        final Matrix sumOfMatrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final long newValue = getValue(i, j) + matrix.getValue(i, j);
                sumOfMatrix.setValue(newValue, i, j);
            }
        }
        return sumOfMatrix;
    }

    public Matrix sub(final Matrix matrix) {
        checkAddOrSubPreconditions(matrix);
        final Matrix sumOfMatrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final long newValue = getValue(i, j) - matrix.getValue(i, j);
                sumOfMatrix.setValue(newValue, i, j);
            }
        }
        return sumOfMatrix;
    }

    public Matrix multiply(final long multiplyNumber) {
        final Matrix multipliedMatrix = new Matrix(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final long newValue = getValue(i, j) * multiplyNumber;
                multipliedMatrix.setValue(newValue, i, j);
            }
        }
        return multipliedMatrix;
    }

    public Matrix multiply(final Matrix otherMatrix) {
        checkMultiplyPreconditions(otherMatrix);
        final Matrix multipliedMatrix = new Matrix(rows, otherMatrix.columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < otherMatrix.columns; j++) {
                long temp = 0;
                for (int w = 0; w < otherMatrix.rows; w++) {
                    temp += getValue(i, w) * otherMatrix.getValue(w, j);
                }
                multipliedMatrix.setValue(temp, i, j);
            }
        }
        return multipliedMatrix;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            stringBuilder.append("|\t");
            for (int j = 0; j < columns; j++) {
                stringBuilder.append(matrix[i][j])
                             .append("\t");
            }
            stringBuilder.append("|");
            if (i + 1 < rows) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Matrix matrix1 = (Matrix) o;

        return new EqualsBuilder().append(rows, matrix1.rows)
                                  .append(columns, matrix1.columns)
                                  .append(matrix, matrix1.matrix)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(rows)
                                    .append(columns)
                                    .append(matrix)
                                    .toHashCode();
    }

    private void checkMultiplyPreconditions(final Matrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new InvalidOperationException(
                    "Nie można mnożyć macierzy, jeżeli liczba kolumn w pierwszej macierzy jest różna od liczby wierszy w drugiej!");
        }
    }

    private void checkAddOrSubPreconditions(final Matrix matrix) {
        if (getColumns() != matrix.getColumns() || getRows() != matrix.getRows()) {
            throw new InvalidOperationException("Nie można dodać / odjąć macierzy, jeżeli mają one różne wymiary!");
        }
    }
}
