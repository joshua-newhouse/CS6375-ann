package edu.utdallas.cs6375.ann.network;

import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;

public class ActivationService {
    private static String activationFunctionName = "sigmoid";

    private ActivationService() {
    }

    public static NetworkNeuron.ActivationFunction getAF() {
        switch(activationFunctionName) {
            case "tanh":
                return Math::tanh;
            case "relu":
                return (num) -> Math.max(0.0, num);
            default:
                return (num) -> (1.0 / (1.0 + Math.exp(-1.0 * num)));
        }
    }

    public static NetworkNeuron.ActivationFunction getAFPrime() {
        switch(activationFunctionName) {
            case "tanh":
                return (num) -> {
                    double tnh = Math.tanh(num);
                    return 1 - tnh * tnh;
                };
            case "relu":
                return (num) -> num < 0.0 ? 0.0 : 1;
            default:
                return (num) -> num * (1.0 - num);
        }
    }

    public static void setActivationFunctionName(String name) {
        activationFunctionName = name;
    }
}
