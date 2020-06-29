package edu.utdallas.cs6375.ann.network.neuron;

public class OutputNeuron extends NetworkNeuron {
    public OutputNeuron(ActivationFunction af, ActivationFunction afPrime) {
        super(af, afPrime);
    }

    @Override
    public void calculateDelta(double target) {
        this.delta = (target - this.output) * this.afDerivative.apply(this.output);
    }

    @Override
    protected void addDownstreamNeuron(NetworkNeuron neuron) {
    }

    @Override
    public String toString() {
        return "OUTPUT_NEURON:\n" + super.toString();
    }
}
