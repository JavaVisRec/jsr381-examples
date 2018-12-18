package jsr381.example;

import java.util.Map;
import java.util.Properties;
import javax.visrec.ml.classification.Classifier;
import javax.visrec.ml.data.DataSet;
import javax.visrec.util.Builder;

/**
 * This class performs basic binary classification - mapping of specified input to true/false with probability.
 * 
 * @author Zoran Sevarac
 */
public abstract class LogisticRegression<MODEL_CLASS> implements Classifier<float[], Boolean>{ // better to return float instead of Map with boolean

    private MODEL_CLASS model;
    
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }
    
   
    @Override
    public abstract Map<Boolean, Float> classify(float[] someInput);
    
    public static class LogisticRegressionBuilder implements Builder<LogisticRegression> {

        @Override
        public LogisticRegression build(Properties prop) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public LogisticRegression build() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public LogisticRegressionBuilder trainingSet(DataSet<double[]> traininGSet) {
            return this;
        }
        
    } 

    public static LogisticRegressionBuilder builder() {
            return new LogisticRegressionBuilder();
    }
    
}
