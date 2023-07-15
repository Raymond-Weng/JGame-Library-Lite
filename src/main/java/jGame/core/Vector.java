package jGame.core;

public class Vector {

    private double x;
    private double y;

    /**
     * Creates a Vector object whose elements have the specified values.
     *
     * @param x the value of the x elements of the vector.
     * @param y the value of the y elements of the vector.
     */
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new Vector object whose two elements have the same value.
     *
     * @param val the value of both elements of the vector.
     */
    public Vector(double val) {
        this.x = val;
        this.y = val;
    }

    /**
     * Sets the elements to the absolute values of each of this instance's elements.
     */
    public void abs() {
        x = Math.abs(x);
        y = Math.abs(y);
    }

    /**
     * Returns a vector whose elements are the absolute values of each of the specified vector's elements.
     *
     * Note that the specified vector is unchanged and a new absolute vector is returned.
     *
     * @param vec2 the vector to be computed.
     * @return a vector whose elements are the absolute values of each of the specified vector's elements.
     */
    public static Vector abs(Vector vec2) {
        return new Vector(Math.abs(vec2.x), Math.abs(vec2.y));
    }

    /**
     * Returns a value that indicates whether this instance and another vector are equal.
     *
     * @param vec2 the vector you want to compare with.
     * @return a value that indicates whether this instance and another vector are equal.
     */
    public boolean equals(Vector vec2) {
        return (x == vec2.x && y == vec2.y);
    }

    /**
     * Returns a vector whose elements are the maximum of each of the pairs of elements in two specified vectors.
     *
     * @param vec1 the vector to be computed.
     * @param vec2 the vector to be computed.
     * @return a vector whose elements are the maximum of each of the pairs of elements in two specified vectors.
     */
    public static Vector max(Vector vec1, Vector vec2) {
        return new Vector(Math.max(vec1.x, vec2.x), Math.max(vec1.y, vec2.y));
    }

    /**
     * Returns a vector whose elements are the minimum of each of the pairs of elements in two specified vectors.
     *
     * @param vec1 the vector to be computed.
     * @param vec2 the vector to be computed.
     * @return a vector whose elements are the minimum of each of the pairs of elements in two specified vectors.
     */
    public static Vector min(Vector vec1, Vector vec2) {
        return new Vector(Math.min(vec1.x, vec2.x), Math.min(vec1.y, vec2.y));
    }

    /**
     * Divides this instance by a specified scalar value.
     *
     * @param val divisor.
     */
    public void divide(double val) {
        x /= val;
        y /= val;
    }

    /**
     * Divides the specified vector by a specified scalar value.
     *
     * Note that the specified vector is unchanged and a new divided vector is returned.
     *
     * @param vec2 dividend.
     * @param val divisor.
     * @return quotient.
     */
    public static Vector divide(Vector vec2, double val) {
        return new Vector(vec2.x / val, vec2.y / val);
    }

    /**
     * Divides this instance  by a specified vector.
     *
     * @param vec2 dividend.
     */
    public void divide(Vector vec2) {
        x /= vec2.x;
        y /= vec2.y;
    }

    /**
     * Divides the first vector by the second one.
     *
     * Note that the specified vector is unchanged and a new divided vector is returned.
     *
     * @param vec1 dividend.
     * @param vec2 divisor.
     * @return quotient.
     */
    public static Vector divide(Vector vec1, Vector vec2) {
        return new Vector(vec1.x / vec2.x, vec1.y / vec2.y);
    }

    /**
     * Multiplies this instance by a specified scalar value.
     *
     * @param val multiplier
     */
    public void multiply(double val) {
        x *= val;
        y *= val;
    }

    /**
     * Returns a vector whose elements are the value of the specified vector multiplies by a scalar value.
     *
     * @param vec2 multiplicand.
     * @param val multiplier.
     * @return quotient.
     */
    public static Vector multiply(Vector vec2, double val) {
        return new Vector(vec2.x * val, vec2.y * val);
    }

    /**
     * Returns a vector whose elements are the value of the specified vector multiplies by a scalar value.
     *
     * @param val multiplicand.
     * @param vec2 multiplier.
     * @return quotient.
     */
    public static Vector multiply(double val, Vector vec2) {
        return new Vector(vec2.x * val, vec2.y * val);
    }

    /**
     * Subtracts this instance's elements by a specified value.
     *
     * @param val subtrahend.
     */
    public void subtract(double val) {
        x -= val;
        y -= val;
    }

    /**
     * Subtracts this instance's elements by a specified value.
     *
     * Note that the specified vector is unchanged and a new subtracted vector is returned.
     *
     * @param vec2 minuend.
     * @param val subtrahend.
     * @return quotient.
     */
    public static Vector subtract(Vector vec2, double val) {
        return new Vector(vec2.x - val, vec2.y - val);
    }

    /**
     * Subtracts a specified vector from this instance.
     *
     * @param vec2 subtrahend.
     */
    public void subtract(Vector vec2) {
        x -= vec2.x;
        y -= vec2.y;
    }

    /**
     * Subtract the second vector from the first one.
     *
     * Note that neither of the two vector is changed and a new subtracted vector is returned.
     *
     * @param vec2_1 minuend.
     * @param vec2_2 subtrahend.
     * @return vec2, quotient.
     */
    public static Vector subtract(Vector vec2_1, Vector vec2_2) {
        return new Vector(vec2_1.x - vec2_2.x, vec2_1.y - vec2_2.y);
    }

    /**
     * Adds the specified value to this instance's elements.
     *
     * @param val addend.
     */
    public void add(double val) {
        x += val;
        y += val;
    }

    /**
     * Adds the specified value to the specified vector's elements.
     *
     * Note that the specified vector is unchanged and a new added vector is returned.
     *
     * @param vec2 summand.
     * @param val addend.
     * @return quotient.
     */
    public static Vector add(Vector vec2, double val) {
        return new Vector(vec2.x + val, vec2.y + val);
    }

    /**
     * Adds this instance by a specified vector.
     *
     * @param vec2 addend.
     */
    public void add(Vector vec2) {
        x += vec2.x;
        y += vec2.y;
    }

    /**
     * Adds two vectors together.
     *
     * Note that neither of the two vector is changed and a new added vector is returned.
     *
     * @param vec2_1 summand.
     * @param vec2_2 addend.
     * @return vec2, quotient.
     */
    public static Vector add(Vector vec2_1, Vector vec2_2) {
        return new Vector(vec2_1.x + vec2_2.x, vec2_1.y + vec2_2.y);
    }

    /**
     * Makes this vector have a magnitude of 1.
     *
     * When normalized, a vector keeps the same direction but its length is 1.0.
     *
     * Note that the current vector is unchanged and a new normalized vector is returned.
     *
     * @param vec2, the vector you want to normalize.
     * @return a normalized vector.
     */
    public static Vector normalized(Vector vec2) {
        if(vec2.x == 0 && vec2.y == 0) {
            return new Vector(0, 0);
        }
        else if(vec2.x == 0) {
            if(vec2.y > 0) {
                return new Vector(0, 1);
            }
            else if(vec2.y < 0) {
                return new Vector(0, -1);
            }
        }
        else if(vec2.y == 0) {
            if(vec2.x > 0) {
                return new Vector(1, 0);
            }
            else if(vec2.x < 0) {
                return new Vector(-1, 0);
            }
        }

        double theta = Math.atan(vec2.y / vec2.x);
        return new Vector(Math.cos(theta), Math.sin(theta));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}