package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Polygon {
    public Triangle(Point a, Point b, Point c) {
        super(a,b,c);
    }

    /**
     * Helper method to find the geometric intersections between a ray and the triangle.
     *
     * @param ray         The ray to intersect with the triangle
     * @param maxDistance The maximum distance of valid intersections
     * @return A list of GeoPoint objects representing the geometric intersections,
     *         or null if no intersection is found
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance) {
        // Find intersections with the plane containing the triangle
        List<GeoPoint> intersections = plane.findGeoIntersections(ray,maxDistance);

        // If there are no intersections with the plane, return null
        if (intersections == null) {
            return null;
        }
        List<Point> triangleIntersections = new ArrayList<Point>();
        for (GeoPoint p : intersections) {
            // Check if the intersection point is inside the triangle
            Vector v1 = vertices.get(0).subtract(ray.getP0());
            Vector v2 = vertices.get(1).subtract(ray.getP0());
            Vector v3 = vertices.get(2).subtract(ray.getP0());

            Vector n1 = v1.crossProduct(v2);
            Vector n2 = v2.crossProduct(v3);
            Vector n3 = v3.crossProduct(v1);

            double t1 = ray.getDir().dotProduct(n1);
            double t2 = ray.getDir().dotProduct(n2);
            double t3 = ray.getDir().dotProduct(n3);

            if ((t1 >0 && t2 > 0 && t3 > 0)||(t1 < 0 && t2 < 0 && t3 <0)) {
                triangleIntersections.add(p.point);
            }
        }
        // If there are no intersections with the triangle, return null
        if (triangleIntersections.isEmpty()) {
            return null;
        }

        return List.of(new GeoPoint(this,triangleIntersections.get(0))) ;
    }
}
