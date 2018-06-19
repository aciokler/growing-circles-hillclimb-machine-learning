package algorithms.hillclimbsearch;

import java.util.List;

public interface EvaluationFunction<T extends ClassifierState, K extends Comparable<K>, P, Q extends InputElement<P>> {

	public K evaluate(T currentState, List<Q> trainingSet);

}
