package jsr381.example;

import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import java.util.Properties;
import javax.visrec.regression.Regressor;
import javax.visrec.util.Builder;

/**
 * Simple linear regression provides best possible straight line approximation of the given data set.
 * It can be used to get rough answers to the following question:
 * - is there a relationship between two variables
 * - how strong is the relationship between two variables
 * ...
 * 
 * https://en.wikipedia.org/wiki/Simple_linear_regression
 * @author Zoran Sevarac
 */
public final class SimpleLinearRegression implements Regressor<Double, Double> {
    
    private double slope, intercept;
    
    @Override
    public Double predict(Double inputs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }
    
    
    
    public static SimpleLinearRegressionBuilder builder() {
        return new SimpleLinearRegressionBuilder();
    }
    
    public static class SimpleLinearRegressionBuilder implements Builder<SimpleLinearRegression> {

        FeedForwardNetwork ffn;
        
        public SimpleLinearRegressionBuilder hiddenLayerWidth(int width) {
            
        }
        
        @Override
        public SimpleLinearRegression build() {
            FeedForwardNetwork ffn = FeedForwardNetwork.builder()
                                            .addInputLayer(1)
                                            .addDenseLayer(1)
                                            .addOutputLayer(1, ActivationType.LINEAR)
                                            .build();
            return this;
        }

        @Override
        public SimpleLinearRegression build(Properties prop) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
}
