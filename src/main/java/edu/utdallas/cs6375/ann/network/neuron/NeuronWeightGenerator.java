package edu.utdallas.cs6375.ann.network.neuron;

import java.util.Random;

public class NeuronWeightGenerator {
    private static final Random GENERATOR = new Random(System.nanoTime());

    private static double upperBound = 20.0;
    private static double lowerBound = -20.0;

    private NeuronWeightGenerator() {
    }

    public static void setSeed(long seed) {
        GENERATOR.setSeed(seed);
    }

    public static void setUpperBound(double ub) {
        upperBound = ub;
    }

    public static void setLowerBound(double lb) {
        lowerBound = lb;
    }

    public static double get() {
        return GENERATOR.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }
}
