package growingcircles;

import algorithms.hillclimbsearch.InputElement;

public class Position implements InputElement<Boolean> {

	Coordinate coordinate;
	private Boolean positionClass;

	public Position(Coordinate coordinate, Boolean positionClass) {
		this.coordinate = coordinate;
		this.positionClass = positionClass;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	@Override
	public Boolean getInputClass() {
		return positionClass;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getCoordinate().toString()).append(", positive: ").append(this.getInputClass()).append("\n");
		return sb.toString();
	}
}
