package jsr381.example;

import javax.visrec.ml.classification.Classifier;


public abstract class MultiClassClassifier<MODEL_CLASS> implements Classifier<float[], String> {

    private MODEL_CLASS model;

    public MODEL_CLASS getModel() {
        return model;
    }

    protected void setModel(MODEL_CLASS model) {
        this.model = model;
    }

}
