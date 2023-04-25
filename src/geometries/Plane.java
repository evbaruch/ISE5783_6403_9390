package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

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
        normal = v1.crossProduct(v2).normalize();
    }

    public Vector getNormal() {
        return this.normal;
    }


    @Override
    public Vector getNormal(Point p) {
        return this.normal;
    }


    @Override
    public List<Point> findIntsersections(Ray ray) {
        List<Point> result = null;

        Vector n = getNormal();
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        double nv = n.dotProduct(v);
//        if (isZero(nv)){
            double t =n.dotProduct(q0.subtract(p0)) / nv;

            if (t > 0 && Double.isFinite(t)) {
                // Create a new Point object with the calculated coordinates and return it.
                 Point intersect = p0.add(v.scale(t));
                result = List.of(intersect);
            }
        //}

        return result;
    }
}
