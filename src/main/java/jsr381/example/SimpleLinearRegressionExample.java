package jsr381.example;

import deepnetts.data.BasicDataSet;
import deepnetts.data.DataSet;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author zoran
 */
public class SimpleLinearRegressionExample {
    
    public static void main(String[] args) throws IOException {

        String datasetFile = "SweedenAutoInsurance.csv";
        int inputsCount = 1;
        int outputsCount = 1;
        String delimiter = ",";
        
        // create data set from csv file
        DataSet dataSet = BasicDataSet.fromCSVFile(new File(datasetFile), inputsCount, outputsCount, delimiter);        
        
        // build model
        SimpleLinearRegression linReg = DeepNettsSimpleLinearRegression.builder()
                                                                       .trainingSet(dataSet)    
                                                                       .learningRate(0.1f)
                                                                       .maxError(0.01f)
                                                                       .build();    
                                                                       // maybe also provide test set and run evaluation after training automatically
        
        // use model         
        float someInput = 0.1f;  
        Float result = linReg.predict(someInput);
        
        float slope = linReg.getSlope();
        float intercept = linReg.getIntercept();      
  
        System.out.println("Model y = " + slope + " * x + "+intercept);
                
        // evaluate model
    ///    PerformanceMetrics perfMetrics= evaluator.evaluate(linReg);
        
        // RSE = sqrt(RSS/(n-2))    estimate of error std, average amount of error that deviate from true regression line, close to   RMSE
        // R2 = 1 - RSS/TSS         tells us does the regression explains variability in response Y, 1 is good, 0 bad
       // System.out.println(perfMetrics);
        // TODO: plot data set and  line
        
    }
    
}
