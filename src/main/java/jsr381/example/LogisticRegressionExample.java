package jsr381.example;

import deepnetts.data.BasicDataSet;
import deepnetts.data.DataSet;
import jsr381.example.util.DataSetExamples;

import java.io.IOException;
import java.net.URL;
import java.util.Map;

/**
 *
 * @author zoran
 */
public class LogisticRegressionExample {

    // set
    // with
    // use
    //nothing

    public static void main(String[] args) throws IOException {
        // Get the DataSet from file location
        DataSet dataSet = DataSetExamples.fromURL(new URL(" https://raw.githubusercontent.com/JavaVisRec/jsr381-examples-datasets/master/sonar.csv"),
                ",", 60, 1, false);
        // Build logistic regression classifier
        LogisticRegression logReg  = DeepNettsLogisticRegression.builder()
                                            .inputsNum(60)
                                            .trainingSet(dataSet)
                                            .learningRate(0.2f)
                                            .maxError(0.03f)
                                            .maxEpochs(500)
                                            .build();

        // Classify the input
        float[] someInput = new float[]{
                0.02f,0.0371f,0.0428f,0.0207f,0.0954f,0.0986f,0.1539f,0.1601f,0.3109f,0.2111f,0.1609f,0.1582f,0.2238f,
                0.0645f,0.066f,0.2273f,0.31f,0.2999f,0.5078f,0.4797f,0.5783f,0.5071f,0.4328f,0.555f,0.6711f,0.6415f,
                0.7104f,0.808f,0.6791f,0.3857f,0.1307f,0.2604f,0.5121f,0.7547f,0.8537f,0.8507f,0.6692f,0.6097f,0.4943f,
                0.2744f,0.051f,0.2834f,0.2825f,0.4256f,0.2641f,0.1386f,0.1051f,0.1343f,0.0383f,0.0324f,0.0232f,0.0027f,
                0.0065f,0.0159f,0.0072f,0.0167f,0.018f,0.0084f,0.009f,0.0032f};
        Map<Boolean, Float> result = logReg.classify(someInput);

        System.out.println(result);

        // performa classifier performance evaluation
        // evaluate performance using test set
        // logReg.evaluate(trainAndTestSet[1]); // create internal evaluator, performa evaluation and return metrics

    }

}
