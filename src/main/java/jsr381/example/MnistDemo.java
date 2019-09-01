package jsr381.example;

import deepnetts.data.DataSets;
import deepnetts.data.ImageSet;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.ml.data.DataSet;
import javax.visrec.util.VisRecConstants;
import jsr381.example.util.DataSetExamples;
import visrec.ri.ml.classification.ImageClassifierNetwork;

/**
 * Hand written digit recognition using MNIST data set - image classification hello world.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemo {

    public static void main(String[] args) throws IOException {

        // specify image classifier configuation as set of properties in a 
        Map<String, Object> conf = new HashMap<>();

        // provide data set properties: image dimesions, categories/labels and list of image files
        conf.put(VisRecConstants.IMAGE_WIDTH, "28" );  // width of example images
        conf.put(VisRecConstants.IMAGE_HEIGHT, "28" ); // height of example images
        conf.put(VisRecConstants.LABELS_FILE, "D:\\datasets\\mnist\\train\\labels.txt"); // put java.io.tmp nad zip into data set

        // specify training file which contains a list of images to learn
        conf.put(VisRecConstants.TRAINING_FILE, "D:\\datasets\\mnist\\train\\train.txt");

        // specify network architecture in json file
        conf.put("visrec.model.deepnetts", "mnist1.json"); // put path to json file or JSON object define network architecture in json file
        conf.put(VisRecConstants.MODEL_SAVE_TO, "mnist.dnet");  // save trained model to file at the end

        // learning algorithm settings
        conf.put(VisRecConstants.SGD_MAX_ERROR, "1.4" );
        conf.put(VisRecConstants.SGD_MAX_EPOCHS , "100" );
        conf.put(VisRecConstants.SGD_LEARNING_RATE, "0.01" );
        

        // building image classifier with specified configuration
        AbstractImageClassifier imageClassifier = ImageClassifierNetwork.builder().build(conf);

        // Evalate image classifier
        
        // Using image classifier
        Map<String, Float> results = imageClassifier.classify(new File("00060.png"));
        System.out.println(results);
    }
}
