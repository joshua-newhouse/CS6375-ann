package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.data.DataPoint;
import edu.utdallas.cs6375.ann.data.DataSet;
import edu.utdallas.cs6375.ann.network.ANNException;
import edu.utdallas.cs6375.ann.network.ArtificialNeuralNetwork;

import java.util.List;

public class AnnApplication {
    private static String dataFilepath;
    private static int iterations = 1000;
    private static double alpha = 100.0;
    private static int correctPredictions;

    public static void main(String[] args) {
        processArgs(args);

        /* Read data */
        DataSet dataSet = new DataSet(dataFilepath);
        dataSet.init();
        List<DataPoint> trainingData = dataSet.getTrainingData();
        List<DataPoint> testingData = dataSet.getTestingData();

        try {
            /* Create ANN */
            ArtificialNeuralNetwork ann;
            ann = new ArtificialNeuralNetwork(alpha, 13, 1);

            /* Process training data */
            for(; iterations > 0; iterations--) {
                trainingData.forEach(dataPoint -> {
                    try {
                        ann.setInput(dataPoint.asInputsList(), dataPoint.target());
                        ann.forwardPass();
                        ann.backwardPropagate();
                        ann.updateWeights();
                    } catch(ANNException e) {
                        System.out.println("Failed training data point: " + dataPoint.toString() + " ::: " + e.toString());
                    }
                });
            }

            /* Validate against testing data */
            testingData.forEach(dataPoint -> {
                try {
                    ann.setInput(dataPoint.asInputsList(), dataPoint.target());
                    ann.forwardPass();
                    correctPredictions += ann.isPredictionGood() ? 1 : 0;
                } catch(ANNException e) {
                    System.out.println("Failed testing data point: " + dataPoint.toString() + " ::: " + e.toString());
                }
            });

        } catch(ANNException e) {
            System.out.println("Failed creating network: " + e.toString());
            System.exit(1);
        }

        System.out.println(String.format("Correct predictions: %d\nTotal predictions: %d\nAccuracy: %f\n",
                correctPredictions,
                testingData.size(),
                (double) correctPredictions / (double) testingData.size()));
    }

    private static void processArgs(String[] args) {
        for(int i = 0; i < args.length - 1; i++) {
            switch(args[i]) {
                case "--filePath":
                    dataFilepath = args[++i];
                    break;
                case "--iterations":
                    try {
                        iterations = Integer.parseInt(args[++i]);
                    } catch(Exception e) {
                        System.out.println("Could not parse iterations: " + e.toString());
                    }
                    break;
                case "--alpha":
                    try {
                        alpha = Double.parseDouble(args[++i]);
                    } catch(Exception e) {
                        System.out.println("Could not parse alpha: " + e.toString());
                    }
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
            }
        }

        if(dataFilepath == null) {
            System.out.println("Expects absolute filepath to data csv file as CLI argument: --filePath <absolute_path>");
            System.exit(1);
        }
    }
}
