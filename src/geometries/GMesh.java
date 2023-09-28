package geometries;

import primitives.Mesh;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * Represents a GMesh object that wraps a Mesh object.
 */
public class GMesh extends Geometry {
    private Mesh mesh;

    /**
     * Constructs a GMesh object with the specified Mesh.
     *
     * @param mesh the Mesh object to be wrapped
     * @throws IllegalArgumentException if the mesh has fewer than 3 points
     */
    public GMesh(Mesh mesh) {
        try {
            if (mesh.getPoints().size() < 3) {
                throw new IllegalArgumentException("A mesh must have at least 3 points.");
            }
            this.mesh = mesh;
        } catch (IllegalArgumentException e) {
            // Handle the exception here or rethrow it if needed
            System.out.println("Invalid mesh: " + e.getMessage());
        }
    }


    /**
     * Returns the normal vector of the mesh at the specified point.
     *
     * @param p the Point at which to calculate the normal
     * @return the Vector representing the normal at the specified point
     */
    @Override
    public Vector getNormal(Point p) {
        int index = -1;
        double minDistance = Double.MAX_VALUE;
        List<List<Integer>> triangles = mesh.getTriangles();
        List<Point> points = mesh.getPoints();

        for (int i = 0; i < triangles.size(); i++) {
            List<Integer> triangle = triangles.get(i);
            Point a = points.get(triangle.get(0));
            Point b = points.get(triangle.get(1));
            Point c = points.get(triangle.get(2));

            double distance = p.distanceSquared(a) + p.distanceSquared(b) + p.distanceSquared(c);
            if (distance < minDistance) {
                minDistance = distance;
                index = i;
            }
        }

        List<Integer> triangle = triangles.get(index);
        Point a = points.get(triangle.get(0));
        Point b = points.get(triangle.get(1));
        Point c = points.get(triangle.get(2));

        return calculateNormal(a, b, c);
    }

    /**
     * Calculates and returns the normal vector given three points.
     *
     * @param a the first Point of the triangle
     * @param b the second Point of the triangle
     * @param c the third Point of the triangle
     * @return the Vector representing the normal of the triangle
     */
    private Vector calculateNormal(Point a, Point b, Point c) {
        Vector v1 = a.subtract(b);
        Vector v2 = a.subtract(c);
        return v1.crossProduct(v2).normalize();

    }



    /**
     * Helper method used to find the geometric intersection points between a ray and the mesh.
     * Returns a list of GeoPoint objects representing the valid intersections within the specified maximum distance.
     *
     * @param ray         the Ray object representing the ray of intersection
     * @param maxDistance the maximum distance for a valid intersection
     * @return a list of GeoPoint objects representing the valid intersections, or null if no intersections are found
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> geoPoints = new LinkedList<>();

        List<List<Integer>> triangles = mesh.getTriangles();
        List<Point> points = mesh.getPoints();

        for (List<Integer> triangle : triangles) {
            Point a = points.get(triangle.get(0));
            Point b = points.get(triangle.get(1));
            Point c = points.get(triangle.get(2));

            Point intersection = intersections(ray, maxDistance, a, b, c);
            if (intersection != null) {
                Vector v1 = a.subtract(ray.getP0());
                Vector v2 = b.subtract(ray.getP0());
                Vector v3 = c.subtract(ray.getP0());

                Vector n1 = v1.crossProduct(v2);
                Vector n2 = v2.crossProduct(v3);
                Vector n3 = v3.crossProduct(v1);

                double t1 = ray.getDir().dotProduct(n1);
                double t2 = ray.getDir().dotProduct(n2);
                double t3 = ray.getDir().dotProduct(n3);

                if ((t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0)) {
                    geoPoints.add(new GeoPoint(this, intersection));
                }
            }
        }

        return geoPoints.isEmpty() ? null : geoPoints;
    }


    /**
     * @param ray
     * @return
     */
    @Override
    protected GeoPoint findClosesGeoIntersectionsHelper(Ray ray) {
        List<List<Integer>> triangles = mesh.getTriangles();
        List<Point> points = mesh.getPoints();


        for (List<Integer> triangle : triangles) {

            Point a = points.get(triangle.get(0));
            Point b = points.get(triangle.get(1));
            Point c = points.get(triangle.get(2));

            Point intersection = intersections(ray, Double.MAX_VALUE, a, b, c);
            if (intersection != null) {
                Vector v1 = a.subtract(ray.getP0());
                Vector v2 = b.subtract(ray.getP0());
                Vector v3 = c.subtract(ray.getP0());

                Vector n1 = v1.crossProduct(v2);
                Vector n2 = v2.crossProduct(v3);
                Vector n3 = v3.crossProduct(v1);

                double t1 = ray.getDir().dotProduct(n1);
                double t2 = ray.getDir().dotProduct(n2);
                double t3 = ray.getDir().dotProduct(n3);

                if ((t1 > 0 && t2 > 0 && t3 > 0) || (t1 < 0 && t2 < 0 && t3 < 0)) {
                    return new GeoPoint(this, intersection);
                }
            }
        }

        return  null;
    }


    /**
     * Calculates the intersection point between a ray and a triangle defined by three points.
     * Returns the intersection point if it exists within the valid range, or null otherwise.
     *
     * @param ray         the Ray object representing the ray of intersection
     * @param maxDistance the maximum distance for a valid intersection
     * @param a           the first Point of the triangle
     * @param b           the second Point of the triangle
     * @param c           the third Point of the triangle
     * @return the intersection Point if it exists within the valid range, or null otherwise
     */
    private Point intersections(Ray ray, double maxDistance, Point a, Point b, Point c) {
        Vector n = calculateNormal(a, b, c);
        Vector v = ray.getDir();
        Point p0 = ray.getP0();

        double nv = n.dotProduct(v);
        if (isZero(nv)) {
            return null; // Ray is parallel to the plane
        }

        double t = alignZero(n.dotProduct(a.subtract(p0)) / nv);
        if (alignZero(maxDistance - t) <= 0 || t <= 0 || !Double.isFinite(t)) {
            return null; // Intersection parameter is out of range or invalid
        }

        return ray.getPoint(t);
    }

}
