package hola;



public class SVector3d implements Comparable<SVector3d> {
	
	private static final double DEFAULT_COMPONENT = 0.0;

	private final double x;

	private final double y;

	private final double z;

	public SVector3d() {
		this(DEFAULT_COMPONENT, DEFAULT_COMPONENT, DEFAULT_COMPONENT);
	}

	public SVector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public SVector3d(double x, double y) {
		this(x, y, DEFAULT_COMPONENT);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public SVector3d add(SVector3d v) {
		return new SVector3d(x + v.x, y + v.y, z + v.z);
	}

	public SVector3d substract(SVector3d v) {

		return new SVector3d(x - v.x, y - v.y, z - v.z);
	}

	public SVector3d multiply(double m) {

		return new SVector3d(m * x, m * y, m * z);
	}

	public double modulus() {

		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	public static double distance(SVector3d A, SVector3d B) {
		return B.substract(A).modulus();
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (!(obj instanceof SVector3d))
			return false;

		SVector3d other = (SVector3d) obj;

		// Comparaison des valeurs x,y et z en utilisant la méthode de comparaison de
		// SMath
		if (!(Double.compare(x, other.x) == 0))
			return false;

		if (!(Double.compare(y, other.y) == 0))
			return false;

		if (!(Double.compare(z, other.z) == 0))
			return false;

		return true;
	}

	@Override
	public int compareTo(SVector3d o) {
		int comp;

		comp = Double.compare(this.x, o.x);

		if (comp != 0)
			return comp;

		comp = Double.compare(this.y, o.y);

		if (comp != 0)
			return comp;

		return Double.compare(this.z, o.z);
	}


}
