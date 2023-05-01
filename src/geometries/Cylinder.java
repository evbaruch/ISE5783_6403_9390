package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Represents a cylinder in 3D space, which is a tube with a finite height.
 */
public class Cylinder extends Tube {
    private double height;

    /**
     * Constructs a new cylinder with the given axis ray, radius, and height.
     *
     * @param axisRay the axis ray of the cylinder
     * @param radius the radius of the cylinder
     * @param height the height of the cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

    /**
     * Computes and returns the normal vector to the surface of the cylinder at the given point.
     *
     * @param p the point on the surface of the cylinder
     * @return the normal vector to the surface of the cylinder at the given point
     */
    @Override
    public Vector getNormal(Point p) {

        if (super.axisRay.getP0().equals(p)){
            return super.axisRay.getDir().normalize();
        }

        //Calculate the vector from the point P0 to the point we got
        Vector v = p.subtract(axisRay.getP0());//There may be an exception here that the vector is zero
        //We will do a scalar multiplication between the vector of the line and the vector we got.
        // And because the vector of the line is normalized, the result will be the projection of
        // our vector on the line
        double d = alignZero(v.dotProduct(axisRay.getDir()));

        if (d <= 0) {
            return super.axisRay.getDir().normalize();//.scale(-1)
        }

        if (alignZero(height - d) > 0) {
            return super.getNormal(p);
        }

        if (d >= height) {
           return super.axisRay.getDir().normalize();
        }


//        // If none of the above conditions are true, then the point is on the lateral surface of the cylinder.
//        // We can compute the normal vector by subtracting the projection of the vector v onto the axis ray from v itself.
//        return v.subtract(super.axisRay.getDir().scale(d)).normalize();
        return null;//impossible case
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
