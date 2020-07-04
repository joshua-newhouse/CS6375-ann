package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.network.matrix.DoubleMatrix;
import edu.utdallas.cs6375.ann.network.matrix.MatrixException;

public class MatrixTest {
    public static void main(String[] args) {
        DoubleMatrix matrix = new DoubleMatrix(3, 5);

        for(int row = 0; row < matrix.rows(); row++) {
            for(int column = 0; column < matrix.columns(); column++) {
                matrix.setElement(row, column, row + column);
            }
        }

        System.out.println(matrix.toString());

        DoubleMatrix matrix2 = new DoubleMatrix(5, 6);

        for(int row = 0; row < matrix2.rows(); row++) {
            for(int column = 0; column < matrix2.columns(); column++) {
                matrix2.setElement(row, column, row * column);
            }
        }

        System.out.println(matrix2.toString());

        DoubleMatrix output = new DoubleMatrix(matrix.rows(), matrix2.columns());

        try {
            matrix.multiply(matrix2, output);
        } catch (MatrixException e) {
            System.out.println(e.toString());
        }

        System.out.println(output.toString());

        matrix.applyFunctionToElements((v) -> v * 2.0);
        System.out.println(matrix.toString());


        try {
            matrix.columnVectorMultiply(0, output, 2, output, 5);
        } catch(MatrixException e) {
            System.out.println(e.toString());
        }

        System.out.println(output.toString());
    }
}
