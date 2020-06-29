package edu.utdallas.cs6375.ann.network;

import edu.utdallas.cs6375.ann.network.layers.*;

import java.util.ArrayList;
import java.util.List;

public class ArtificialNeuralNetwork {
    private static final int layerWidth = 4;

    private double alpha;

    private InputLayer inputLayer;
    private OutputLayer outputLayer;
    private List<NetworkLayer> layers = new ArrayList<>();

    private double target;

    public ArtificialNeuralNetwork(double alpha,
                                   int inputs,
                                   int hiddenLayers) throws ANNException {
        this.alpha = alpha;
        initializeHiddenLayers(hiddenLayers);
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

    public void initializeHiddenLayers(int num) throws ANNException {
        if(num < 0) {
            throw new ANNException("Invalid number of hidden layers: " + num);
        }

        for(; num > 0; num--) {
            layers.add(new HiddenLayer(layerWidth));
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
        for(int i = layers.size() - 1; i > 0; i--) {
            layers.get(i).calculateDeltas(this.target);
        }
    }

    public void updateWeights() {
        layers.forEach(layer -> layer.updateWeights(alpha));
    }

    public boolean isPredictionGood() {
        return this.target * outputLayer.getOutput() > 0.0;
    }
}
