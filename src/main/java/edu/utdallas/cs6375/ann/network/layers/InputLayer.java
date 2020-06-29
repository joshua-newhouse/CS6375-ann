package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.neuron.InputNeuron;

import java.util.List;

public class InputLayer extends Layer<InputNeuron> {
    public InputLayer(int inputs) {
        for(; inputs > 0; inputs--) {
            neurons.add(new InputNeuron());
        }

        InputNeuron bias = new InputNeuron();
        bias.setValue(1);
        neurons.add(bias);
    }

    public void setInputs(List<Double> inputs) throws LayerException {
        if(inputs.size() != neurons.size() - 1) {
            throw new LayerException(
                    String.format("Inputs cardinality not equal to input layer: #Inputs: %d; #Inputs in layer: %d",
                            inputs.size(), neurons.size() - 1)
            );
        }
    }
}
