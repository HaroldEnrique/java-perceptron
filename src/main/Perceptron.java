package main;
/**
 * Created by Dave on 5/11/17.
 */
public class Perceptron {

    double[] weights = new double[] {0.2,0.3,0.1,0.25}; // weights are initialized to 0 [0,0,0,0]
    //weights = [0.2,0.3,0.1,0.25];
    double bias;
    double[][] trainData;
    double[][] testData;
    double learningRate;
    int iterations;
    
    Perceptron(double learningRate, int iterations, double[][] trainData, double[][] testData, double bias) {
        this.learningRate = learningRate;
        this.iterations = iterations;
        this.trainData = trainData;
        this.testData = testData;
        this.bias = bias;
    }

    /* Your Perceptron should be an object with methods to train, predict, etc */

    public void train() {
        System.out.println("Training Data.....");
        for (int i = 0; i < iterations; i++) {
            int numberOfMisClassifications = 0;
            System.out.println("Starting iteration " + (i + 1));
            
            for (double[] row: trainData) {
            		
//                System.out.println("valores iniciales ");
//                System.out.println("pesos > " + weights[0] + " | " + weights[1] + " | " + weights[2] + " | " + weights[3]);
//                System.out.println("bias > " + bias);
            	
            	System.out.println(row[0]);
                double label = row[row.length - 1];
                System.out.println(row[row.length - 1]);
                double predictedLabel = dotProduct(row);
                double delta = label - predictedLabel;
                System.out.println("Error (D - Y) > " + delta);
                increaseWeightVector(row,delta);
                System.out.println("bias old > " + bias);
                updateBias(delta);
                System.out.println("bias new > " + bias);
				if (predictedLabel > label) {
					numberOfMisClassifications++;
					// adjust the weight vector
					// since the prediction was too big we make it smaller

					//decreaseWeightVector(row, delta);
				} else if (predictedLabel < label) {
					numberOfMisClassifications++;
					// predicted value was too low so we increase it

					//increaseWeightVector(row, delta);
				}
                
//                System.out.println("valores finales ");
//                System.out.println("pesos > " + weights[0] + " | " + weights[1] + " | " + weights[2] + " | " + weights[3]);
//                System.out.println("bias > " + bias);
//                System.out.println("learningRate > " + learningRate);
                
                
            }
            System.out.println("Number of Misclassifications: " + numberOfMisClassifications);
            
            
            
        }
    }

    public void predict() {
        /* Classify the testing data. Remember our algorithm hasn't
         * seen this data before. Hopefully, it "learned" the difference
         * between the two flowers */
        String one = "Iris-setosa";
        String zero = "Iris-versicolor";
        int numberOfCorrectPredictions = 0;

        for (double[] row : testData) {
            double label = row[row.length - 1];
            double prediction = dotProduct(row);
            boolean correct = prediction == label;

            if (correct && prediction == 1) {
                numberOfCorrectPredictions++;
                System.out.println("Predicted: " + one + "  | Truth: " + one);
            }
            else if (correct && prediction == -1) {
                numberOfCorrectPredictions++;
                System.out.println("Predicted: " + zero + "  | Truth: " + zero);
            }
            else if (!correct && prediction == 1) {
                System.out.println("Predicted: " + one + "  | Truth: " + zero);
            }
            else {
                System.out.println("Predicted: " + zero + "  | Truth: " + one);
            }
        }
        calculateAccuracy(numberOfCorrectPredictions);

    }

    public void calculateAccuracy(int correct) {
        double percentCorrect = ((double) correct / testData.length) * 100;
        System.out.println("Accuracy " + percentCorrect + "%");

    }

    private void decreaseWeightVector(double[] dataPoint, double delta) {
        for (int i = 0; i < dataPoint.length - 1; i++) {
            //System.out.println("############# " + dataPoint[i]);
        	//[4.6, 3.2, 1.4, 0.2]
        	//actualizacion de pesos
            weights[i] -= dataPoint[i] * learningRate * delta;
            //weights[i] = weights[i] - ( dataPoint[i] * learningRate);
        }
    }
    //[2,3,5,1]
    private void increaseWeightVector(double[] dataPoint, double delta) {
        for (int i = 0; i < dataPoint.length - 1; i++) {
            //System.out.println("************ " + dataPoint[i]);
            weights[i] = weights[i] + ( dataPoint[i] * learningRate * delta);
            //peso_nuevo = 0.2 + (2*0.01*delta)
        }
    }
    
    private void updateBias(double error) {
    	bias = bias - (learningRate * error);
    }

    private double dotProduct(double[] dataPoint) {
        // you might want to write this method
    	
    	//[4.6, 3.2, 1.4, 0.2]
    	//[0, 0, 0, 0]
    	
    	
    	
        double result = 0;
        for (int i = 0; i < dataPoint.length - 1; i++) {
            result += dataPoint[i] * weights[i];
        }
        result = result - bias;
        
        System.out.println("Resultado da sumatoria > " +result);
        
        if (result > 0.0) {
            return 1.0;
        }
        else {
            return -1.0;
        }
        
    }


}
