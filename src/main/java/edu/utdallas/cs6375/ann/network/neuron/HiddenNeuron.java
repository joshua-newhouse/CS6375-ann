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

        if(Double.isInfinite(this.delta) || Double.isNaN(this.delta)) {
            throw new NeuronException(ID + ": delta is infinite or not a number");
        }
    }

    @Override
    public String toString() {
        return "HIDDEN_NEURON:\n" + super.toString();
    }
}
