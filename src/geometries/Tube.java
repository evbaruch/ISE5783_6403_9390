package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends RadialGeometry {
    protected Ray axisRay;

    public Tube(Ray axisRay,double radius) {
        this.axisRay = axisRay;
        super.radius = radius;
    }

    public Vector getNormal(Point p) {

        //Calculate the vector from the point P0 to the point we got
        Vector v = p.subtract(axisRay.getP0());//There may be an exception here that the vector is zero
        //We will do a scalar multiplication between the vector of the line and the vector we got.
        // And because the vector of the line is normalized, the result will be the projection of
        // our vector on the line
        double d = v.dotProduct(axisRay.getDir());
        //We will build a point according to the direction of the vector
        // and add to it the projection from previous
        Point o = axisRay.getP0().add(axisRay.getDir().scale(d));
        //We will return the vector from point O from place to point I gave normalized

        Vector u = p.subtract(o);
        return u.normalize();
    }


    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
