package jsr381.example;

import deepnetts.data.BasicDataSet;
import deepnetts.data.DataSet;
import java.io.IOException;

public class IrisClassificationExample {
    public static void main(String[] args) throws IOException {

        // load iris data set
//        DataSet dataSet = DataSets.readCsv("csvFilePath");
//        DataSet[] trainAndTestSet = DataSets.trainTestSplit(dataSet, 0.7);
        DataSet dataSet = BasicDataSet.fromCSVFile("iris_data_normalised.txt", 4, 3, ",");

        MultiClassClassifier classifier = DeepNettsMultiClassClassifier.builder()
                                                        .inputsNum(4)
                                                        .hiddenLayerSizes(16)
                                                        //.hiddenActivation()
                                                        .outputsNum(3)
                                                        .maxEpochs(10000)
                                                        .maxError(0.01f)
                                                        .learningRate(0.01f)
                                                        .trainingSet(dataSet)
                                                        .build();


        // if training set is specified perform training when build is invoked, otherwice invoke train separetly from building, see bellow
        // if test set is specified also perform performance evalaution
        // performe entire standard ml workflow with meaningfull error messages if something is not right
        // we are not assuming that user know data prep, ml and stats
        // croccvalidation?

      //  classifier.train(trainAndTestSet[0]);
  //      classifier.test(trainAndTestSet[1]);   // do this internally after training, log results, and return Map with performance measures?

    }
}
