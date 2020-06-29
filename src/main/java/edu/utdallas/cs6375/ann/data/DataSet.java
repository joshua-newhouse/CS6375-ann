package edu.utdallas.cs6375.ann.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataSet {
    private final String filepath;
    private final List<DataPoint> dataSet = new ArrayList<>();

    private int testIdx;
    private boolean initialized;

    public DataSet(String filepath) {
        this.filepath = filepath;
        this.testIdx = 0;
        this.initialized = false;
    }

    public void init() {
        if(initialized) {
           return;
        }

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while((line = br.readLine()) != null) {
                String[] rawDataPoint = line.split(",");

                try {
                    dataSet.add(new DataPoint(rawDataPoint));
                } catch (DataException e) {
//                    System.out.println(String.format("Failed creating data point: %s", e.toString()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        testIdx = dataSet.size() * 8 / 10;
        initialized = true;
    }

    public List<DataPoint> getTrainingData() {
        return Collections.unmodifiableList(dataSet.subList(0, testIdx));
    }

    public List<DataPoint> getTestingData() {
        return Collections.unmodifiableList(dataSet.subList(testIdx, dataSet.size()));
    }
}
