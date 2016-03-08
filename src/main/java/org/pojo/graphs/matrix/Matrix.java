package org.pojo.graphs.matrix;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Matrix {

    private final int n;
    private final int m;

    private final long[][] matrix;

    public Matrix(final int n) {
        this(n, n);
    }

    public Matrix(final int n, final int m) {
        matrix = new long[n][m];
        this.n = n;
        this.m = m;
    }

    public void setValue(final long value, final int n, final int m) {
        if (n > this.n || m > this.m) {
            throw new UnsupportedOperationException("Nie można ustawić wartości dla pola spoza zakresu macierzy!");
        }
        final int row = translateToJavaNumbering(n);
        final int column = translateToJavaNumbering(m);

        matrix[row][column] = value;
    }

    public long getValue(final int n, final int m) {
        if (n > this.n || m > this.m) {
            throw new UnsupportedOperationException("Nie można odczytać wartości dla pola spoza zakresu macierzy!");
        }
        final int row = translateToJavaNumbering(n);
        final int column = translateToJavaNumbering(m);

        return matrix[row][column];
    }

    public Matrix add(final Matrix matrix) {
        checkAddOrSubPreconditions(matrix);
        final Matrix sumOfMatrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                final long newValue = getValue(i, j) + matrix.getValue(i, j);
                sumOfMatrix.setValue(newValue, i, j);
            }
        }
        return sumOfMatrix;
    }

    public Matrix sub(final Matrix matrix) {
        checkAddOrSubPreconditions(matrix);
        final Matrix sumOfMatrix = new Matrix(n, m);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                final long newValue = getValue(i, j) - matrix.getValue(i, j);
                sumOfMatrix.setValue(newValue, i, j);
            }
        }
        return sumOfMatrix;
    }

    public void print() {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append("|\t");
            for (int j = 0; j < m; j++) {
                stringBuilder.append(matrix[i][j])
                             .append("\t");
            }
            stringBuilder.append("|");
            if (i + 1 < n) {
                stringBuilder.append("\n");
            }
        }
        System.out.print(stringBuilder.toString());
    }

    public int getRows() {
        return n;
    }

    public int getColumns() {
        return m;
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

        return new EqualsBuilder().append(n, matrix1.n)
                                  .append(m, matrix1.m)
                                  .append(matrix, matrix1.matrix)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(n)
                                    .append(m)
                                    .append(matrix)
                                    .toHashCode();
    }

    private int translateToJavaNumbering(final int n) {
        return n == 0
               ? 0
               : n - 1;
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
