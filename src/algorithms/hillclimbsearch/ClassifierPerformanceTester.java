package algorithms.hillclimbsearch;

import java.util.List;
import java.util.Random;

public abstract class ClassifierPerformanceTester<STATE extends ClassifierState, CLASSES, EVALRET extends Comparable<EVALRET>, INPUT extends InputElement<CLASSES>> {

	public EVALRET test(STATE classifier, List<INPUT> trainingSet,
			EvaluationFunction<STATE, EVALRET, CLASSES, INPUT> evaluationFunction, double testingSetPercentage) {

		long testingSetCount = Math.round(testingSetPercentage * (double) trainingSet.size());
		System.out.println("testing set count: " + testingSetCount);
		Random random = new Random(trainingSet.size());

		List<INPUT> availableSet = listProducerCopy(trainingSet);
		List<INPUT> testingSet = listProducer();
		do {
			int index = random.nextInt(availableSet.size());
			// System.out.println("index: " + index);
			testingSet.add(availableSet.remove(index));
		} while (testingSet.size() < testingSetCount);

		return evaluationFunction.evaluate(classifier, testingSet);
	}

	protected abstract List<INPUT> listProducerCopy(List<INPUT> original);

	protected abstract List<INPUT> listProducer();
}
