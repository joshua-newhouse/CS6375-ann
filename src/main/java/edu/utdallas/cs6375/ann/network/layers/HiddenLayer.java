package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.neuron.HiddenNeuron;
import edu.utdallas.cs6375.ann.network.neuron.NetworkNeuron;

public class HiddenLayer extends NetworkLayer {
    public HiddenLayer(int width) {
        for(; width > 0; width--) {
            neurons.add(new HiddenNeuron(ActivationService.getAF(), ActivationService.getAFPrime()));
        }
    }
}
