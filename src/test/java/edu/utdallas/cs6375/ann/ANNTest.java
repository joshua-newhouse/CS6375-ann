package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.network.ANNException;
import edu.utdallas.cs6375.ann.network.ArtificialNeuralNetwork;

import java.util.Arrays;
import java.util.List;

public class ANNTest {
    public static void main(String[] args) throws ANNException {
        ArtificialNeuralNetwork ann = new ArtificialNeuralNetwork(0.5, 2, 1, 2);

        List<Double> inputs = Arrays.asList(0.05, 0.10);
        ann.setInput(inputs, 1.0);
        System.out.println(ann.toString());

        ann.forwardPass();
        ann.backwardPropagate();
        ann.updateWeights();

        System.out.println(ann.toString());
    }
}
