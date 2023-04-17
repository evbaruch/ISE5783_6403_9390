package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry{

    private final Point q0;

    private final Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    public Plane(Point a, Point b, Point c) {
        q0 = a;
        Vector v1 =  a.subtract(b);
        Vector v2 =  a.subtract(c);
        normal = v2.crossProduct(v1).normalize();
    }

    public Vector getNormal() {
        return this.normal;
    }


    @Override
    public Vector getNormal(Point p) {
        return this.normal;
    }


}
