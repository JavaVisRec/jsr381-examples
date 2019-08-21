package jsr381.example;

import deepnetts.data.DataSet;
import deepnetts.data.DataSets;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.visrec.AbstractImageClassifier;
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

      DataSet dataSet = null; // DataSetExamples.getMnistTrainingDataSet();
        DataSet[] trainTestSet = DataSets.trainTestSplit(dataSet, 0.6);

        // specify image classifier configuation as set of properties in map
        Map<String, Object> configuration = new HashMap<>();

        // provide data set properties: image dimesions, categories/labels and list of image files
        configuration.put(VisRecConstants.IMAGE_WIDTH, "28" );  // width of example images
        configuration.put(VisRecConstants.IMAGE_HEIGHT, "28" ); // height of example images
        configuration.put(VisRecConstants.LABELS_FILE, "D:\\datasets\\mnist\\train\\labels.txt"); // put java.io.tmp nad zip into data set

        // specify training file which contains a list of images to learn
        configuration.put(VisRecConstants.TRAINING_FILE, "D:\\datasets\\mnist\\train\\train.txt");

        // set network architecture from json file
        configuration.put("visrec.model.deepnetts", "mnist1.json"); // put path to json file or JSON object
        configuration.put(VisRecConstants.MODEL_SAVE_TO, "mnist.dnet");  // save trained model to file at the end

        // learning algorithm settings
        configuration.put(VisRecConstants.SGD_MAX_ERROR, "1.4" );
        configuration.put(VisRecConstants.SGD_MAX_EPOCHS , "100" );
        configuration.put(VisRecConstants.SGD_LEARNING_RATE, "0.01" );

        // building image classifier with specified configuration
        AbstractImageClassifier imageClassifier = ImageClassifierNetwork.builder().build(configuration);

        // Using image classifier
        Map<String, Float> results = imageClassifier.classify(new File("00060.png"));
        System.out.println(results);
    }
}
