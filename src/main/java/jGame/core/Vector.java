/**
 *  A vector is unchangeable, create a new one to access the values.
 */


package jGame.core;

public class Vector {

    private final double X;
    private final double Y;

    /**
     * Creates a Vector object whose elements have the specified values.
     *
     * @param x the value of the x elements of the vector.
     * @param y the value of the y elements of the vector.
     */
    public Vector(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    /**
     * Creates a new Vector object whose two elements have the same value.
     *
     * @param val the value of both elements of the vector.
     */
    public Vector(double val) {
        this.X = val;
        this.Y = val;
    }

    /**
     * Returns the x value of the vector.
     *
     * @return the x value of the vector.
     */
    public double getX() {
        return X;
    }

    /**
     * Sets the x value of the vector.
     *
     * @param x the value that'll be set to the vector's x element.
     * @return set vector.
     */
    public Vector setX(double x) {
        return new Vector(x, this.Y);
    }

    /**
     * Returns the y value of the vector.
     *
     * @return the y value of the vector.
     */
    public double getY() {
        return Y;
    }

    /**
     * Sets the y value of the vector.
     *
     * @param y the value that'll be set to the vector's y element.
     * @return set vector.
     */
    public Vector setY(double y) {
        return new Vector(this.X, y);
    }

    /**
     * Sets the elements to the absolute values of each of this instance's elements.
     *
     * @return absolute vector.
     */
    public Vector abs() {
        return new Vector(Math.abs(X), Math.abs(Y));
    }

    /**
     * Returns a vector whose elements are the absolute values of each of the specified vector's elements.
     *
     * @param vec the vector to be computed.
     * @return a vector whose elements are the absolute values of each of the specified vector's elements.
     */
    public static Vector abs(Vector vec) {
        return new Vector(Math.abs(vec.getX()), Math.abs(vec.getY()));
    }

    /**
     * Computes the Euclidean distance between the two given points.
     *
     * @param vec1
     * @param vec2
     * @return double, the distance of the points.
     */
    public static double distance(Vector vec1, Vector vec2) {
        return Math.sqrt(Math.pow(vec1.getX() - vec2.getX(), 2) + Math.pow(vec1.getY() - vec2.getY(), 2));
    }

    /**
     * Returns a value that indicates whether this instance and another vector are equal.
     *
     * @param vec the vector you want to compare with.
     * @return a value that indicates whether this instance and another vector are equal.
     */
    public boolean equals(Vector vec) {
        return (X == vec.getX() && Y == vec.getY());
    }

    /**
     * Returns a value the indicates whether the two vectors are equaled.
     *
     * @param vec1 the first vector you to be compared.
     * @param vec2 the second vector you to be compared.
     * @return a value the indicates whether the two vectors are equaled.
     */
    public static boolean equals(Vector vec1, Vector vec2) {
        return (vec1.getX() == vec2.getX() && vec1.getY() == vec2.getY());
    }

    /**
     * Returns the magnitude of the vector.
     *
     * For example, given vector(3, 4), it'll return 5.
     *
     * @return the magnitude of the vector.
     */
    public double magnitude() {
        return distance(new Vector(0, 0), this);
    }

    /**
     * Returns a vector whose elements are the maximum of each of the pairs of elements in two specified vectors.
     *
     * @param vec1 the vector to be computed.
     * @param vec2 the vector to be computed.
     * @return a vector whose elements are the maximum of each of the pairs of elements in two specified vectors.
     */
    public static Vector max(Vector vec1, Vector vec2) {
        return new Vector(Math.max(vec1.getX(), vec2.getX()), Math.max(vec1.getY(), vec2.getY()));
    }

    /**
     * Returns a vector whose elements are the minimum of each of the pairs of elements in two specified vectors.
     *
     * @param vec1 the vector to be computed.
     * @param vec2 the vector to be computed.
     * @return a vector whose elements are the minimum of each of the pairs of elements in two specified vectors.
     */
    public static Vector min(Vector vec1, Vector vec2) {
        return new Vector(Math.min(vec1.getX(), vec2.getX()), Math.min(vec1.getY(), vec2.getY()));
    }

    /**
     * Divides this instance by a specified scalar value.
     *
     * @param val divisor.
     * @return quotient.
     */
    public Vector divide(double val) {
        return new Vector(X / val, Y / val);
    }

    /**
     * Divides the specified vector by a specified scalar value.
     *
     * @param vec dividend.
     * @param val divisor.
     * @return quotient.
     */
    public static Vector divide(Vector vec, double val) {
        return new Vector(vec.getX() / val, vec.getY() / val);
    }

    /**
     * Divides this instance  by a specified vector.
     *
     * @param vec dividend.
     * @return quotient.
     */
    public Vector divide(Vector vec) {
        return new Vector(X / vec.getX(), Y / vec.getY());
    }

    /**
     * Divides the first vector by the second one.
     *
     * @param vec1 dividend.
     * @param vec2 divisor.
     * @return quotient.
     */
    public static Vector divide(Vector vec1, Vector vec2) {
        return new Vector(vec1.getX() / vec2.getX(), vec1.getY() / vec2.getY());
    }

    /**
     * Multiplies this instance by a specified scalar value.
     *
     * @param val multiplier
     * @return quotient.
     */
    public Vector multiply(double val) {
        return new Vector(X * val, Y * val);
    }

    /**
     * Returns a vector whose elements are the value of the specified vector multiplies by a scalar value.
     *
     * @param vec multiplicand.
     * @param val multiplier.
     * @return quotient.
     */
    public static Vector multiply(Vector vec, double val) {
        return new Vector(vec.getX() * val, vec.getY() * val);
    }

    /**
     * Returns a vector whose elements are the value of the specified vector multiplies by a scalar value.
     *
     * @param val multiplicand.
     * @param vec multiplier.
     * @return quotient.
     */
    public static Vector multiply(double val, Vector vec) {
        return new Vector(vec.getX() * val, vec.getY() * val);
    }

    /**
     * Subtracts this instance's elements by a specified value.
     *
     * @param val subtrahend.
     * @return quotient.
     */
    public Vector subtract(double val) {
        return new Vector(X - val, Y - val);
    }

    /**
     * Subtracts this instance's elements by a specified value.
     *
     * Note that the specified vector is unchanged and a new subtracted vector is returned.
     *
     * @param vec minuend.
     * @param val subtrahend.
     * @return quotient.
     */
    public static Vector subtract(Vector vec, double val) {
        return new Vector(vec.getX() - val, vec.getY() - val);
    }

    /**
     * Subtracts a specified vector from this instance.
     *
     * @param vec subtrahend.
     * @return quotient.
     */
    public Vector subtract(Vector vec) {
        return new Vector(X - vec.getX(), Y - vec.getY());
    }

    /**
     * Subtract the second vector from the first one.
     *
     * Note that neither of the two vector is changed and a new subtracted vector is returned.
     *
     * @param vec1 minuend.
     * @param vec2 subtrahend.
     * @return vec2, quotient.
     */
    public static Vector subtract(Vector vec1, Vector vec2) {
        return new Vector(vec1.X - vec2.X, vec1.Y - vec2.Y);
    }

    /**
     * Adds the specified value to this instance's elements.
     *
     * @param val addend.
     * @return quotient.
     */
    public Vector add(double val) {
        return new Vector(this.X + val, this.Y + val);
    }

    /**
     * Adds the specified value to the specified vector's elements.
     *
     * Note that the specified vector is unchanged and a new added vector is returned.
     *
     * @param vec summand.
     * @param val addend.
     * @return quotient.
     */
    public static Vector add(Vector vec, double val) {
        return new Vector(vec.X + val, vec.Y + val);
    }

    /**
     * Adds this instance by a specified vector.
     *
     * @param vec addend.
     * @return quotient.
     */
    public Vector add(Vector vec) {
        return new Vector(this.X + vec.getX(), this.Y + vec.getY());
    }

    /**
     * Adds two vectors together.
     *
     * Note that neither of the two vector is changed and a new added vector is returned.
     *
     * @param vec1 summand.
     * @param vec2 addend.
     * @return vec2, quotient.
     */
    public static Vector add(Vector vec1, Vector vec2) {
        return new Vector(vec1.X + vec2.X, vec1.Y + vec2.Y);
    }

    /**
     * Returns if both elements this instance is NaN or not.
     *
     * @return if both elements this instance is NaN or not.
     */
    public boolean isBothNaN() {
        return Double.isNaN(X) && Double.isNaN(Y);
    }

    /**
     * Makes this vector have a magnitude of 1.
     *
     * When normalized, a vector keeps the same direction but its length is 1.0.
     *
     * Note that the current vector is unchanged and a new normalized vector is returned.
     *
     * @return a normalized vector.
     */
    public Vector normalized() {
        Vector normalizedVector = Vector.divide(this, this.magnitude());
        return !normalizedVector.isBothNaN() ? normalizedVector : new Vector(0);
    }
}