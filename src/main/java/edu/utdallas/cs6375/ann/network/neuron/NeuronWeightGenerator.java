package edu.utdallas.cs6375.ann.network.neuron;

public class NeuronWeightGenerator {
    private static double upperBound = 20.0;
    private static double lowerBound = -20.0;

    private NeuronWeightGenerator() {
    }

    public static void setUpperBound(double ub) {
        upperBound = ub;
    }

    public static void setLowerBound(double lb) {
        lowerBound = lb;
    }

    public static double get() {
        return Math.random() * upperBound + lowerBound;
    }
}
