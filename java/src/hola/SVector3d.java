package hola;

/**
 * <p>
 * La classe <b>SVector3d</b> représente une vecteur <i>x</i>, <i>y</i> et
 * <i>z</i> à trois dimensions.
 * </p>
 * 
 * <p>
 * Les opérations mathématiques supportées sont les suivantes :
 * <ul>
 * - L'addition.
 * </ul>
 * <ul>
 * - La soustraction.
 * </ul>
 * <ul>
 * - La multiplication par un scalaire.
 * </ul>
 * <ul>
 * - Le produit scalaire (<i>dot product</i>).
 * </ul>
 * <ul>
 * - Le produit vectoriel (<i>cross product</i>).
 * </ul>
 * <ul>
 * - La normalisation (vecteur de module 1).
 * </ul>
 * </p>
 * 
 * @author Simon Vezina
 * @author Simon Tran
 * @since 2014-12-16
 * @version 2017-12-20 (version labo – Le ray tracer v2.1)
 */

public class SVector3d implements Comparable<SVector3d> {
	/**
	 * La constante 'DEFAULT_COMPONENT' correspond à la composante par défaut des
	 * variables x,y et z du vecteur étant égale à {@value}.
	 */
	private static final double DEFAULT_COMPONENT = 0.0;

	// --------------
	// VARIABLES //
	// --------------

	/**
	 * La variable <b>x</b> correspond à la composante x du vecteur 3d.
	 */
	private final double x;

	/**
	 * La variable <b>y</b> correspond à la composante y du vecteur 3d.
	 */
	private final double y;

	/**
	 * La variable <b>z</b> correspond à la composante z du vecteur 3d.
	 */
	private final double z;

	// -----------------
	// CONSTRUCTEURS //
	// -----------------
	/**
	 * Constructeur représentant un vecteur 3d à l'origine d'un système d'axe xyz.
	 */
	public SVector3d() {
		this(DEFAULT_COMPONENT, DEFAULT_COMPONENT, DEFAULT_COMPONENT);
	}

	/**
	 * Constructeur avec composante <i>x</i>,<i>y</i> et <i>z</i> du vecteur 3d.
	 * 
	 * @param x
	 *            La composante <i>x</i> du vecteur.
	 * @param y
	 *            La composante <i>y</i> du vecteur.
	 * @param z
	 *            La composante <i>z</i> du vecteur.
	 */
	public SVector3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructeur avec composante <i>x</i> et <i>y</i> vecteur 3d. La composante <i>z</i> est assignée à zéro.
	 * 
	 * @param x
	 *            La composante <i>x</i> du vecteur.
	 * @param y
	 *            La composante <i>y</i> du vecteur.
	 */
	public SVector3d(double x, double y) {
		this(x, y, DEFAULT_COMPONENT);
	}

	// ------------
	// MÉTHODES //
	// ------------

	/**
	 * Méthode qui donne accès à la coordonnée x du vecteur.
	 * 
	 * @return La coordonnée x.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Méthode qui donne accès à la coordonnée y du vecteur.
	 * 
	 * @return La coordonnée y.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Méthode qui donne accès à la coordonnée z du vecteur.
	 * 
	 * @return La coordonnée z.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Méthode qui calcule <b>l'addition</b> de deux vecteurs.
	 * 
	 * @param v
	 *            Le vecteur à ajouter.
	 * @return La somme des deux vecteurs.
	 */
	public SVector3d add(SVector3d v) {
		return new SVector3d(x + v.x, y + v.y, z + v.z);
	}

	/**
	 * Méthode qui calcul la <b>soustraction</b> de deux vecteurs.
	 * 
	 * @param v
	 *            - Le vecteur à soustraire au vecteur présent.
	 * @return La soustraction des deux vecteurs.
	 */
	public SVector3d substract(SVector3d v) {

		return new SVector3d(x - v.x, y - v.y, z - v.z);
	}

	/**
	 * Méthode qui effectue la <b>multiplication d'un vecteur par une scalaire</b>.
	 * 
	 * @param m
	 *            Le scalaire.
	 * @return La multiplication d'un vecteur par un scalaire.
	 */
	public SVector3d multiply(double m) {

		return new SVector3d(m * x, m * y, m * z);
	}

	/**
	 * Permet de savoir si un vecteur est nul
	 * @return Vrai si le vecteur est nul
	 */
	public boolean isNull() {
		return this.compareTo(new SVector3d()) == 0;
	}

	/**
	 * Méthode pour obtenir le <b>module</b> d'un vecteur.
	 * 
	 * @return Le module du vecteur.
	 */
	public double modulus() {

		return Math.sqrt((x * x) + (y * y) + (z * z));
	}

	/**
	 * Méthode pour <b>normaliser</b> un vecteur à trois dimensions. Un vecteur
	 * normalisé possède la même orientation, mais possède une <b> longeur unitaire
	 * </b>. Si le <b>module du vecteur est nul</b>, le vecteur normalisé sera le
	 * <b> vecteur nul </b> (0.0, 0.0, 0.0).
	 * 
	 * @return Le vecteur normalisé.
	 */
	public SVector3d normalize() {
		double mod = modulus();

		// Vérification du module. S'il est trop petit, nous ne pouvons pas
		// numériquement normaliser ce vecteur
		if (Double.compare(mod, 0.0) == 0)
		    return new SVector3d();
		else
			return new SVector3d(x / mod, y / mod, z / mod);
	}

	/**
	 * Méthode pour effectuer le <b>produit scalaire</b> entre deux vecteurs.
	 * 
	 * @param v
	 *            Le vecteur à mettre en produit scalaire.
	 * @return Le produit scalaire entre les deux vecteurs.
	 */
	public double dot(SVector3d v) {

		return (x * v.x + y * v.y + z * v.z);
	}

	/**
	 * Méthode pour effectuer le <b>produit vectoriel</b> avec un autre vecteur v.
	 * <p>
	 * Cette version du produit vectoriel est implémenté en respectant la <b> règle
	 * de la main droite </b>. Il est important de rappeler que le produit vectoriel
	 * est <b> anticommutatif </b> (AxB = -BxA) et que l'ordre des vecteurs doit
	 * être adéquat pour obtenir le résultat désiré. Si l'un des deux vecteurs est
	 * <b> nul </b> ou les deux vecteurs sont <b> parallèles </b>, le résultat sera
	 * un <b> vecteur nul </b>.
	 * </p>
	 * 
	 * @param v
	 *            Le second vecteur dans le produit vectoriel.
	 * @return Le résultat du produit vectoriel.
	 */
	public SVector3d cross(SVector3d v) {

		return new SVector3d(y * v.z - z * v.y, -1 * (x * v.z - z * v.x), x * v.y - y * v.x);
	}

	/**
	 * Méthode pour obtenir la distance entre deux points.
	 * 
	 * @param A
	 *            Le premier point.
	 * @param B
	 *            Le deuxième point.
	 * @return La distance entre le point A et B.
	 */
	public static double distance(SVector3d A, SVector3d B) {
		return B.substract(A).modulus();
	}

	/**
	 * Méthode permettant d'obtenir l'angle entre deux vecteurs A et B. L'angle sera
	 * obtenu en radian.
	 * 
	 * @param A
	 *            Le premier vecteur.
	 * @param B
	 *            Le second vecteur.
	 * @return L'angle entre les deux vecteurs.
	 */
	public static double angleBetween(SVector3d A, SVector3d B) {
		return Math.acos(A.dot(B) / (A.modulus() * B.modulus()));
	}


	@Override
	public String toString() {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	@Override
	public int hashCode() {
		// hashcode autogénéré par Eclipse avec les paramètres x, y et z
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		// Variable déterminant la comparaison
		// ( comp < 0 ==> plus petit )
		// ( comp > 0 ==> plus grand )
		// ( comp = 0 ==> égal )
		int comp;

		// Comparaison sur les x
		comp = Double.compare(this.x, o.x);

		if (comp != 0)
			return comp;

		// Comparaison sur les y
		comp = Double.compare(this.y, o.y);

		if (comp != 0)
			return comp;

		// Comparaison sur les z
		return Double.compare(this.z, o.z);
	}


}// fin de la classe SVector3d
