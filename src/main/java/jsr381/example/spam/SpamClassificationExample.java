package jsr381.example.spam;

import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.NeuralNetBinaryClassifier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import javax.visrec.ml.model.ModelCreationException;

/**
 * Minimum example for creating binary classifier from CSV file.
 * This example shows how to instantiate, train, evaluate and use Logistic Regression using Machine Learning Layer from VisRec API.
 * 
 * @author Zoran Sevarac
 */
public class SpamClassificationExample {

    public static void main(String[] args) throws IOException, ModelCreationException {
        
        // create data set from specified file
        URL spamCsvResource = SpamClassificationExample.class.getClassLoader().getResource("spam.csv");
        if (spamCsvResource == null) {
            throw new IOException("spam.csv not found");
        }

        // Build binary classifer based on neural network
        BinaryClassifier<float[]> spamClassifier = NeuralNetBinaryClassifier.builder()
                                                        .inputClass(float[].class)
                                                        .inputsNum(57)
                                                        .hiddenLayers(30, 15)
                                                        .maxError(0.03f)
                                                        .maxEpochs(15000)
                                                        .learningRate(0.01f)                     
                                                        .trainingPath(Paths.get(spamCsvResource.getFile()))
                                                        .build();

        // using trained classifier
        float[] testEmail = getExampleEmailToClassify();
        Float result = spamClassifier.classify(testEmail);
        System.out.println(result);
        
        Email email2=new Email("Some email string ...");
        //BinaryClassifier<Email> spamClassifier2 =null;
        float spamProbability = spamClassifier.classify(email2.getClassifierInput());
    }

    static float[] getExampleEmailToClassify() {
        float[] emailFeatures = new float[57];
        emailFeatures[56] = 1;
         return emailFeatures;
    }

}