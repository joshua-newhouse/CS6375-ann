package edu.utdallas.cs6375.ann.network;

import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;

public class ActivationService {
    private static String activationFunctionName = "sigmoid";

    private ActivationService() {
    }

    public static NetworkNeuron.ActivationFunction getAF() {
        switch(activationFunctionName) {
            default:
                return (num) -> (1.0 / (1.0 + Math.exp(-1.0 * num)));
        }
    }

    public static NetworkNeuron.ActivationFunction getAFPrime() {
        switch(activationFunctionName) {
            default:
                return (num) -> num * (1.0 - num);
        }
    }

    public void setActivationFunctionName(String name) {
        activationFunctionName = name;
    }
}
