/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsr381.example;

import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import java.util.Properties;

/**
 *
 * @author zoran
 */
public class DeepNettsSimpleLinearRegression extends AbstractSimpleLinearRegression<FeedForwardNetwork>{

    public Double predict(Double inputs) {
      //  getModel().setInput(inputs);
      return 1d;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder implements javax.visrec.util.Builder<DeepNettsSimpleLinearRegression> {
        DeepNettsSimpleLinearRegression product = new DeepNettsSimpleLinearRegression();
        
        int hiddenNeurons = 1;
        float learningRate = 0.01f;
        
        // hidden neurons, learning rate, ...
        public Builder hiddenNeurons(double hiddenNeurons) {
            
        }
        
        public Builder learningRate(float learningRate) {
            
        }
        
        public DeepNettsSimpleLinearRegression build() {
            FeedForwardNetwork model= FeedForwardNetwork.builder()
                                        .addInputLayer(1)
                                        .addDenseLayer(5)   // ovo kao parametar -- minimmum parameters
                                        .addOutputLayer(1, ActivationType.LINEAR)
                                        .build();
            product.setModel(model);
            return product;
        }

        public DeepNettsSimpleLinearRegression build(Properties prop) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
}
