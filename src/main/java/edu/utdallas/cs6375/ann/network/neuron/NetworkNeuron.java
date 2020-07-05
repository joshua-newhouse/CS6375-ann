package edu.utdallas.cs6375.ann.network.neuron;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* Acts as both hidden neurons and output neurons */
public abstract class NetworkNeuron extends Neuron {
    private final Map<Neuron, Double> inputNeuronWeights = Collections.synchronizedMap(new LinkedHashMap<>());
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

//        System.out.println(ID + ": OUTPUT CALC");
        inputNeuronWeights.forEach((neuron, weight) -> {
//                System.out.printf("Neuron: %s, weight: %f%n", neuron.ID, weight);
                this.output += neuron.getOutput() * weight;
        });

//        System.out.println(ID + ": net = " + this.output);
        this.output = activationFunction.apply(this.output);

        if(Double.isInfinite(this.output) || Double.isNaN(this.output)) {
            throw new NeuronException(ID + ": output is infinite or not a number");
        }
    }

    public void updateWeights(double alpha) {
        inputNeuronWeights.replaceAll((neuron, weight) -> weight + alpha * this.delta * neuron.getOutput());
    }

    public double getDelta() {
        return delta;
    }

    public abstract void calculateDelta(double target);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.ID).append("\n")
                .append("value = ").append(this.output)
                .append(" delta = ").append(this.delta)
                .append("\n----- WEIGHTS -----\n");

        inputNeuronWeights.forEach((n, weight) ->
                sb.append(weight).append("; ")
        );

        return sb.append("\n").toString();
    }
}
