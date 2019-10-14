package jsr381.example;

/**
 * This interface should be implemented for classes whose objects we want to be able to classify.Instances of classes that implement this interface can be classified, and used as examples
 to build machine learning based classifiers.Typical implementation scenario is to wrap domain specific class and implement this interface.
 * 
 * @author Zoran Sevarac
 * @param <T> Type of input for classifier
 * @param <C> Type of class instances (could be anything like enum, String, Integer or user defined class)
 */
public interface Classifiable<T, C> {
    
    /**
     * Returns input for classifier.
     * Implementation of this method should convert attributes of an object to fit specific classifier.
     * @return 
     */
    T getClassifierInput();
    
    /**
     * Returns target class for classifier.
     * @return 
     */
    C getTargetClass();
}
