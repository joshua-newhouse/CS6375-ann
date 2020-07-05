package edu.utdallas.cs6375.ann.network.neuron;

public class OutputNeuron extends NetworkNeuron {
    public OutputNeuron(ActivationFunction af, ActivationFunction afPrime, String ID) {
        super(af, afPrime);
        setID("OutputNeuron" + ID);
    }

    @Override
    public void calculateDelta(double target) {
        this.delta = (target - this.output) * this.afDerivative.apply(this.output);

        if(Double.isInfinite(this.delta) || Double.isNaN(this.delta)) {
            throw new NeuronException(ID + ": delta is infinite or not a number");
        }
    }

    @Override
    protected void addDownstreamNeuron(NetworkNeuron neuron) {
    }

    @Override
    public String toString() {
        return ID + ":\n" + super.toString();
    }
}
