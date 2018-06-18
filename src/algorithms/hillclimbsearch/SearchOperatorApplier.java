package algorithms.hillclimbsearch;

public interface SearchOperatorApplier<T extends ClassifierState, K extends SearchOperator> {

	public T applySearchOperator(T inState, K searchOperator);

}
