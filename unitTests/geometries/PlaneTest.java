package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for geometries.Plane claas
 * @author evyatar
 */
class PlaneTest {

    /**
     * Test method for {@link geometries.Plane#getNormal(Point)}
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Plane with normal pointing along z-axis
        Point q1 = new Point(1, 2, 3);
        Point q2 = new Point(2, 1, 3);
        Point q3 = new Point(1, 1, 3);
        Plane plane = new Plane(q1, q2, q3);

        // generate the test result
        Vector result = plane.getNormal();

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // ensure the result matches the expected normal
        assertEquals(new Vector(0,0,-1), result, "Plane's normal is incorrect");

        // ============ Boundary Value Tests ==============
        // TC02: Plane with normal pointing along x-axis


        // generate the test result
        result = plane.getNormal();

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // ensure the result matches the expected normal
        assertEquals(new Vector(0,0,-1), result, "Plane's normal is incorrect");
    }

//    @Test
//    void findIntsersections() {
//
//        Point A = new Point(1,1,1);
//        Vector n = new Vector(1,2,3);
//        Plane plane = new Plane(A,n);
//
//        Point P = new Point(3,2,1);
//        Vector v = new Vector(-3,-2,-1);
//        Ray ray = new Ray(P,v);
//
//        List<Point> result = new ArrayList<>();
//
//        result.add(new Point(1.8,1.2,0.6));
//        assertEquals(result, plane.findIntsersections(ray) ,"intersection does not operate properly" );
//
//
//        P = new Point(1.8,1.2,0.6);
//        v = new Vector(2,-4,2);
//        ray = new Ray(P,v);
//        result.clear();
//        result.add(new Point(1.8,1.2,0.6));
//        result.add(new Point(1.8,1.2,0.6));
//        assertEquals(result, plane.findIntsersections(ray) ,"intersection does not operate properly" );
//    }
    @Test
    void findIntsersections() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects the plane
        Point A = new Point(0, 0, 1);
        Vector n = new Vector(0, 0, 1);
        Plane plane = new Plane(A, n);

        Point P = new Point(0, 0, 0);
        Vector v = new Vector(0, 0, 1);
        Ray ray = new Ray(P, v);

        List<Point> expected = new ArrayList<>();
        expected.add(new Point(0, 0, 1));

        List<Point> result = plane.findIntsersections(ray);

        assertEquals(expected, result, "Failed to find intersection point");

        // TC02: Ray is parallel to the plane
        P = new Point(0, 0, 0);
        v = new Vector(0, 1, 0);
        ray = new Ray(P, v);

        expected.clear();

        result = plane.findIntsersections(ray);

        assertTrue(result == null, "Ray is parallel to the plane but the method returned an intersection point");

        // ============ Boundary Value Tests ==============
        // TC03: Ray is contained within the plane
        A = new Point(1, 1, 1);
        n = new Vector(1, 0, 0);
        plane = new Plane(A, n);

        P = new Point(1, 2, 1);
        v = new Vector(0, 0, 1);
        ray = new Ray(P, v);

        expected.clear();
        result = plane.findIntsersections(ray);

        assertNull(result, "Failed to find intersection point");

        // TC04: Ray is perpendicular to the plane and intersecting it
        A = new Point(0, 0, 1);
        n = new Vector(0, 0, 1);
        plane = new Plane(A, n);

        P = new Point(1, 1, 1);
        v = new Vector(0, 0, -1);
        ray = new Ray(P, v);

        expected.clear();
        expected.add(new Point(1, 1, 1));

        result = plane.findIntsersections(ray);

        assertTrue(result == null, "Ray is perpendicular to the plane but the method returned an intersection point");


        // TC05: Ray starts after the plane and does not intersect
        P = new Point(0, 0, 2);
        v = new Vector(0, 0, 1);
        ray = new Ray(P, v);

        expected.clear();
        result = plane.findIntsersections(ray);

        assertTrue(result == null, "Ray starts after the plane but the method returned an intersection point");
    }

}