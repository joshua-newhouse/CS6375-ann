package edu.utdallas.cs6375.ann.network.neuron;

import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {
    protected double output;
    private List<NetworkNeuron> downstreamNeurons = new ArrayList<>();

    public double getOutput() {
        return output;
    }

    protected void addDownstreamNeuron(NetworkNeuron neuron) {
        downstreamNeurons.add(neuron);
    }

    protected List<NetworkNeuron> getDownstreamNeurons() {
        return downstreamNeurons;
    }
}
