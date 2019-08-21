package jsr381.example;

import jsr381.example.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.util.VisRecConstants;
import visrec.ri.ml.classification.DeepNettsImageClassifier;

/**
 * Hand written digit recognition using MNIST data set - image classification hello world.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemoBak {

    public static void main(String[] args) throws IOException {

        Map<String, Object> configuration = new HashMap<>();
        // provide data set properties: image dimesions, categories/labels and list of image files
        configuration.put(VisRecConstants.IMAGE_WIDTH, "28" );  // width of example images
        configuration.put(VisRecConstants.IMAGE_HEIGHT, "28" ); // height of example images
        configuration.put(VisRecConstants.LABELS_FILE, "D:\\datasets\\mnist\\train\\labels.txt");     // path to file
        // configuration.put(VisRecConstants.LABELS_FILE, MnistDemo.class.getResource("../../../../labels.txt").getFile());     // path to filer
        // specify training file which contains a list of images to learn
        configuration.put(VisRecConstants.TRAINING_FILE, "D:\\datasets\\mnist\\train\\train.txt");     // file with list of training images (contains image paths and corresponding labels)
        // configuration.put(VisRecConstants.TRAINING_FILE, MnistDemo.class.getResource("../../../../train3.txt").getFile());     // file with list of training images (contains image paths and corresponding labels)

        // set network architecture from json file
        configuration.put("visrec.model.deepnetts", "mnist1.json"); // Constant should be defined in DeepNettsConstants.VISREC_MODEL
        // configuration.put("visrec.model.deepnetts", MnistDemo.class.getResource("../../../../mnist1.json").getFile()); // Constant should be defined in DeepNetts.VISREC_MODEL
        configuration.put(VisRecConstants.MODEL_SAVE_TO, "mnist.dnet");  // save trained model to file at the end

        // learning algorithm settings
        configuration.put(VisRecConstants.SGD_MAX_ERROR, "1.4" );
        configuration.put(VisRecConstants.SGD_MAX_EPOCHS , "100" );
        configuration.put(VisRecConstants.SGD_LEARNING_RATE, "0.01" );

        // building image classifier with specified configuration
        AbstractImageClassifier imageClassifier = DeepNettsImageClassifier.builder().build(configuration);

        // Using image classifier
        Map<String, Float> results = imageClassifier.classify(new File("00846.png"));
        //Map<String, Float> results = imageClassifier.classify(new File(MnistDemo.class.getResource("../../../../00005.png").getFile()));
        System.out.println(results);
    }
}
