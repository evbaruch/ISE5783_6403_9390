package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere extends RadialGeometry {
    private Point center;

    public Sphere(Point center,double radius) {
        this.center = center;
        super.radius = radius;
    }

    public Vector getNormal(Point p) {
        Vector v = p.subtract(center);//There may be an exception here that the vector is zero
        return v.normalize();
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
