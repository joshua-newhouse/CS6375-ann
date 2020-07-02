package edu.utdallas.cs6375.ann.data;

public class NormalizedData {
    private double min;
    private double max;
    private double dif;
    public NormalizedData() {
    }

    public void add(double v) {
        if(v > this.max) {
            this.max = v;
            this.dif = max - min;
        } else if(v < this.min) {
            this.min = v;
            this.dif = max - min;
        }
    }

    public double getNormalizedForValue(double v) {
        return (v - this.min) / this.dif;
    }
}
