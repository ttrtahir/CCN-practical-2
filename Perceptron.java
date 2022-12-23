public class Perceptron {
    // The weights for the input features and bias term
    private double[] weights;

    // The learning rate for the perceptron
    private double learningRate;

    // The threshold for the perceptron
    private double threshold;

    public Perceptron(int numFeatures, double learningRate, double threshold) {
        // Adding the bias node for
        this.weights = new double[numFeatures + 1];
        for (int i = 0; i < this.weights.length; i++) {
            // Initialize the weights to random values between -1 and 1
            this.weights[i] = Math.random() * 2 - 1;
        }

        this.learningRate = learningRate;
        this.threshold = threshold;
    }

    public void train(double[][] trainingData, int[] labels) {
        int sumError = 1;
        int trainGeneration = 0;
        for (; sumError != 0; trainGeneration++) {
            sumError = 0;
            // Loop through the training data and update the weights
            for (int i = 0; i < trainingData.length; i++) {
                // Make a prediction based on the current weights
                int prediction = predict(trainingData[i]);

                // Calculate the error
                int error = labels[i] - prediction;
                sumError += error;

                // Update the weights based on the error
                for (int j = 0; j < weights.length; j++) {
                    weights[j] += error * trainingData[i][j] * learningRate;
                    // System.out.println(j + ". weight is: " + weights[j]);
                }
            }

            System.out.println("After the " + (trainGeneration + 1) + ". application of training on the weights:");
            for (int j = 0; j < weights.length - 1; j++)
                System.out.println((j + 1) + ". weight is: " + weights[j]);

            System.out.println("Bias weight is: " + weights[weights.length - 1]);
        }

        System.out.println("Number of the training application is needed: " + trainGeneration);
    }

    public int predict(double[] features) {
        // Calculate the weighted sum of the input features and bias term
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i] * features[i];
        }

        // Return 1 if the sum is greater than the threshold, or 0 otherwise
        return sum > threshold ? 1 : 0;
    }

    public static void initAnd() {
        // Create a perceptron with 2 input features, a learning rate of 0.1, and a
        // threshold of 0 with bias
        Perceptron perceptron = new Perceptron(2, 0.1, 0);

        // Define the training data and labels for the AND function
        double[][] trainingData = { { 0, 0, 1 }, { 0, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[] labels = { 0, 0, 0, 1 };

        // Train the perceptron on the training data
        perceptron.train(trainingData, labels);

        // Test the perceptron on some new data
        double[] testData1 = { 0, 0, 1 };
        int prediction1 = perceptron.predict(testData1);
        System.out.println("Prediction for test data 1: " + prediction1);

        double[] testData2 = { 0, 1, 1 };
        int prediction2 = perceptron.predict(testData2);
        System.out.println("Prediction for test data 2: " + prediction2);

        double[] testData3 = { 1, 0, 1 };
        int prediction3 = perceptron.predict(testData3);
        System.out.println("Prediction for test data 3: " + prediction3);

        double[] testData4 = { 1, 1, 1 };
        int prediction4 = perceptron.predict(testData4);
        System.out.println("Prediction for test data 4: " + prediction4);
    }

    public static void initAnother() {
        Perceptron perceptron2 = new Perceptron(2, 0.1, 0);

        double[][] trainingData2 = { { 0.1, 1.3, 1 }, { 0.2, 1.3, 1 }, { 0.3, 1.7, 1 }, { 0.5, 1.9, 1 } };
        int[] labels2 = { 1, 0, 1, 0 };

        perceptron2.train(trainingData2, labels2);

        double[] testData = { 1, 0.5, 1 };
        int prediction = perceptron2.predict(testData);
        System.out.println(prediction);

        double[] testData2 = { 1, 1, 1 };
        int prediction2 = perceptron2.predict(testData2);
        System.out.println(prediction2);

        double[] testData3 = { 1, 3, 1 };
        int prediction3 = perceptron2.predict(testData3);
        System.out.println(prediction3);

        double[] testData4 = { 1, 5, 1 };
        int prediction4 = perceptron2.predict(testData4);
        System.out.println(prediction4);
    }

    public static void main(String[] args) {

        // Logical function: AND
        initAnd();
        // y = 2x + 1
        initAnother();
    }
}
