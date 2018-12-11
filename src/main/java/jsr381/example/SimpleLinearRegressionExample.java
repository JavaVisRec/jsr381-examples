package jsr381.example;

/**
 *
 * @author zoran
 */
public class SimpleLinearRegressionExample {
    
    public static void main(String[] args) {
        SimpleLinearRegression linReg = SimpleLinearRegression.builder()
        //                                            .trainingSet(trainingSet)    
                                                    //.withInputColumn("budget")    maybe set these on dataSet
                                                    //.withTargetColumn("sales")                                                    
                                                    .build();    
    //    linReg.train(trainingSet); // umesto specifikacije u builderu - tako radi scikit learn
       // linReg.test(testSet);
        
        double someInput = 0.1;  
        double result = linReg.predict(someInput);
        

        double slope = linReg.getSlope();
        double intercept = linReg.getIntercept();      
        
        // TODO: plot data set and  line
        
    }
    
}
