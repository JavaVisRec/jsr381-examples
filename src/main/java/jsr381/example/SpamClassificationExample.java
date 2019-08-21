package jsr381.example;

import deepnetts.data.DataSet;
import deepnetts.data.DataSets;
import java.io.IOException;
import java.util.Map;
import javax.visrec.ml.classification.Classifier;

/**
 * Minimum example for creating binary classifier from CSV file.
 * This example shows how to instantiate, train, evaluate and use Logistic Regression using Machine Learning Layer from VisRec API.
 * 
 * @author Zoran Sevarac
 */
public class SpamClassificationExample {

    public static void main(String[] args) throws IOException {
        
        // create data set from specified file 
        DataSet dataSet = DataSets.readCsv("spam.csv", 57, 1, true);
        
        // Build binary classifer based on neural network
        Classifier<float[], Boolean> spamClassifier = BinaryClassifierNetwork.builder()            
                                                        .inputsNum(57)
                                                        .hiddenLayers(5)
                                                        .maxError(0.03)
                                                        .maxEpochs(15000)
                                                        .learningRate(0.01f)                     
                                                        .trainingSet(dataSet)           
                                                        .build();

        // using trained classifier
        float[] testEmail = getExampleEmailToClassify();
        Map<Boolean, Float> result = spamClassifier.classify(testEmail);
        System.out.println(result.get(Boolean.TRUE));        
    }

    static float[] getExampleEmailToClassify() {
        float[] emailFeatures = new float[57];
        emailFeatures[56] = 1;
         return emailFeatures;
    }

}