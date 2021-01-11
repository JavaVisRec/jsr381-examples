package jsr381.example;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.visrec.ml.ClassifierCreationException;
import javax.visrec.ml.classification.ImageClassifier;
import javax.visrec.ml.classification.NeuralNetImageClassifier;

import jsr381.example.util.DataSetExamples;

/**
 * @author Nishant Raut
 *
 */
public class CatDogRecognition {
	public static void main(String[] args) throws ClassifierCreationException, IOException {
		// Download the dataset and calculate how much time it took
		long start = System.currentTimeMillis();
		DataSetExamples.ExampleDataSet dataSet = DataSetExamples.getCatDogDataSet();
		System.out.println(String.format("Took %d milliseconds to download and/or unzip the CatDog dataset",
				System.currentTimeMillis() - start));

		// Configuration to train the model
		ImageClassifier<BufferedImage> classifier = NeuralNetImageClassifier.builder().inputClass(BufferedImage.class)
				.imageHeight(128).imageWidth(128).labelsFile(dataSet.getLabelsFile())
				.trainingFile(dataSet.getTrainingFile())
				//.trainingFile(new File("catdog_arch.json"))
				.exportModel(Paths.get("catdog.dnet")).maxError(0.03f).maxEpochs(1000).learningRate(0.01f).build();

		// Get input image from resources and use the classifier.
		URL input = CatDogRecognition.class.getClassLoader().getResource("cat_36.png");
		if (input == null) {
			throw new IOException("Input file not found in resources");
		}

		BufferedImage image = ImageIO.read(new File(input.getFile()));
		Map<String, Float> results = classifier.classify(image);

		// Print the outcome
		System.out.println(results);

	}
}
