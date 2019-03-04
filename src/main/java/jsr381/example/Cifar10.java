package jsr381.example;

import visrec.ri.ml.classification.DeepNettsImageClassifier;

import javax.visrec.ml.classification.Classifier;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class Cifar10 {

    public static void main(String[] args) throws IOException {

        // BUILD IMAGE CLASSIFIER USING VIS REC API
        Map<String, Object> configuration = new HashMap<>();
        // provide data set properties
        configuration.put("visrec.imageWidth", "32");
        configuration.put("visrec.imageHeight", "32");
        configuration.put("visrec.labelsFile", "/home/zoran/datasets/cifar10/train/labels.txt");   // list of labels in txt file
        configuration.put("visrec.trainingFile", "/home/zoran/datasets/cifar10/train/train.txt");  // list of training images in txt file
        configuration.put("visrec.testFile", "/home/zoran/datasets/cifar10/test/test.txt");        // list of test images in txt file

        // provide model properties -  how to specify network architecture ??? could the model be injected?
        //prop.setProperty("networkArch", "architecture.json"); // how to do it programaticly? for different models?
        // training settings
        configuration.put("visrec.sgd.maxError", "0.03");
        configuration.put("visrec.sgd.learningRate", "0.01");
        configuration.put("modelFile", "cifar10.dnet");  // save trained model in file at the end (model has to provide this feature)

        Classifier imageClassifier = DeepNettsImageClassifier.builder().build(configuration); // maybe also attach some listener  (or callback function) to be notified when model building is complete?
        System.out.println("Done building image classifier.");

        // TEST VISREC IMAGE CLASSIFIER
        // TODO ...
        // USE VISREC IMAGE CLASSIFIER
        System.out.println("Classifiying images ...");
        Map<String, Float> results = imageClassifier.classify(new File("/home/zoran/datasets/Cifar10/train/airplane/someTestImage.png"));
        System.out.println(results);

        System.out.println("Done.");
    }
}
