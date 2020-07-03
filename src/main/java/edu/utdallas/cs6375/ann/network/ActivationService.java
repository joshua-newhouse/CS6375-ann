package edu.utdallas.cs6375.ann.network;

import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;

public class ActivationService {
    private ActivationService() {
    }

    public static NetworkNeuron.ActivationFunction getAF(String activationFunctionName) {
        switch(activationFunctionName) {
            case "tanh":
                return Math::tanh;
            case "relu":
                return (num) -> Math.max(0.0, num);
            case "sigmoid":
            default:
                return (num) -> (1.0 / (1.0 + Math.exp(-1.0 * num)));
        }
    }

    public static NetworkNeuron.ActivationFunction getAFPrime(String activationFunctionName) {
        switch(activationFunctionName) {
            case "tanh":
                return (o) -> 1 - o * o;
            case "relu":
                return (o) -> o < 0.0 ? 0.0 : 1;
            case "sigmoid":
            default:
                return (o) -> o * (1.0 - o);
        }
    }
}
