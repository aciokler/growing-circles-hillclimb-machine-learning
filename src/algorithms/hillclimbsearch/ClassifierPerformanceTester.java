package algorithms.hillclimbsearch;

import java.util.List;
import java.util.Random;

public abstract class ClassifierPerformanceTester<STATE extends ClassifierState, CLASSES, EVALRET extends Comparable<EVALRET>, INPUT extends InputElement<CLASSES>> {

	public EVALRET test(STATE classifier, List<INPUT> trainingSet,
			EvaluationFunction<STATE, EVALRET, CLASSES, INPUT> evaluationFunction, double testingSetPercentage) {

		long testingSetCount = Math.round(testingSetPercentage / (double) trainingSet.size());

		Random random = new Random(trainingSet.size());

		List<INPUT> availableSet = listProducerCopy(trainingSet);
		List<INPUT> testingSet = listProducer();
		do {
			testingSet.add(availableSet.remove(random.nextInt(availableSet.size())));
		} while (testingSet.size() < testingSetCount);

		return evaluationFunction.evaluate(classifier, testingSet);
	}

	protected abstract List<INPUT> listProducerCopy(List<INPUT> original);

	protected abstract List<INPUT> listProducer();
}
