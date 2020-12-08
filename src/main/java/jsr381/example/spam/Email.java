package jsr381.example.spam;

/**
 * Example Email class that implements Classifiable interface in order to enable classification of its instances.
 * It should wrap instance of some email, and provide interface that will allow building classifiers.
 * and classifying instances of this class.
 * The end user should not thing about low level how to convert/encode objects into the for used by ML algorithm.
 * 
 * @see Classifier 
 * @author Zoran Sevarac
 */
public final class Email implements Classifiable<float[], Boolean> {
    
    private final float[] emailFeatures;
    private Boolean isSpam;
        
    public Email(String message) {
        // transform email to feature vector: extract uppercale letters, keywords, etc.
        emailFeatures = new float[57];
        emailFeatures[56] = 1;        
    }
    
    @Override
    public float[] getClassifierInput() {
        return emailFeatures;
    }

    @Override
    public Boolean getTargetClass() {
        return isSpam;
    }
    
}
