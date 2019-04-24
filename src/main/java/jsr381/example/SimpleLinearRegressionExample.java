package jsr381.example;

import deepnetts.data.BasicDataSet;
import deepnetts.data.DataSet;
import jsr381.example.util.DataSetExamples;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This example uses a Swedish Auto Insurance Dataset to predict the total
 * payment for all auto insurance claims (in thousands of Swedish Kronor),
 * given the total number of claims
 *
 * @author Zoran Sevarac
 */
public class SimpleLinearRegressionExample {

    public static void main(String[] args) throws IOException {
        // Create a DataSet object from the CSV file
        DataSet dataSet = DataSetExamples.getSimpleLinearRegressionDataSet();

        // Build the model
        SimpleLinearRegression linReg = DeepNettsSimpleLinearRegression.builder()
                .trainingSet(dataSet)
                .learningRate(0.1f)
                .maxError(0.01f)
                .build();
                                                                       // maybe also provide test set and run evaluation after training automatically

      //  linReg.train(trainingSet);    // TODO: move training set to train method
        // PerformanceMeasure pm = linReg.test(testSet); // Model intreface with train and test methods
        // System.out.println(pm);

        // Display information about the trained model
        float slope = linReg.getSlope();
        float intercept = linReg.getIntercept();
        System.out.println("Trained Model y = " + slope + " * x + " + intercept);

        // Predict the outcome based on some input.
        float someInput = 0.10483871f;
        Float prediction = linReg.predict(someInput);
        System.out.println("predicted output for " + (someInput*124) + " is:" + (prediction*422.2));


        //
        // Evaluators.evaluate();
        // evaluate model
    ///    PerformanceMetrics perfMetrics= evaluator.evaluate(linReg);

        // RSE = sqrt(RSS/(n-2))    estimate of error std, average amount of error that deviate from true regression line, close to   RMSE
        // R2 = 1 - RSS/TSS         tells us does the regression explains variability in response Y, 1 is good, 0 bad
       // System.out.println(perfMetrics);
        // TODO: plot data set and  line

    }

}
