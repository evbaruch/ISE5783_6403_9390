package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

/**
 * Unit test for geometries.Sphere claas
 * @author evyatar
 */
class SphereTest {

    /**
     * Test method for {@link geometries.Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // Create a new sphere with center (1,1,1) and radius 2.
        Sphere sphere = new Sphere(new Point(1,1,1),2);

        // Define some test points.
        Point pointOnSurface1 = new Point(1,2,3);
        Point pointOnSurface2 = new Point(1,2,2);
        Point pointOnSurface3 = new Point(1,1,3);
        Point centerPoint = new Point(1,1,1);

        // ============ Equivalence Partitions Tests ==============

        // Test that the normal vector at a point on the surface of the sphere points
        // outwards from the center of the sphere.
        assertEquals(new Vector(0,1,2).normalize(), sphere.getNormal(pointOnSurface1), "GetNormal() test for point on surface 1");
        assertEquals(new Vector(0,1,1).normalize(), sphere.getNormal(pointOnSurface2), "GetNormal() test for point on surface 2");
        assertEquals(new Vector(0,0,2).normalize(), sphere.getNormal(pointOnSurface3), "GetNormal() test for point on surface 3");

        // =============== Boundary Values Tests ==================

        // Test that an IllegalArgumentException is thrown when calling getNormal()
        // with a point at the center of the sphere.
        assertThrows(IllegalArgumentException.class, () -> sphere.getNormal(centerPoint), "GetNormal() test for center point");
    }

    /**
     * Test method for {@link }.
     */
    @Test
    public void testFindIntersections() {
        Sphere sphere = new Sphere(new Point (1, 0, 0),1d);
        Point p1, p2;
        List<Point> result;

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        result = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0),"p1 Does not give what is expected");
        assertEquals(p2, result.get(1),"p2 Does not give what is expected");

        // TC03: Ray starts inside the sphere (1 point)
        p2 = new Point(1.6851646544245034, 0.7283882181415011, 0);
        result = sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(3, 1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0),"p1 Does not give what is expected");

        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(3, 1, 0))),"ERROR: Ray starts after the sphere");

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        p2 = new Point(1.333333333333333, 0.6666666666666669, 0.6666666666666669);
        result = sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(-1, 1, 1)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0),"p1 Does not give what is expected");

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 1))),"ERROR: Ray starts at sphere and goes outside");

        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        p1 = new Point(0.18350341907227397, -0.408248290463863, -0.408248290463863);
        p2 = new Point(1.8164965809277271, 0.408248290463863, 0.408248290463863);
        result = sphere.findIntersections(new Ray(new Point(-1, -1, -1), new Vector(2, 1, 1)));
        assertEquals(2, result.size(), "Wrong number of points");
        assertEquals(p1, result.get(0),"p1 Does not give what is expected");
        assertEquals(p2, result.get(1),"p2 Does not give what is expected");

        // TC14: Ray starts at sphere and goes inside (1 points)
        p2 = new Point(1, -1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, -1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0),"p1 Does not give what is expected");

        // TC15: Ray starts inside (1 points)
        p2 = new Point(1, -1, 0);
        result = sphere.findIntersections(new Ray(new Point(1, 0.5, 0), new Vector(0, -1, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(p2, result.get(0),"p1 Does not give what is expected");

        // TC16: Ray starts at the center (1 points)
//        p2 = new Point(1, -1, 0);
//        result = sphere.findIntsersections(new Ray(new Point(1, 0, 0), new Vector(0, -1, 0)));
//        assertEquals(1, result.size(), "Wrong number of points");
//        assertEquals(p2, result.get(0),"p1 Does not give what is expected");

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, -1, 0), new Vector(0, -1, 0))),"ERROR: Ray starts at sphere and goes outside");

        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, -2, 0), new Vector(0, -1, 0))),"ERROR: Ray starts after sphere");

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, -1), new Vector(0, 0, 1))),"ERROR: Ray starts before the tangent point");

        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 0, 1))),"ERROR: Ray starts at the tangent point");

        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 0, 1))),"ERROR: Ray starts after the tangent point");

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(1, 2, 0), new Vector(0, 0, 1))),"ERROR: Ray's line is outside");
    }
}