package jsr381.example.spam;

import deepnetts.data.DataSets;
import visrec.ri.ml.classification.BinaryClassifierNetwork;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.classification.DeprecatedBinaryClassifier;
import javax.visrec.ml.data.DataSet;
import java.io.IOException;
import java.net.URL;

/**
 * Minimum example for creating binary classifier from CSV file.
 * This example shows how to instantiate, train, evaluate and use Logistic Regression using Machine Learning Layer from VisRec API.
 * 
 * @author Zoran Sevarac
 */
public class SpamClassificationExample {

    public static void main(String[] args) throws IOException, ClassificationException {
        
        // create data set from specified file
        URL spamCsvResource = SpamClassificationExample.class.getClassLoader().getResource("spam.csv");
        if (spamCsvResource == null) {
            throw new IOException("spam.csv not found");
        }
        DataSet dataSet = DataSets.readCsv(spamCsvResource.getFile(), 57, 1, true);
        DataSets.normalizeMax(dataSet);
        
        // Build binary classifer based on neural network
        DeprecatedBinaryClassifier<float[]> spamClassifier = BinaryClassifierNetwork.builder()
                                                        .inputsNum(57)
                                                        .hiddenLayers(5)
                                                        .maxError(0.03)
                                                        .maxEpochs(15000)
                                                        .learningRate(0.01f)                     
                                                        .trainingSet(dataSet)           
                                                        .build();

        // using trained classifier
        float[] testEmail = getExampleEmailToClassify();
        Float result = spamClassifier.classify(testEmail);
        System.out.println(result);        
    }

    static float[] getExampleEmailToClassify() {
        float[] emailFeatures = new float[57];
        emailFeatures[56] = 1;
         return emailFeatures;
    }

}