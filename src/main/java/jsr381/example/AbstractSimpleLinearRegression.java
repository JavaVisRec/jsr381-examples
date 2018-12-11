package jsr381.example;

import javax.visrec.regression.Regressor;

/**
 *
 * @author zoran
 */
public abstract class AbstractSimpleLinearRegression<MODEL_CLASS> implements Regressor<Double, Double> {

    private MODEL_CLASS model;
    
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }
    
    

}
