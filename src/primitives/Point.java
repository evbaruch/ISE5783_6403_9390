package primitives;

import java.nio.channels.Pipe;
import java.util.Objects;

import static primitives.Util.isZero;

public class Point {
    Double3  xyz;

    public final static Point ZERO = new Point(0,0,0);

    /**
     * Constructs a new Point object with the specified coordinates.
     *
     * @param xyz a Double3 object containing the x, y, and z coordinates of the point
     */
    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    /**
     * Constructs a new Point object with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     * @param z the z-coordinate of the point
     */
    public Point(double x,double y,double z) {
        this.xyz = new Double3(x,y,z);
    }

    /**
     * Determines whether this Point object is equal to another object.
     * Two Point objects are considered equal if they have the same coordinates.
     *
     * @param o the object to compare with this Point
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return isZero(this.xyz.d1 - point.xyz.d1)
                && isZero(this.xyz.d2 - point.xyz.d2)
                && isZero(this.xyz.d3 - point.xyz.d3);
    }


    /**
     * Returns a string representation of this Point object.
     * The string contains the coordinates of the point.
     *
     * @return a string representation of this Point object
     */
    @Override
    public String toString() {
        return "Point{" +
                "(x,y,z)=" + xyz +
                '}';
    }

    /**
     * Adds a vector to this point and returns a new Point object with the resulting coordinates.
     *
     * @param v the vector to add to this point
     * @return a new Point object with the resulting coordinates
     */
    public Point add(Vector v){

        return new Point(this.xyz.add(v.xyz));
    }

    /**
     * Subtracts another point from this point and returns a new Vector object with the resulting coordinates.
     *
     * @param p the point to subtract from this point
     * @return a new Vector object with the resulting coordinates
     */
    public Vector subtract(Point p)
    {
        if (this.equals(p)){
            throw new IllegalArgumentException("ERROR: The same value cannot be subtracted");
        }
        return new Vector(this.xyz.subtract(p.xyz));
    }

    /**
     * Computes the squared distance between this point and another point.
     * This is a faster alternative to computing the actual distance between the points,
     * since it avoids taking the square root.
     *
     * @param p the point to compute the squared distance to
     * @return the squared distance between this point and the other point
     */
    public double distanceSquared(Point p){
        return (p.xyz.d1 - this.xyz.d1)*(p.xyz.d1 - this.xyz.d1) +
               (p.xyz.d2 - this.xyz.d2)*(p.xyz.d2 - this.xyz.d2) +
               (p.xyz.d3 - this.xyz.d3)*(p.xyz.d3 - this.xyz.d3);
    }

    /**
     * Computes the distance between this point and another point.
     *
     * @param p the point to compute the distance to
     * @return the distance between this point and the other point
     */
    public double distance(Point p){
        return Math.sqrt(distanceSquared(p));
    }
    public Point movePointOnViewPlane(Vector vUp, Vector vRight, double randomX, double randomY , double Rx , double Ry) {
        // Step 1: Determine the displacement vector on the plane
        Vector displacement = vUp.scale(randomX).add(vRight.scale(randomY));

        // Step 2: Update the origin point of the ray
        Point newPoint = this.add(displacement);

        return newPoint;
    }

    public double getX() {
        return this.xyz.d1;
    }
    public double getY() {
        return this.xyz.d2;
    }
    public double getZ() {
        return this.xyz.d3;
    }
}
