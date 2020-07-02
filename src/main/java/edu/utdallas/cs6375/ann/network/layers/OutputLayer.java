package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.neuron.OutputNeuron;

public class OutputLayer extends NetworkLayer {
    public OutputLayer() {
        this.neurons.add(new OutputNeuron(ActivationService.getAF(), ActivationService.getAFPrime(), "O"));
        super.setID("OutputLayer");
    }

    public double getOutput() {
        return this.neurons.get(0).getOutput();
    }
}
