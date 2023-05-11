package primitives;

import java.util.List;
import java.util.Objects;

public class Ray {
    private final Point p0;
    private final Vector dir;

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

    public Point getP0() {
        return p0;
    }

    public Vector getDir() {
        return dir;
    }

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
    public Point findClosestPoint(List<Point> points) {
        if (points.isEmpty()) {
            return null;
        }

        double closestDistance = Double.MAX_VALUE;
        Point closestPoint = null;

        for (Point point : points) {
            double distance = p0.distanceSquared(point);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }
}
