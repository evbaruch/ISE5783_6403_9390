package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    private Point center;

    public Vector getNormal(Point p) {
        Vector v = center.subtract(p);
        v.normalize();
        return v;
    }
}
