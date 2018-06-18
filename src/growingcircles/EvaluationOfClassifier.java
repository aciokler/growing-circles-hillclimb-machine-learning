package growingcircles;

import java.util.List;

import algorithms.hillclimbsearch.EvaluationFunction;

public class EvaluationOfClassifier implements EvaluationFunction<CircleClassifier, Double, Boolean, Position> {

	@Override
	public Double evaluate(CircleClassifier currentState, CircleClassifier finalState, List<Position> trainingSet) {
		int successfullyClassifiedCount = 0;
		for (Position pos : trainingSet) {
			double distanceFromCenterOfCircle = Math
					.sqrt(Math.pow(currentState.getCenter().getX() - pos.getCoordinate().getX(), 2)
							+ Math.pow(currentState.getCenter().getY() - pos.getCoordinate().getY(), 2));

			if (pos.getInputClass()) {
				if (distanceFromCenterOfCircle <= currentState.getRadius()) {
					successfullyClassifiedCount++;
				}
			} else if (distanceFromCenterOfCircle > currentState.getRadius()) {
				successfullyClassifiedCount++;
			}
		}
		double errorRate = 1.00 - ((double) successfullyClassifiedCount / (double) trainingSet.size());
		currentState.setErrorRate(errorRate);
		return errorRate;
	}

}
