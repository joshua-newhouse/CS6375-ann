package edu.utdallas.cs6375.ann.network;

import edu.utdallas.cs6375.ann.network.layers.*;

import java.util.ArrayList;
import java.util.List;

public class ArtificialNeuralNetwork {
    private final int layerWidth;

    private double alpha;

    private InputLayer inputLayer;
    private OutputLayer outputLayer;
    private List<NetworkLayer> layers = new ArrayList<>();

    private double target;

    public ArtificialNeuralNetwork(double alpha,
                                   int inputs,
                                   int hiddenLayers,
                                   int layerWidth,
                                   String activationFunction) throws ANNException {
        this.alpha = alpha;
        this.layerWidth = layerWidth;
        initializeHiddenLayers(hiddenLayers, activationFunction);
        initializeOutputLayer();
        initializeInputLayer(inputs);

        layers.add(outputLayer);
        for(int i = 0; i < layers.size() - 1; i++) {
            layers.get(i).addNextLayer(layers.get(i + 1));
        }

        inputLayer.addNextLayer(layers.get(0));
    }

    public void initializeInputLayer(int num) throws ANNException {
        if(num < 1) {
            throw new ANNException("Invalid number of inputs: " + num);
        }

        this.inputLayer = new InputLayer(num);
    }

    public void initializeOutputLayer() {
        this.outputLayer = new OutputLayer();
    }

    public void initializeHiddenLayers(int num, String activationFunction) throws ANNException {
        if(num < 0) {
            throw new ANNException("Invalid number of hidden layers: " + num);
        }

        for(int i = 0; i < num; i++) {
            HiddenLayer next = new HiddenLayer(layerWidth, Integer.toString(i), activationFunction);
            layers.add(next);
        }
    }

    public void setInput(List<Double> inputs, double target) throws ANNException {
        this.target = target;

        try {
            inputLayer.setInputs(inputs);
        } catch(LayerException e) {
            throw new ANNException(e);
        }
    }

    public void forwardPass() {
        layers.forEach(NetworkLayer::calculateOutputs);
    }

    public void backwardPropagate() {
        for(int i = layers.size() - 1; i >= 0; i--) {
            layers.get(i).calculateDeltas(this.target);
        }
    }

    public void updateWeights() {
        layers.forEach(layer -> layer.updateWeights(alpha));
    }

    public boolean isPredictionGood() {
        double o = outputLayer.getOutput();
        double prediction = Math.abs(o - 1.0) < Math.abs(o) ? 1.0 : 0.0;
        return this.target == prediction;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("Input Layer:\n%s\n", inputLayer.toString()));
        layers.forEach(layer -> sb.append(String.format("Layer:\n%s\n", layer.toString())));

        return sb.toString();
    }
}
