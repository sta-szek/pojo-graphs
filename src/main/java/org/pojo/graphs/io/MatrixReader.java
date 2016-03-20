package org.pojo.graphs.io;


import org.apache.commons.lang3.ArrayUtils;
import org.pojo.graphs.matrix.Matrix;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixReader {

    public Matrix read(final Path path) throws IOException {
        final List<Integer[]> collect = Files.lines(path)
                                             .map(this::toArray)
                                             .collect(Collectors.toList());
        final int[][] integers = checkSizeAndConvertToTwoDimensionalArray(collect);

        return new Matrix(integers);
    }

    private int[][] checkSizeAndConvertToTwoDimensionalArray(final List<Integer[]> collect) {
        if (collect.isEmpty()) {
            throw new InvalidMatrixFileException("Plik jest pusty!");
        }
        final int[][] twoDimensionalArray = new int[collect.size()][];
        final int secondDimension = collect.get(0).length;
        for (int i = 0; i < collect.size(); i++) {
            final Integer[] array = collect.get(i);
            if (array.length != secondDimension) {
                throw new InvalidMatrixFileException("Wymiary miacierzy nie zgadzają się.");
            }
            twoDimensionalArray[i] = ArrayUtils.toPrimitive(array);
        }
        return twoDimensionalArray;
    }

    private Integer[] toArray(final String row) {
        final String[] splitRow = row.split(" ");
        return Arrays.stream(splitRow)
                     .map(Integer::parseInt)
                     .toArray(Integer[]::new);
    }
}
