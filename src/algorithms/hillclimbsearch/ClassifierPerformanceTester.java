package algorithms.hillclimbsearch;

import java.util.List;

public class ClassifierPerformanceTester<STATE extends ClassifierState, K, INPUT extends InputElement<K>> {

	public void test(STATE classifier, List<INPUT> trainingSet, double testingSetPercentage) {

		double testingSetCount = Math.round(testingSetPercentage / (double) trainingSet.size());

	}

}
