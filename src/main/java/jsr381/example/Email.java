package jsr381.example;

/**
 * Example Email class that implements Classifiable interface in order to enable classification of its instances.
 * 
 * @see Classifier 
 * @author Zoran Sevarac
 */
public final class Email implements Classifiable<float[], Boolean> {
    
    private float[] emailFeatures;
    private Boolean isSpam;
        
    public Email(String message) {
        // transform email to feature vector
        emailFeatures = new float[57];
        emailFeatures[56] = 1;        
    }
    
    // TODO: method to transform email message object to feature vector

    @Override
    public float[] getClassifierInput() {
        return emailFeatures;
    }

    @Override
    public Boolean getTargetClass() {
        return isSpam;
    }
    
}
