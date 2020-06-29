package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;
import edu.utdallas.cs6375.ann.network.neuron.Neuron;

public abstract class NetworkLayer extends Layer<NetworkNeuron> {
    public void addInputNeuron(Neuron n) {
        this.neurons.forEach(neuron -> neuron.addInputNeuron(n));
    }

    public void calculateOutputs() {
        this.neurons.forEach(NetworkNeuron::calculateOutput);
    }

    public void calculateDeltas(double target) {
        this.neurons.forEach(n -> n.calculateDelta(target));
    }

    public void updateWeights(double alpha) {
        neurons.forEach(n -> n.updateWeights(alpha));
    }
}
