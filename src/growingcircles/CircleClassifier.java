package growingcircles;

import algorithms.hillclimbsearch.ClassifierState;

public class CircleClassifier implements ClassifierState {

	private Coordinate center;
	private double radius;
	private double errorRate;

	public CircleClassifier(Coordinate center, double radius) {
		this.center = center;
		this.radius = radius;
		this.errorRate = -1.00;
	}

	public CircleClassifier(Coordinate center, double radius, double errorRate) {
		this(center, radius);
		this.errorRate = errorRate;
	}

	public Coordinate getCenter() {
		return center;
	}

	public void setCenter(Coordinate center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public double getErrorRate() {
		return errorRate;
	}

	public void setErrorRate(double errorRate) {
		this.errorRate = errorRate;
	}

	private boolean areDecimalsEqual(double left, double right) {
		boolean value = !(left < right || left > right);
		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof CircleClassifier)) {
			return false;
		}

		CircleClassifier other = (CircleClassifier) obj;
		return (this.getErrorRate() < 0.0 && this.getCenter().equals(other.getCenter())
				&& areDecimalsEqual(this.getRadius(), other.getRadius()))
				|| (this.getErrorRate() >= 0.0 && this.getErrorRate() <= other.getErrorRate());
	}

	@Override
	public int hashCode() {
		return (int) (this.getCenter().hashCode() + this.getRadius() + (this.getErrorRate() * 100.f));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(this.getCenter().getX()).append(")² + (").append(this.getCenter().getY()).append(")² = (")
				.append(this.getRadius()).append(")²\n");
		sb.append("E = ").append(this.getErrorRate());
		return sb.toString();
	}
}
