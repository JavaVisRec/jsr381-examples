package jsr381.example;

import deepnetts.data.DataSets;
import jsr381.example.util.DataSetExamples;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.classification.MultiClassClassifier;
import javax.visrec.ml.data.DataSet;
import javax.visrec.ri.ml.classification.MultiClassClassifierNetwork;
import java.io.IOException;
import java.util.Map;

public class IrisFlowersClassificationExample {
    public static void main(String[] args) throws IOException, ClassificationException {

        // Load iris data set
        DataSet dataSet = DataSetExamples.getIrisClassificationDataSet();
        DataSet[] trainTest = DataSets.trainTestSplit(dataSet, 0.7);

        // Build multi class classifier using Deep Netts implementation of Feed Forward Network under the hood
        MultiClassClassifier<float[], String> irisClassifier = MultiClassClassifierNetwork.builder()
                                                                .inputsNum(4)
                                                                .hiddenLayers(16)
                                                                .outputsNum(3)
                                                                .maxEpochs(9000)
                                                                .maxError(0.03f)
                                                                .learningRate(0.01f)
                                                                .trainingSet(trainTest[0])
                                                                .build();

        // Use classifier to predict class - returns a map with probabilities associated to possible classes
        Map<String, Float> results = irisClassifier.classify(new float[] {0.1f, 0.2f, 0.3f, 0.4f});
        System.out.println(results);
    }
}
