package jsr381.example;

import javax.imageio.ImageIO;
import javax.visrec.ml.classification.ImageClassifier;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @author Zoran Sevarac
 */
public class Cifar10 {

    public static void main(String[] args) throws IOException {

        ImageClassifier imageClassifier = ImageClassifier.newBuilder()
                .imageWidth(32)
                .imageHeight(32)
                .labelsFile(new File("/home/zoran/datasets/cifar10/train/labels.txt"))
                .trainingsFile(new File("/home/zoran/datasets/cifar10/train/train.txt"))
//                .testFile(new File("/home/zoran/datasets/cifar10/test/test.txt"))
                .maxError((float)0.03)
                .learningRate((float)0.01)
                .modelFile(new File("cifar10.dnet"))
                .build();

        System.out.println("Done building image classifier.");
        System.out.println("Classifiying image ...");

        BufferedImage image = ImageIO.read(new File("/home/zoran/datasets/Cifar10/train/airplane/someTestImage.png"));
        Map<String, Float> results = imageClassifier.classify(image);
        System.out.println(results);

        System.out.println("Done.");
    }
}
