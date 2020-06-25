package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.data.DataSet;

public class AnnApplication {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Expects absolute filepath to data csv file as first and only argument.");
            System.exit(1);
        }

        DataSet dataSet = new DataSet(args[0]);

        dataSet.init();

        System.out.println(dataSet.getTrainingData().get(0).toString());
        System.out.println(dataSet.getTestingData().get(0).toString());

        System.out.println("Training data size: " + dataSet.getTrainingData().size());
        System.out.println("Testing data size: " + dataSet.getTestingData().size());
    }
}
