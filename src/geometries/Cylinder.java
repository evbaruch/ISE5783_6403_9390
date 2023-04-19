package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

public class Cylinder extends Tube {
    private double height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this.height = height;
    }

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



        return null;//impossible case
    }
}
