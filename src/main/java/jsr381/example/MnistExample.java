package jsr381.example;

import jsr381.example.util.DataSetExamples;

import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ml.classification.NeuralNetImageClassifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Handwritten digit recognition using MNIST data set - image classification hello world.
 * 
 * This example is deprectaed
 */
@Deprecated
public class MnistExample {

    public static void main(String[] args) throws IOException, ClassificationException, ClassifierCreationException {
        // Download the dataset and calculate how much time it took
        long start = System.currentTimeMillis();
        DataSetExamples.ExampleDataSet dataSet = DataSetExamples.getMnistDataSet();
        System.out.println(String.format("Took %d milliseconds to download and/or unzip the MNIST dataset", System.currentTimeMillis() - start));

        // Configuration to train the model
        Map<String, Object> conf = new HashMap<>();

        // provide data set properties: image dimesions, categories/labels and list of image files
        conf.put("imageWidth", "28");  // width of example images
        conf.put("imageHeight", "28"); // height of example images
        conf.put("labelsFile", dataSet.getLabelsFile().getAbsolutePath());

        // specify training file which contains a list of images to learn
        conf.put("trainingFile", dataSet.getTrainingFile().getAbsolutePath());

        // specify network architecture in json file
        conf.put("networkArchitecture", "pathToArch");

        // learning algorithm settings
        conf.put("maxError", "1.4");
        conf.put("maxEpochs", "100");
        conf.put("learningRate", "0.01");

        // building image classifier with specified configuration
        ImageClassifier<BufferedImage> imageClassifier = NeuralNetImageClassifier.builder()
                .inputClass(BufferedImage.class)
                .build(conf);

        // Using image classifier
        // Get the image file from resources
        URL input = MnistWithBuildingBlocksExample.class.getClassLoader().getResource("00060.png");
        if (input == null) {
            throw new IOException("Input file not found in resources");
        }
        Map<String, Float> results = imageClassifier.classify(new File(input.getFile()));

        // Print the outcome
        System.out.println(results);
    }
}
