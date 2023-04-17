package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit test for primitives.Point claas
 * @author evyatar
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#equals(Object)}
     */
    @Test
    void testEquals() {
        Point v1 = new Point(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that the equals() method returns true for two identical points.
        assertTrue(v1.equals(new Point(1,2,3)),"ERROR: Point.equals does not work correctly ");
    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        Point v1 = new Point(1, 2, 3);
        // =============== Boundary Values Tests ==================
        //TC01: Test that adding a vector to a point with coordinates (1, 2, 3) and a vector with coordinates (-1, -2, -3) returns the point (0, 0, 0).
        assertEquals(
                new Point(0,0,0),
                v1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
        // ============ Equivalence Partitions Tests ==============
        //TC02: Test that adding a vector to a point with coordinates (1, 2, 3) and a vector with coordinates (-2, -4, -6) returns the point (-1, -2, -3).
        assertEquals(
                new Point(-1,-2,-3),
                v1.add(new Vector(-2,-4,-6)),
                "ERROR: Point + Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test that subtracting the point (-2, -4, -6) from the point (1, 2, 3) returns the vector (3, 6, 9).
        assertEquals(
                new Vector(3, 6, 9),
                p1.subtract(p2),
                "ERROR: Point - Point does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that calculating the squared distance between the points (1, 2, 3) and (-2, -4, -6) returns the value 94.
        assertEquals(
                (-2-1)*(-2-1)+(-4-2)*(-4-2)+(-6-3)*(-6-3),
                p1.distanceSquared(p2),
                "ERROR: Point.distanceSquared does not work correctly ");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        Point p1 = new Point(1, 2, 3);
        Point p2 = new Point(-2, -4, -6);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Test that calculating the distance between the points (1, 2, 3) and (-2, -4, -6) returns the value sqrt(94).
        assertEquals(
                Math.sqrt(p1.distanceSquared(p2)),
                p1.distance(p2),
                "ERROR: Point.distance does not work correctly ");
    }
}