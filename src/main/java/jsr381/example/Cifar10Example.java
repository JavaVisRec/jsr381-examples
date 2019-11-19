package jsr381.example;

import visrec.ri.ml.classification.ImageClassifierNetwork;

import javax.imageio.ImageIO;
import javax.visrec.ml.ClassificationException;
import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.ImageClassifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cifar10 is a commonly used data set with 60000 of small 32x32 pixel images, used for image classification benchmarks.
 * 
 * @author Zoran Sevarac
 */
public class Cifar10Example {

    public static void main(String[] args) throws IOException, ClassificationException, ClassifierCreationException {

//        ImageClassifier imageClassifier = ImageClassifier.newBuilder()
//                .imageWidth(32)
//                .imageHeight(32)
//                .labelsFile(new File("/home/zoran/datasets/cifar10/train/labels.txt"))
//                .trainingsFile(new File("/home/zoran/datasets/cifar10/train/train.txt"))
////                .testFile(new File("/home/zoran/datasets/cifar10/test/test.txt"))
//                .maxError((float)0.03)
//                .learningRate((float)0.01)
//                .modelFile(new File("cifar10.dnet"))
//                .build();

        // provide model properties -  how to specify network architecture ??? could the model be injected?
        //prop.setProperty("networkArch", "architecture.json"); // how to do it programaticly? for different models?
        // training settings
        Map<String, Object> conf = new HashMap<>();
        conf.put("visrec.sgd.maxError", "0.03");
        conf.put("visrec.sgd.learningRate", "0.01");
        conf.put("modelFile", "cifar10.dnet");  // save trained model in file at the end (model has to provide this feature)

        ImageClassifier imageClassifier = ImageClassifierNetwork.builder().build(conf); // maybe also attach some listener  (or callback function) to be notified when model building is complete?
        System.out.println("Done building image classifier.");
        System.out.println("Classifiying image ...");

        BufferedImage image = ImageIO.read(new File("/home/zoran/datasets/Cifar10/train/airplane/someTestImage.png"));
        Map<String, Float> results = imageClassifier.classify(image);
        System.out.println(results);

        System.out.println("Done.");
    }
}
