package edu.utdallas.cs6375.ann.network.neuron;

public class InputNeuron extends Neuron {
    public InputNeuron(String ID) {
        setID("IntputNeuron" + ID);
    }

    public void setValue(double value) {
        this.output = value;
    }

    @Override
    public String toString() {
        return ID + ": value = " + this.output;
    }
}
