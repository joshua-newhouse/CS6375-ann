package edu.utdallas.cs6375.ann.network.matrix;

import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;

import java.util.Arrays;

public class DoubleMatrix {
    private final double[][] matrix;

    public DoubleMatrix(int rows, int columns) {
        matrix = new double[rows][columns];
    }

    public int rows() {
        return matrix.length;
    }

    public int columns() {
        return matrix[0].length;
    }

    public double getElement(int row, int column) {
        return matrix[row][column];
    }

    public void setElement(int row, int column, double value) {
        matrix[row][column] = value;
    }

    public void multiply(DoubleMatrix b, DoubleMatrix out) throws MatrixException {
        if(this.columns() != b.rows() || out.columns() != b.columns() || this.rows() != out.rows()) {
            throw new MatrixException("Matrix multiply dimension mismatch");
        }

        for(int row = 0; row < this.rows(); row++) {
            for(int column = 0; column < b.columns(); column++) {

                double sum = 0.0;

                for(int k = 0; k < this.columns(); k++) {
                    sum += matrix[row][k] * b.matrix[k][column];
                }

                out.matrix[row][column] = sum;
            }
        }
    }

    public void columnVectorMultiply(int thisColumn, DoubleMatrix b, int bColumn, DoubleMatrix out, int outColumn)
            throws MatrixException {

        if(this.rows() != b.rows() || this.rows() != out.rows() ||
                thisColumn < 0 || thisColumn > this.columns() ||
                bColumn < 0 || bColumn > b.columns() ||
                outColumn < 0 || outColumn > out.columns()) {
            throw new MatrixException("Matrix columnVectorMultiply row dimension mismatch");
        }

        for(int row = 0; row < this.rows(); row++) {
            out.matrix[row][outColumn] = this.matrix[row][thisColumn] * b.matrix[row][bColumn];
        }
    }

    public void applyFunctionToElements(NetworkNeuron.ActivationFunction function) {
        for(int row = 0; row < this.rows(); row++) {
            for(int column = 0; column < this.columns(); column++) {
                this.matrix[row][column] = function.apply(this.matrix[row][column]);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int row = 0; row < this.rows(); row++) {
            sb.append(Arrays.toString(matrix[row])).append("\n");
        }

        return sb.toString();
    }
}
