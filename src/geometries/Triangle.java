package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Triangle extends Polygon {
    public Triangle(Point a, Point b, Point c) {
        super(a,b,c);
    }

    /**
     * The implementation is using the option 3 approach for
     * computing the intersection of a ray with a triangle.
     *
     * The first step of the algorithm is to compute the intersection
     * of the ray with the plane of the triangle using the findIntsersections()
     * method of the plane object.
     *
     * Then, if there is an intersection,
     * the implementation checks if the intersection point is
     * inside the triangle using the barycentric coordinates technique.
     * This technique is based on the idea that a point is inside a triangle
     * if it is on the same side of all of its edges.
     *
     * The implementation computes the dot products of the vectors
     * between the intersection point and each vertex of the triangle.
     * If all of these dot products have the same sign, then the point is
     * inside the triangle and the method returns the list of intersection points.
     * Otherwise, the method returns null.
     *
     * Overall, this implementation is a lightweight and efficient way to compute the
     * intersection of a ray with a triangle.
     * @param ray
     * @return
     */
    @Override
    public List<Point> findIntsersections(Ray ray) {

        List<Point> intersections = plane.findIntsersections(ray);
        if (intersections == null) {
            return null;
        }
        Point p = intersections.get(0);
        Vector v1 = vertices.get(0).subtract(p);
        Vector v2 = vertices.get(1).subtract(p);
        Vector v3 = vertices.get(2).subtract(p);
        double d1 = v1.dotProduct(v2);
        double d2 = v2.dotProduct(v3);
        double d3 = v3.dotProduct(v1);
        if (d1 > 0 && d2 > 0 && d3 > 0 || d1 < 0 && d2 < 0 && d3 < 0) {
            return intersections;
        }
        return null;
    }
}
