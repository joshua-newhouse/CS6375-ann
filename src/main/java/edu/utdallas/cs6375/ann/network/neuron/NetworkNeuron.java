package edu.utdallas.cs6375.ann.network.neuron;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* Acts as both hidden neurons and output neurons */
public abstract class NetworkNeuron extends Neuron {
    private final Map<Neuron, Double> inputNeuronWeights = new ConcurrentHashMap<>();
    protected double delta;
    protected ActivationFunction activationFunction;
    protected ActivationFunction afDerivative;

    @FunctionalInterface
    public interface ActivationFunction {
        double apply(double input);
    }

    public NetworkNeuron(ActivationFunction af, ActivationFunction afPrime) {
        this.activationFunction = af;
        this.afDerivative = afPrime;
    }

    public void addInputNeuron(Neuron input) {
        inputNeuronWeights.put(input, NeuronWeightGenerator.get());
        input.addDownstreamNeuron(this);
    }

    public double getWeightForInputNeuron(Neuron n) {
        Double retVal = inputNeuronWeights.get(n);

        return retVal == null ? 0.0 : retVal;
    }

    public void calculateOutput() {
        this.output = 0.0;

        inputNeuronWeights.forEach((neuron, weight) -> {
            this.output += neuron.getOutput() * weight;
        });

        this.output = activationFunction.apply(this.output);
    }

    public void updateWeights(double alpha) {
        inputNeuronWeights.replaceAll((neuron, weight) -> weight - alpha * this.delta * neuron.getOutput());
    }

    public double getDelta() {
        return delta;
    }

    public abstract void calculateDelta(double target);
}
