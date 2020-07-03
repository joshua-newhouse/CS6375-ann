package edu.utdallas.cs6375.ann;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.S3Object;
import edu.utdallas.cs6375.ann.data.DataPoint;
import edu.utdallas.cs6375.ann.data.DataSet;
import edu.utdallas.cs6375.ann.network.ANNException;
import edu.utdallas.cs6375.ann.network.ActivationService;
import edu.utdallas.cs6375.ann.network.ArtificialNeuralNetwork;
import edu.utdallas.cs6375.ann.network.neuron.NeuronException;
import edu.utdallas.cs6375.ann.network.neuron.NeuronWeightGenerator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnnApplication {
    private static int iterations = 1000;
    private static double alpha = 1.0;
    private static int hiddenLayers = 2;
    private static int layerWidth = 5;
    private static String activationFunction = "sigmoid";
    private static int correctPredictions;
    private static String BUCKET_NAME = "cs6375-vxs190040";
    private static String FILE_NAME = "adult.data";

    public static void main(String[] args) {
        Collection<String> inputDataSet = readS3File();
        processArgs(args);
        /* Read data */
        DataSet dataSet = new DataSet();
        dataSet.init(inputDataSet);
        List<DataPoint> trainingData = dataSet.getTrainingData();
        List<DataPoint> testingData = dataSet.getTestingData();

        try {
            /* Create ANN */
            ArtificialNeuralNetwork ann;
            ann = new ArtificialNeuralNetwork(alpha, 13, hiddenLayers, layerWidth, activationFunction);

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
                    } catch(NeuronException e) {
                        System.out.println(e.toString());
                        System.out.println(ann.toString());
                        System.exit(1);
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
                    activationFunction = args[++i];
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
    }

    private  static Collection<String> readS3File() {
            Collection<String> inputDataSet = null;
            AmazonS3 amazonS3Client = AmazonS3Client.builder()
                    .withRegion("us-east-1")
                    .build();
            final S3Object s3Object = amazonS3Client.getObject(BUCKET_NAME, FILE_NAME);
            final InputStreamReader streamReader = new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8);
            final BufferedReader reader = new BufferedReader(streamReader);
            inputDataSet = reader.lines().collect(Collectors.toSet());
            inputDataSet = inputDataSet.stream().filter(inputData -> inputData != null && !inputData.equals("")).collect(Collectors.toList());
            return inputDataSet;
        }

}