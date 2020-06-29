package edu.utdallas.cs6375.ann.network.neuron;

public class HiddenNeuron extends NetworkNeuron {
    public HiddenNeuron(ActivationFunction af, ActivationFunction afPrime) {
        super(af, afPrime);
    }

    @Override
    public void calculateDelta(double target) {
        this.delta = this.afDerivative.apply(this.output)
                * this.getDownstreamNeurons().stream()
                                             .mapToDouble(n -> n.getWeightForInputNeuron(this) * n.getDelta())
                                             .sum();
    }
}
