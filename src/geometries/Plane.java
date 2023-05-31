package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

public class Plane extends Geometry{

    private final Point q0;

    private final Vector normal;

    /**
     * Constructs a plane by a point on the plane and its normal vector.
     *
     * @param q0     A point on the plane
     * @param normal The normal vector to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * Constructs a plane by three non-collinear points.
     *
     * @param a The first point
     * @param b The second point
     * @param c The third point
     */
    public Plane(Point a, Point b, Point c) {
        q0 = a;
        Vector v1 =  a.subtract(b);
        Vector v2 =  a.subtract(c);
        normal = v1.crossProduct(v2).normalize();
    }

    /**
     * Returns the normal vector of the plane.
     *
     * @return The normal vector
     */

    public Vector getNormal() {
        return this.normal;
    }


    /**
     * Returns the normal vector of the plane.
     *
     * @param p The point on the plane (unused for a plane)
     * @return The normal vector of the plane
     */
    @Override
    public Vector getNormal(Point p) {
        return this.normal;
    }


    /**
     * Helper method to find the intersection points between a ray and the plane.
     *
     * @param ray         The ray for intersection
     * @param maxDistance The maximum allowed distance of intersection
     * @return A list of GeoPoint objects representing the intersection points, or null if no intersection
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray , double maxDistance) {
        Point result = null;

        // Get the triangle's normal,
        Vector n = getNormal();
        // ray's direction vector,
        Vector v = ray.getDir();
        // and ray's starting point.
        Point p0 = ray.getP0();
        // Check if the ray is not parallel to the plane.
        double nv = n.dotProduct(v);
        if (!isZero(nv)){
            // Calculate the intersection point between the ray and the plane.
            double t = alignZero( n.dotProduct(q0.subtract(p0)) / nv);

            // Check if the intersection parameter is within the valid range.
            if(alignZero(maxDistance - t) > 0){
                // Check if the intersection point is in front of the starting point of the ray.
                if (t > 0 && Double.isFinite(t)) {
                    // Calculate the intersection point coordinates and return it
                    result = ray.getPoint(t);
                }
            }
        }

        if(result == null) {
            return null;
        }
        // Return the intersection point or null if no intersection exists.
        return List.of(new GeoPoint(this,result)) ;
    }

}
