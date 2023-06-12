package primitives;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;

/**

 Represents a ray in three-dimensional space, defined by a starting point and a direction vector.
 */
public class Ray {
    private final Point p0;
    private final Vector dir;

    /**
     Constructs a new ray with the given starting point and direction.
     @param p0 The starting point of the ray.
     @param dir The direction vector of the ray.
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return Objects.equals(p0, ray.p0) && Objects.equals(dir, ray.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    /**
     Returns the starting point of the ray.
     @return The starting point of the ray.
     */
    public Point getP0() {
        return p0;
    }

    /**
     Returns the direction vector of the ray.
     @return The direction vector of the ray.
     */
    public Vector getDir() {
        return dir;
    }

    /**

     Computes the point on the ray at a given parameter t.
     @param t The parameter value determining the point along the ray.
     @return The point on the ray at parameter t.
     */
    public Point getPoint(double t)
    {
        return p0.add(dir.scale(t));
    }


    /**
     * Finds the closest point from a given list of points to a reference point.
     *
     * @param points The list of points to search from.
     * @return The closest point to the reference point, or null if the list is empty.
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null) {
            return null;
        }
        double closestDistance = Double.MAX_VALUE;
        GeoPoint closestPoint = null;

        for (GeoPoint point : points) {
            double distance = p0.distanceSquared(point.point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }

    /**

     Finds the closest point from a given list of points to a reference point.
     @param points The list of points to search from.
     @return The closest point to the reference point, or null if the list is empty.
     */

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

}
