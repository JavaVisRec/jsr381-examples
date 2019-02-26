package jsr381.example;

import deepnetts.data.DataSet;
import deepnetts.data.DataSets;
import java.io.IOException;
import java.util.Map;

public class IrisClassificationExample {
    public static void main(String[] args) throws IOException {

        // load iris data set
        //DataSet dataSet = BasicDataSet.fromCSVFile("iris_data_normalised.txt", 4, 3, ",");
        DataSet dataSet = DataSets.readCsv("iris_data_normalised.txt", 4, 3, true);
        DataSet[] trainAndTestSet = DataSets.trainTestSplit(dataSet, 0.7);

        // build multi class classifier using deep netts implementation of feed forward network under the hood
        MultiClassClassifier classifier = DeepNettsMultiClassClassifier.builder()
                                                        .inputsNum(4)
                                                        .hiddenLayers(16)
                                                        //.hiddenActivation() // todo
                                                        .outputsNum(3)
                                                        .maxEpochs(10000)
                                                        .maxError(0.05f)
                                                        .learningRate(0.01f)
                                                        .trainingSet(trainAndTestSet[0])
                                                        .build();

        // evaluate data sets

        // use classifier to predict class
        Map<String, Float> results = classifier.classify(args);

        // if training set is specified perform training when build is invoked, otherwice invoke train separetly from building, see bellow
        // if test set is specified also perform performance evalaution
        // performe entire standard ml workflow with meaningfull error messages if something is not right
        // we are not assuming that user know data prep, ml and stats
        // croccvalidation?

      //  classifier.train(trainAndTestSet[0]);
  //      classifier.test(trainAndTestSet[1]);   // do this internally after training, log results, and return Map with performance measures?

    }
}
