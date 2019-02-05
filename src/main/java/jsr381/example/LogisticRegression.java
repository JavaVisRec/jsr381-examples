package jsr381.example;

import javax.visrec.ml.classification.Classifier;
//import javax.visrec.ml.data.DataSet;

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


    // add train method here so we can dow model.train()
}
