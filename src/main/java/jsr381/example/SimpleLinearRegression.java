package jsr381.example;

import javax.visrec.regression.Regressor;

/**
 * Our ML API specifies what are the inputs, what are the outputs, what type of ml task specific algorithm performs
 * It simplifies  usage to software developers
 * 
 * @author zoran
 */
public abstract class SimpleLinearRegression<MODEL_CLASS> implements Regressor<Float, Float> {

    private MODEL_CLASS model;
    
    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }
    
    public abstract Float predict(Float inputs);    
    
    public abstract float getSlope();

    public abstract float getIntercept();
    // ili da vracam parametre modela u mapi?
    // ili kao niz koeficijenata?

    // performan ce measures
    // RSE
    // R2    
    

//    Map.new().put()
    
}
