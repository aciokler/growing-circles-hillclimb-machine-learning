package algorithms.hillclimbsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class HillClimbClassifierSearch<STATE extends ClassifierState, OPERATOR extends SearchOperator, EVALRET extends Comparable<EVALRET>, CLASSES, INPUT extends InputElement<CLASSES>, EVAL extends EvaluationFunction<STATE, EVALRET, CLASSES, INPUT>> {

	private List<STATE> classifiers = new LinkedList<>();
	private List<STATE> seenClassifiers = new LinkedList<>();

	private EVAL evaluationFunction;
	private List<OPERATOR> searchOperators;

	private SearchOperatorApplier<STATE, OPERATOR> operatorApplier;

	public HillClimbClassifierSearch(EVAL evaluationFunction, List<OPERATOR> searchOperators,
			SearchOperatorApplier<STATE, OPERATOR> operatorApplier) {
		this.evaluationFunction = evaluationFunction;
		this.searchOperators = searchOperators;
		this.operatorApplier = operatorApplier;
	}

	public STATE findClassifier(STATE initialClassifierState, STATE finalClassifierState, List<INPUT> trainingSet,
			int maxNumberOfTries) {

		int numberOfTries = 0;

		classifiers.add(initialClassifierState);
		do {

			STATE state = classifiers.remove(0);
			if (state.equals(finalClassifierState)) {
				System.out.println("tries: " + numberOfTries);
				// System.out.println(state);
				return state;
			}
			seenClassifiers.add(state);

			// find new states
			List<STATE> newClassifierStates = new ArrayList<>();
			for (OPERATOR searchOperator : searchOperators) {
				STATE newState = operatorApplier.applySearchOperator(state, searchOperator);
				if (!seenClassifiers.contains(newState)) {
					newClassifierStates.add(newState);
				}
			}

			// sort new states
			Collections.sort(newClassifierStates,
					new StateComparator<STATE, EVALRET, CLASSES, INPUT>(evaluationFunction, trainingSet));

			// add at the front of states list
			classifiers.addAll(0, newClassifierStates);

			numberOfTries++;

			// System.out.println("try: " + numberOfTries);

		} while (!classifiers.isEmpty() && numberOfTries < maxNumberOfTries);

		System.out.println("tries: " + numberOfTries);

		return null;
	}

	public static class StateComparator<STATE extends ClassifierState, EVALRET extends Comparable<EVALRET>, CLASSES, INPUT extends InputElement<CLASSES>>
			implements Comparator<STATE> {

		private EvaluationFunction<STATE, EVALRET, CLASSES, INPUT> evaluationFunction;
		private List<INPUT> trainingSet;

		public StateComparator(EvaluationFunction<STATE, EVALRET, CLASSES, INPUT> evaluationFunction,
				List<INPUT> trainingSet) {
			this.evaluationFunction = evaluationFunction;
			this.trainingSet = trainingSet;
		}

		@Override
		public int compare(STATE stateLeft, STATE stateRight) {
			EVALRET ls = evaluationFunction.evaluate(stateLeft, trainingSet);
			EVALRET rs = evaluationFunction.evaluate(stateRight, trainingSet);
			return ls.compareTo(rs);
		}

	}
}
