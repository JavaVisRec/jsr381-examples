package jsr381.example;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javax.visrec.ml.classification.BinaryClassifier;
import javax.visrec.ml.classification.Classifiable;
import javax.visrec.ml.classification.NeuralNetBinaryClassifier;
import javax.visrec.ml.model.ModelCreationException;

/**
 * @author Nishant
 *
 */
public class CreditCardFraudDetection {
	public static void main(String[] args) throws IOException, ModelCreationException {

		// Build binary classifer based on neural network
		BinaryClassifier<float[]> fraudClassifier = NeuralNetBinaryClassifier.builder().inputClass(float[].class)
				.inputsNum(29).hiddenLayers(29, 15).maxError(0.03f).maxEpochs(15000).learningRate(0.01f)
				.trainingPath(Paths.get("datasets/creditcard.csv")).build();
		Float result = fraudClassifier.classify(new CreditCardFraud().getClassifierInput());
		System.out.println(result);
	}

	static class CreditCardFraud implements Classifiable<float[], Boolean> {

		private float[] creditCardFraudFeatures;
		private Boolean isFraud;

		public CreditCardFraud() {
			creditCardFraudFeatures = new float[29];
			creditCardFraudFeatures[28] = 0;
		}

		@Override
		public float[] getClassifierInput() {
			return creditCardFraudFeatures;
		}

		@Override
		public Boolean getTargetClass() {
			return isFraud;
		}
	}
}
