package main;
/**
 * Created by Dave on 5/11/17.
 */
public class Tester {
    public static void main(String[] args) {
        double[][] trainingData = DataSetup.loadData("iris-training.csv");
        DataSetup.printData(trainingData);


        System.out.println("___________________________________________________");
        double[][] testingData = DataSetup.loadData("iris-testing.csv");
        DataSetup.printData(testingData);


        System.out.println("======================================================");
        Perceptron perceptron = new Perceptron(0.2, 100, trainingData, testingData, 0.25);
        // 0.2, 100, 0.18
        // 0.3, 100, 0.25
        // 0.83% -> 0.2, 100, 0.25
        perceptron.train();
        perceptron.predict();
    }
}
