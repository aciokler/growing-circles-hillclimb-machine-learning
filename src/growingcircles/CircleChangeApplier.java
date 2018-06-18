package growingcircles;

import algorithms.hillclimbsearch.SearchOperatorApplier;

public class CircleChangeApplier implements SearchOperatorApplier<CircleClassifier, CircleChange> {

	@Override
	public CircleClassifier applySearchOperator(CircleClassifier inState, CircleChange searchOperator) {
		return new CircleClassifier(
				new Coordinate(inState.getCenter().getX() + searchOperator.getPositionAjustment().getX(),
						inState.getCenter().getY() + searchOperator.getPositionAjustment().getY()),
				inState.getRadius() + searchOperator.getRadiusChange());
	}

}
