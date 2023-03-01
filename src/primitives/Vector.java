package primitives;
/**
 * This class represents a vector in a three-dimensional space.
 * It extends the {@link Point} class, which means it has x, y, and z coordinates.
 */
public class Vector extends Point {
    /**
     * Constructs a new vector from a {@link Double3} object.
     * @param xyz the {@link Double3} object that contains the x, y, and z coordinates of the vector
     */
    public Vector(Double3 xyz) {
        super(xyz);
    }

    /**
     * Constructs a new vector with the given x, y, and z coordinates.
     * @param x the x coordinate of the vector
     * @param y the y coordinate of the vector
     * @param z the z coordinate of the vector
     */
    Vector(double x,double y,double z) {
        super(x,y,z);
    }

    /**
     * Returns true if the Vector object is equal to a given object.
     *
     * @param o the object to compare with the Vector
     * @return true if the Vector object is equal to the given object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    /**
     * Adds a vector to this vector and returns the result as a new vector.
     * @param p the vector to add to this vector
     * @return the sum of this vector and the given vector as a new vector
     */
    @Override
    public Vector add(Vector p) {
        return new Vector(this.xyz.add(p.xyz));
    }

    /**
     * Scales this vector by a given factor and returns the result as a new vector.
     * @param d the scaling factor
     * @return the scaled vector as a new vector
     */
    public Vector scale(double d) {
        return new Vector(this.xyz.scale(d));
    }

    /**
     * Calculates the cross product of this vector and another vector.
     * @param v the other vector
     * @return the cross product of this vector and the given vector as a new vector
     */
    public Vector crossProduct(Vector v){
        return new Vector(this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,
                          this.xyz.d3*v.xyz.d1 - this.xyz.d1*v.xyz.d3,
                          this.xyz.d1*v.xyz.d2 - this.xyz.d2*v.xyz.d1);
    }

    /**
     * Calculates the length of this vector squared.
     * @return the length of this vector squared
     */
    public double lengthSquered(){
    return this.distanceSquared(new Point(Double3.ZERO));
    }

    /**
     * Calculates the length of this vector.
     * @return the length of this vector
     */
    public double length(){
        return Math.sqrt(lengthSquered());
    }

    /**
     * Normalizes this vector and returns the result as a new vector.
     * @return the normalized vector as a new vector
     */
    public Vector normalize(){
        return new Vector(this.xyz.reduce(length()));
    }

    /**
     * Calculates the dot product of this vector and another vector.
     * @param v the other vector
     * @return the dot product of this vector and the given vector
     */
    public double dotProduct(Vector v){
        return (this.xyz.d1*v.xyz.d1 + this.xyz.d2*v.xyz.d2 + this.xyz.d3*v.xyz.d3);
    }

    /**
     * Returns a string representation of this vector.
     * @return a string representation of this vector
     */
    @Override
    public String toString() {
        return "Vector{" +
                "v =" + xyz +
                '}';
    }
}
