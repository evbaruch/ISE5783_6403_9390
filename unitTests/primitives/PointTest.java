package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit test for primitives.Point claas
 * @author yehuda
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#equals(Object)}
     */
    @Test
    void testEquals() {
    }

    /**
     * Test method for {@link primitives.Point#add(Vector)}.
     */
    @Test
    void testAdd() {
        Point v1 = new Point(1, 2, 3);
        Point v2 = new Point(-2, -4, -6);
        Point v3 = new Point(0, 3, -2);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Point(0,0,0),
                v1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");

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
        // =============== Boundary Values Tests ==================
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
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
    }
}