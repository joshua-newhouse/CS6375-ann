package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.network.ANNException;
import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.ArtificialNeuralNetwork;
import edu.utdallas.cs6375.ann.network.neuron.NeuronWeightGenerator;

import java.util.Arrays;
import java.util.List;

public class ANNTest {
    public static void main(String[] args) throws ANNException {
        NeuronWeightGenerator.setSeed(1);
        NeuronWeightGenerator.setUpperBound(1.0);
        NeuronWeightGenerator.setLowerBound(0.0);

        ArtificialNeuralNetwork ann = new ArtificialNeuralNetwork(0.5, 2, 1, 2, "relu");

        List<Double> inputs = Arrays.asList(0.05, 0.10);
        ann.setInput(inputs, 1.0);
        System.out.println(ann.toString());

        ann.forwardPass();
        ann.backwardPropagate();
        ann.updateWeights();

        System.out.println(ann.toString());
    }
}
