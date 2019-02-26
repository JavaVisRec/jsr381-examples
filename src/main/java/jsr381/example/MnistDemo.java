package jsr381.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import javax.visrec.AbstractImageClassifier;
import javax.visrec.util.VisRecConstants;
import visrec.ri.ml.classification.DeepNettsImageClassifier;

/**
 *
 * @author Zoran Sevarac <zoran.sevarac@deepnetts.com>
 */
public class MnistDemo {

    public static void main(String[] args) throws IOException {

        Properties props = new Properties();
        // provide data set properties
        props.setProperty(VisRecConstants.IMAGE_WIDTH, "28" );  // width of example images
        props.setProperty(VisRecConstants.IMAGE_HEIGHT, "28" ); // height of example images
      //  props.setProperty(VisRecConstants.LABELS_FILE, MnistDemo.class.getResource("../../../../labels.txt").getFile());     // path to filer
        props.setProperty(VisRecConstants.LABELS_FILE, "D:\\datasets\\mnist\\train\\labels.txt");     // path to file
        // with labels
        // (maybe
        // this could be also specifid as visrec.labels="label1,label2,label3")
       // props.setProperty(VisRecConstants.TRAINING_FILE, MnistDemo.class.getResource("../../../../train3.txt").getFile());     // file with list of training images (contains image paths and corresponding labels)
        props.setProperty(VisRecConstants.TRAINING_FILE, "D:\\datasets\\mnist\\train\\train.txt");     // file with list of training images (contains image paths and corresponding labels)

        // prop.setProperty("visrec.model", "networkArchitecture.json");    // provide as json object or json file
                       // DeepNetts.VISREC_MODEL
        // TODO: specify architecture in json which library to use?  or
        // consider https://www.khronos.org/nnef    and     https://onnx.ai/
        // https://www.khronos.org/registry/NNEF/specs/1.0/nnef-1.0.pdf
       // props.setProperty("visrec.model.deepnetts", MnistDemo.class.getResource("../../../../mnist1.json").getFile()); // Constant should be defined in DeepNetts.VISREC_MODEL
        props.setProperty("visrec.model.deepnetts", "mnist1.json"); // Constant should be defined in DeepNetts.VISREC_MODEL
        // or set individual properties but that would be too heavy from here
       // props.setProperty("visrec.model.saveToFile", MnistDemo.class.getResource("../../../../mnist.dnet").getFile());  // save trained model to file at the end

        // training settings visrec.deepnetts.optimizationType=adagrad etc.
        props.setProperty(VisRecConstants.SGD_MAX_ERROR, "0.02" );
        props.setProperty(VisRecConstants.SGD_LEARNING_RATE, "0.03" );

        AbstractImageClassifier imageClassifier = DeepNettsImageClassifier.builder().build(props);
        System.out.println("Done building image classifier.");

        System.out.println("Testing image classifier...");
        Map<String, Float> results = imageClassifier.classify(new File(MnistDemo.class.getResource("../../../../00005.png").getFile()));
        System.out.println(results);
    }
}
