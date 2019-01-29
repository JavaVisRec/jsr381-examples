package jsr381.example;

import deepnetts.data.DataSet;
import deepnetts.net.FeedForwardNetwork;
import deepnetts.net.layers.activation.ActivationType;
import deepnetts.net.loss.LossType;
import deepnetts.net.train.BackpropagationTrainer;
import deepnetts.util.Tensor;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author zoran
 */
public class DeepNettsLogisticRegression extends LogisticRegression<FeedForwardNetwork>{

    @Override
    public Map<Boolean, Float> classify(float[] someInput) {
      FeedForwardNetwork ffn = getModel();
      ffn.setInput(Tensor.create(1, someInput.length, someInput)); //TODO: put array to input tensor placeholder
      float[] output = ffn.getOutput();
      Map<Boolean, Float> result = new HashMap<>();
      result.put(Boolean.TRUE, output[0]);
      result.put(Boolean.FALSE, 1-output[0]);
      return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    // TODO: add static builder class and method

   public static class Builder implements javax.visrec.util.Builder<DeepNettsLogisticRegression> {
        private DeepNettsLogisticRegression product = new DeepNettsLogisticRegression();

        private float learningRate = 0.01f;
        private float maxError = 0.03f;
        private int maxEpochs = 1000;
        private int inputsNum;

        private DataSet<?> trainingSet; // replace with DataSet from visrec

        public Builder inputs(int inputsNum) {
            this.inputsNum = inputsNum;
            return this;
        }

        public Builder learningRate(float learningRate) {
            this.learningRate = learningRate;
            return this;
        }

        public Builder maxError(float maxError) {
            this.maxError = maxError;
            return this;
        }

        public Builder maxEpochs(int maxEpochs) {
            this.maxEpochs = maxEpochs;
            return this;
        }

        public Builder trainingSet(DataSet<?> trainingSet) {
            this.trainingSet = trainingSet;
            return this;
        }

        // test set
        // target accuracy

        public DeepNettsLogisticRegression build() {
            FeedForwardNetwork model= FeedForwardNetwork.builder()
                                        .addInputLayer(inputsNum)
                                        .addOutputLayer(1, ActivationType.SIGMOID)
                                        .lossFunction(LossType.MEAN_SQUARED_ERROR)
                                        .build();

            BackpropagationTrainer trainer = new BackpropagationTrainer();
            trainer.setLearningRate(learningRate)
                    .setMaxError(maxError);
            trainer.train(model, trainingSet);


            product.setModel(model);
            return product;
        }

        @Override
        public DeepNettsLogisticRegression build(Properties prop) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


    }
}
