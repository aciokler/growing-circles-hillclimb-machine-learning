package growingcircles;

import java.util.LinkedList;
import java.util.List;

import algorithms.hillclimbsearch.SearchOperator;

public class CircleChange implements SearchOperator {

	private double radiusChange = 0.1;
	private Coordinate positionAjustment;

	public CircleChange() {
	}

	public CircleChange(double radius) {
		this.radiusChange = radius;
	}

	public CircleChange(Coordinate positionAdjustment) {
		this.positionAjustment = positionAdjustment;
	}

	public CircleChange(double radius, double positionShift) {
		this.radiusChange = radius;
	}

	public CircleChange(double radius, Coordinate positionAdjustment) {
		this(radius);
		this.positionAjustment = positionAdjustment;
	}

	public double getRadiusChange() {
		return radiusChange;
	}

	public void setRadiusChange(double radiusChange) {
		this.radiusChange = radiusChange;
	}

	public Coordinate getPositionAjustment() {
		return positionAjustment;
	}

	public void setPositionAjustment(Coordinate positionAjustment) {
		this.positionAjustment = positionAjustment;
	}

	public static List<CircleChange> getPossibleOperators() {

		final double positionShift = 0.05;

		List<CircleChange> list = new LinkedList<>();
		list.add(new CircleChange(new Coordinate(0.0, positionShift)));
		list.add(new CircleChange(new Coordinate(0.0, -1.0 * positionShift)));

		list.add(new CircleChange(new Coordinate(-1.0 * positionShift, positionShift)));
		list.add(new CircleChange(new Coordinate(-1.0 * positionShift, -1.0 * positionShift)));

		list.add(new CircleChange(new Coordinate(positionShift, positionShift)));
		list.add(new CircleChange(new Coordinate(positionShift, -1.0 * positionShift)));

		list.add(new CircleChange(new Coordinate(positionShift, 0.0)));
		list.add(new CircleChange(new Coordinate(-1.0 * positionShift, 0.0)));

		return list;
	}

}
