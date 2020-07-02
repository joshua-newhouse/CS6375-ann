package edu.utdallas.cs6375.ann.network.neuron;

public class HiddenNeuron extends NetworkNeuron {
    public HiddenNeuron(ActivationFunction af, ActivationFunction afPrime, String ID) {
        super(af, afPrime);
        this.setID("HiddenNeuron" + ID);
    }

    @Override
    public void calculateDelta(double target) {
        this.delta = this.afDerivative.apply(this.output)
                * this.getDownstreamNeurons().stream()
                                             .mapToDouble(n -> n.getWeightForInputNeuron(this) * n.getDelta())
                                             .sum();
    }

    @Override
    public String toString() {
        return "HIDDEN_NEURON:\n" + super.toString();
    }
}
