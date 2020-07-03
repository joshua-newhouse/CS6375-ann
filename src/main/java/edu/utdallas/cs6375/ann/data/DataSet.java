package edu.utdallas.cs6375.ann.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataSet {
    private final List<DataPoint> dataSet = new ArrayList<>();

    private int testIdx;
    private boolean initialized;

    public DataSet() {
        this.testIdx = 0;
        this.initialized = false;
    }

    public void init(Collection<String> inputDataSet) {
        if(initialized) {
           return;
        }

        inputDataSet.stream().forEach(inputRecord -> {
            String[] rawDataPoint = inputRecord.split(",");
            try {
                dataSet.add(new DataPoint(rawDataPoint));
            } catch (DataException e) {

            }
        });

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
