package jsr381.example;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.util.VisRecConstants;
import jsr381.example.util.DataSetExamples;
import visrec.ri.ml.classification.ImageClassifierNetwork;

/**
 * Handwritten digit recognition using MNIST data set - image classification hello world.
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistExample {

    public static void main(String[] args) throws IOException, URISyntaxException {
        // Download the dataset and calculate how much time it took
        long start = System.currentTimeMillis();
        DataSetExamples.MnistDataSet dataSet = DataSetExamples.getMnistDataSet();
        System.out.println(String.format("Took %d milliseconds to download the MNIST dataset", System.currentTimeMillis() - start));

        // Configuration to train the model
        Map<String, Object> conf = new HashMap<>();

        // provide data set properties: image dimesions, categories/labels and list of image files
        conf.put(VisRecConstants.IMAGE_WIDTH, "28");  // width of example images
        conf.put(VisRecConstants.IMAGE_HEIGHT, "28" ); // height of example images
        conf.put(VisRecConstants.LABELS_FILE, dataSet.getLabelsFile().getAbsolutePath());

        // specify training file which contains a list of images to learn
        conf.put(VisRecConstants.TRAINING_FILE, dataSet.getTrainingFile().getAbsolutePath());

        // specify network architecture in json file
        conf.put("visrec.model.deepnetts",  Paths.get(MnistExample.class.getClassLoader().getResource("mnist_arch.json").toURI()).toFile());
        // save trained model to file at the end
        conf.put(VisRecConstants.MODEL_SAVE_TO, "mnist.dnet");

        // learning algorithm settings
        conf.put(VisRecConstants.SGD_MAX_ERROR, "0.03" );
        conf.put(VisRecConstants.SGD_MAX_EPOCHS , "100" );
        conf.put(VisRecConstants.SGD_LEARNING_RATE, "0.01" );
        
        // building image classifier with specified configuration
        AbstractImageClassifier imageClassifier = ImageClassifierNetwork.builder().build(conf);
       
        // Using image classifier
        Map<String, Float> results = imageClassifier.classify(new File("00060.png"));
        // print out probabilities for all possible image classes
        System.out.println(results);
    }
}
