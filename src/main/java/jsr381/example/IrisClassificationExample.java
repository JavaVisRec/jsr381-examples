package jsr381.example;

import deepnetts.data.DataSets;
import deepnetts.data.DeepNettsDataSetItem;
import jsr381.example.util.DataSetExamples;
import visrec.ri.ml.classification.MultiClassClassifierNetwork;

import javax.visrec.ml.classification.MultiClassClassifier;
import java.io.IOException;
import java.util.Map;
import javax.visrec.ml.data.DataSet;

public class IrisClassificationExample {
    public static void main(String[] args) throws IOException {

        // load iris data set
        DataSet<DeepNettsDataSetItem> dataSet = DataSetExamples.getIrisClassificationDataSet();
        DataSet[] trainTest = DataSets.trainTestSplit(dataSet, 0.6);

        // build multi class classifier using deep netts implementation of feed forward network under the hood
        MultiClassClassifier classifier = MultiClassClassifierNetwork.builder() // rename to feed forward multi class classifier
                                                        .inputsNum(4)
                                                        .hiddenLayers(16)                       // if its a common thing put it in the API
                                                        .outputsNum(3)                          //I think its ok to use implementation specific type since this is specific impl
                                                        .maxEpochs(2000)
                                                        .maxError(0.03f)
                                                        .learningRate(0.01f)
                                                        .trainingSet(trainTest[0])
                                                        .build();

        // use classifier to predict class
        Map<String, Float> results = classifier.classify(new float[] {0.1f, 0.2f, 0.3f, 0.4f});
        System.out.println(results);

    }
}
