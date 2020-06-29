package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.neuron.Neuron;
import edu.utdallas.cs6375.ann.network.neuron.OutputNeuron;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class OutputLayer extends NetworkLayer {
    public OutputLayer() {
        this.neurons.add(new OutputNeuron(ActivationService.getAF(), ActivationService.getAFPrime()));
    }

    public double getOutput() {
        return this.neurons.get(0).getOutput();
    }
}
