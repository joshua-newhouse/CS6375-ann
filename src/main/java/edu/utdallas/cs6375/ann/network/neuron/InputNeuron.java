package edu.utdallas.cs6375.ann.network.neuron;

public class InputNeuron extends Neuron {
    public InputNeuron() {}

    public void setValue(double value) {
        this.output = value;
    }
}
