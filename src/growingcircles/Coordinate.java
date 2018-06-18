package growingcircles;

public class Coordinate {

	private double x;
	private double y;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	private boolean areDecimalsEqual(double left, double right) {
		return !(left < right || left > right);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (!(obj instanceof Coordinate)) {
			return false;
		}

		Coordinate other = (Coordinate) obj;
		return areDecimalsEqual(this.getX(), other.getX()) && areDecimalsEqual(this.getY(), other.getY());
	}

	@Override
	public int hashCode() {
		return (int) (this.getX() + this.getY());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(this.getX()).append(", ").append(this.getY()).append(")");
		return sb.toString();
	}
}
