package jsr381.example;

/**
 * This interface should be implemented for classes whose objects we want to be able to classify.
 * Instances of classes that implement this interface can be used to build classifiers.
 * 
 * @author Zoran
 */
public interface Classifiable<T, C> {
    
    /**
     * Returns input for classifier.
     * @return 
     */
    T getClassifierInput();
    
    /**
     * Returns target class for classifier.
     * @return 
     */
    C getTargetClass();
}
