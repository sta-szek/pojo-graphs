package org.pojo.graphs.matrix;

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
        matrix[n - 1][m - 1] = value;
    }

    public long getValue(final int n, final int m) {
        if (n > this.n || m > this.m) {
            throw new UnsupportedOperationException("Nie można odczytać wartości dla pola spoza zakresu macierzy!");
        }
        return matrix[n - 1][m - 1];
    }

//    public Matrix multiply(final Matrix matrix) {
//        checkPreconditions(matrix);
//    }

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

    private void checkPreconditions(final Matrix matrix) {
        if (getColumns() != matrix.getRows()) {
            throw new InvalidOperationException(
                    "Nie można mnożyć macierzy, jeżeli liczba kolumn w pierwszej macierzy jest różna od liczby wierszy w drugiej!");
        }
    }

}
