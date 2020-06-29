package edu.utdallas.cs6375.ann;

import edu.utdallas.cs6375.ann.data.DataPoint;
import edu.utdallas.cs6375.ann.data.DataSet;
import edu.utdallas.cs6375.ann.network.ANNException;
import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.ArtificialNeuralNetwork;
import edu.utdallas.cs6375.ann.network.neuron.NeuronWeightGenerator;

import java.util.List;

public class AnnApplication {
    private static String dataFilepath;
    private static int iterations = 1000;
    private static double alpha = 1.0;
    private static int hiddenLayers = 2;
    private static int layerWidth = 5;
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
            ann = new ArtificialNeuralNetwork(alpha, 13, hiddenLayers, layerWidth);

            /* Process training data */
            int epochs = iterations;
            for(; iterations > 0; iterations--) {
                System.out.printf("Epoch %d%n", epochs - iterations);

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

            System.out.println(ann.toString());

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

        System.out.printf("Correct predictions: %d\nTotal predictions: %d\nAccuracy: %f\n%n",
                correctPredictions,
                testingData.size(),
                (double) correctPredictions / (double) testingData.size());
    }

    private static void processArgs(String[] args) {
        for(int i = 0; i < args.length - 1; i++) {
            switch(args[i]) {
                case "--file-path":
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
                case "--activation-function":
                    ActivationService.setActivationFunctionName(args[++i]);
                    break;
                case "--hidden-layers":
                    try {
                        hiddenLayers = Integer.parseInt(args[++i]);
                    } catch(Exception e) {
                        System.out.println("Could not parse hidden-layers: " + e.toString());
                    }
                    break;
                case "--layer-width":
                    try {
                        layerWidth = Integer.parseInt(args[++i]);
                    } catch(Exception e) {
                        System.out.println("Could not parse layer-width: " + e.toString());
                    }
                    break;
                case "--weight-upper-bound":
                    try {
                        NeuronWeightGenerator.setUpperBound(Double.parseDouble(args[++i]));
                    } catch(Exception e) {
                        System.out.println("Could not parse weight-upper-bound: " + e.toString());
                    }
                    break;
                case "--weight-lower-bound":
                    try {
                        NeuronWeightGenerator.setLowerBound(Double.parseDouble(args[++i]));
                    } catch(Exception e) {
                        System.out.println("Could not parse weight-lower-bound: " + e.toString());
                    }
                    break;
                default:
                    System.out.println("Unknown option: " + args[i]);
            }
        }

        if(dataFilepath == null) {
            System.out.println("Expects absolute filepath to data csv file as CLI argument: --file-path <absolute_path>");
            System.exit(1);
        }
    }
}
