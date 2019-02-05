package jsr381.example;

public class IrisClassificationExample {
    public static void main(String[] args) {

        // load iris data set

        MultiClassClassifier classifier = DeepNettsMultiClassClassifier.builder()
                                                        .inputsNum(4)
                                                        .hiddenLayerSizes(16)
                                                        //.hiddenActivation()
                                                        .outputsNum(3)
//                                                        .maxEpochs(1000)
//                                                        .maxError(0.01f)
//                                                        .learningRate(0.01)
                                                        .build();

        classifier.train(trainingSet);
        classifier.test(testSet);   // do this internally after training, log results, and return Map with performance measures?

    }
}
