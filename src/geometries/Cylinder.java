package geometries;

import primitives.Point;
import primitives.Vector;

public class Cylinder extends Tube {
    private double height;

    @Override
    public Vector getNormal(Point p) {
        //Calculate the vector from the point P0 to the point we got
        Vector v = axisRay.getP0().subtract(p);//There may be an exception here that the vector is zero
        //We will do a scalar multiplication between the vector of the line and the vector we got.
        // And because the vector of the line is normalized, the result will be the projection of
        // our vector on the line
        double d = v.dotProduct(axisRay.getDir());

        if (d <= height)
        {
            return super.getNormal(p);
        }else{
            throw new IllegalArgumentException("ERROR: A point over the size of the Cylinder was obtained");
        }
    }
}
