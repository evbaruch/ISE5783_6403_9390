package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for geometries.Triangle claas
 * @author evyatar
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(Point)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Triangle lying on the xy-plane
        Point a = new Point(0, 0, 0);
        Point b = new Point(1, 0, 0);
        Point c = new Point(0, 1, 0);
        Triangle triangleEP = new Triangle(a, b, c);

        // generate the test result
        Vector result = triangleEP.getNormal(new Point(0, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");

        // ensure the result matches the expected normal pointing along z-axis
        assertEquals(new Vector(0, 0, 1), result, "Triangle's normal is incorrect");

        // ============ Boundary Value Tests ==============

        // TC02: Triangle lying on the yz-plane
        a = new Point(0, 0, 0);
        b = new Point(0, 1, 0);
        c = new Point(0, 0, 1);
        Triangle triangleBV = new Triangle(a, b, c);

        // Generate the test result
        result = triangleBV.getNormal(new Point(0, 0, 0));

        // Ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector in TC02");

        // Ensure the result is orthogonal to all the edges
        assertTrue(isZero(result.dotProduct(b.subtract(a))), "Triangle's normal is not orthogonal to edge AB in TC02");
        assertTrue(isZero(result.dotProduct(c.subtract(b))), "Triangle's normal is not orthogonal to edge BC in TC02");
        assertTrue(isZero(result.dotProduct(a.subtract(c))), "Triangle's normal is not orthogonal to edge CA in TC02");
    }
    @Test
    void findIntsersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01:  Testing for ray-triangle intersection where the ray intersects the triangle.
        Point p1 = new Point(-2, 0, 0);
        Point p2 = new Point(0, 2, 0);
        Point p3 = new Point(2, 0, 0);
        Triangle triangle = new Triangle(p1, p2, p3);

        Point p = new Point(0, 0, 1);
        Vector v = new Vector(0, 1, -1);
        Ray ray = new Ray(p, v);

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 1, 0));

        List<Point> result = triangle.findIntersections(ray);

        assertEquals(expected, result, "Failed to find intersection point");

        // TC02: Testing for ray-triangle intersection where the intersection point is not within the triangle, but on one of its sides.
        p = new Point(1, 2, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(p, v);

        expected.clear();

        result = triangle.findIntersections(ray);

        assertNull(result, "Ray does not intersect the triangle but the method returned an intersection point");

        // TC03:  Testing for ray-triangle intersection where the intersection point is between the lines of the triangle's sides but outside of it.
        p = new Point(4, -1, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(p, v);

        expected.clear();

        result = triangle.findIntersections(ray);

        assertNull(result, "Ray is parallel to the triangle but the method returned an intersection point");

        // ============ Boundary Value Tests ==============
        // TC04: Testing for ray-triangle intersection where the intersection point is on the junction of two sides of the triangle.
        p = new Point(2, 0, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(p, v);

        expected.clear();

        result = triangle.findIntersections(ray);

        assertNull(result, "Failed to find intersection point");

        // TC05: Testing for ray-triangle intersection where the intersection point is on one of the sides of the triangle.
        p = new Point(1, 0, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(p, v);

        expected.clear();

        result = triangle.findIntersections(ray);

        assertNull(result, "Ray is perpendicular to the triangle but the method returned an intersection point");

        // TC06: Testing for ray-triangle intersection where the intersection point is on the extension of one of the triangle's sides, outside of it.
        p = new Point(3, -1, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(p, v);

        expected.clear();

        result = triangle.findIntersections(ray);

        assertNull(result, "Ray starts within the triangle and points away from it but the method returned an intersection point");
    }

}