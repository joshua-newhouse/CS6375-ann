package edu.utdallas.cs6375.ann.network.layers;

import edu.utdallas.cs6375.ann.network.neuron.Neuron;

import java.util.ArrayList;
import java.util.List;

public abstract class Layer<N extends Neuron> {
    protected List<N> neurons = new ArrayList<>();
    protected String ID = "";

    public void addNextLayer(NetworkLayer next) {
        this.neurons.forEach(next::addInputNeuron);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(ID).append("\n");
        neurons.forEach(n -> sb.append(n.toString()).append("\n"));

        return sb.toString();
    }
}
