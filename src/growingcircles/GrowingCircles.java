package growingcircles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algorithms.hillclimbsearch.ClassifierPerformanceTester;
import algorithms.hillclimbsearch.HillClimbClassifierSearch;

public class GrowingCircles {

	public static void main(String[] args) {

		CircleClassifier initialClassifier = new CircleClassifier(new Coordinate(0.0, 0.0), 0.01);
		CircleClassifier finalClassifier = new CircleClassifier(new Coordinate(2.0, 2.0), 1.0, 0.05);

		List<Position> trainingSet = generateTrainingSet(finalClassifier, 20000, 0.05);

		HillClimbClassifierSearch<CircleClassifier, CircleChange, Double, Boolean, Position, EvaluationOfClassifier> classifierSearch = new HillClimbClassifierSearch<>(
				new EvaluationOfClassifier(), CircleChange.getPossibleOperators(), new CircleChangeApplier());

		CircleClassifier foundClassifier = classifierSearch.findClassifier(initialClassifier, finalClassifier,
				trainingSet, 100);

		System.out.println("found classifier? " + (foundClassifier != null));
		if (foundClassifier != null) {
			System.out.println(foundClassifier);
		}

		ClassifierPerformanceTester<CircleClassifier, Boolean, Double, Position> tester = new ClassifierPerformanceTester<CircleClassifier, Boolean, Double, Position>() {
			@Override
			protected List<Position> listProducerCopy(List<Position> original) {
				return new ArrayList<>(original);
			}

			@Override
			protected List<Position> listProducer() {
				return new ArrayList<>();
			}
		};

		Double errorRate = tester.test(foundClassifier, trainingSet, new EvaluationOfClassifier(), 0.1);

		System.out.println("test error rate: " + errorRate);
	}

	public static List<Position> generateTrainingSet(CircleClassifier finalClassifier,
			final int numberOfTrainingExamples, double negativeRatio) {
		List<Position> trainingSet = new ArrayList<>(numberOfTrainingExamples);

		long negativeRatioCount = Math.round((double) numberOfTrainingExamples * negativeRatio);
		long positiveRatioCount = numberOfTrainingExamples - negativeRatioCount;

		int justCheckingPositiveCount = 0;

		Random random = new Random(numberOfTrainingExamples);
		for (int i = 0; i < positiveRatioCount; i++) {

			double x = (random.nextDouble() * finalClassifier.getRadius()) * Math.cos(i)
					+ finalClassifier.getCenter().getX();
			double y = (random.nextDouble() * finalClassifier.getRadius()) * Math.sin(i)
					+ finalClassifier.getCenter().getY();

			double distanceFromCenterOfCircle = Math.sqrt(Math.pow(finalClassifier.getCenter().getX() - x, 2)
					+ Math.pow(finalClassifier.getCenter().getY() - y, 2));

			boolean isPositive = distanceFromCenterOfCircle < finalClassifier.getRadius();
			trainingSet.add(new Position(new Coordinate(x, y), isPositive));

			if (isPositive) {
				justCheckingPositiveCount++;
			}
		}

		for (int i = 0; i < negativeRatioCount; i++) {

			double x = (finalClassifier.getRadius() + 2.0) * Math.cos(i) + finalClassifier.getCenter().getX();
			double y = (finalClassifier.getRadius() + 2.0) * Math.sin(i) + finalClassifier.getCenter().getY();

			double distanceFromCenterOfCircle = Math.sqrt(Math.pow(finalClassifier.getCenter().getX() - x, 2)
					+ Math.pow(finalClassifier.getCenter().getY() - y, 2));

			boolean isInside = distanceFromCenterOfCircle <= finalClassifier.getRadius();
			trainingSet.add(new Position(new Coordinate(x, y), isInside));
		}

		double errorRate = 1.00 - (double) justCheckingPositiveCount / (double) numberOfTrainingExamples;
		finalClassifier.setErrorRate(errorRate);
		return trainingSet;
	}

}
