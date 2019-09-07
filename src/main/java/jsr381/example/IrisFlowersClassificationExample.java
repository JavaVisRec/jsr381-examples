package jsr381.example;

import deepnetts.data.DataSets;
import deepnetts.eval.Evaluators;
import jsr381.example.util.DataSetExamples;
import visrec.ri.ml.classification.MultiClassClassifierNetwork;

import java.io.IOException;
import java.util.Map;
import javax.visrec.ml.classification.MultiClassClassifier;
import javax.visrec.ml.data.DataSet;

public class IrisFlowersClassificationExample {
    public static void main(String[] args) throws IOException {

        // load iris data set
        DataSet dataSet = DataSetExamples.getIrisClassificationDataSet();
        DataSet[] trainTest = DataSets.trainTestSplit(dataSet, 0.6);

        // build multi class classifier using deep netts implementation of feed forward network under the hood
        MultiClassClassifier<float[], String> irisClassifier = MultiClassClassifierNetwork.builder() 
                                                                .inputsNum(4)
                                                                .hiddenLayers(16)                       
                                                                .outputsNum(3)                          
                                                                .maxEpochs(2000)
                                                                .maxError(0.03f)
                                                                .learningRate(0.01f)
                                                                .trainingSet(trainTest[0])
                                                                .build();

        // use classifier to predict class
        Map<String, Float> results = irisClassifier.classify(new float[] {0.1f, 0.2f, 0.3f, 0.4f});
        System.out.println(results);

        //Evaluators.evaluateClassifier(classifier.geModel(), dataSet);
    }
}
