package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Point p1 = new Point(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Point(0,0,0),
                p1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        Point p1 = new Point(1, 2, 3);
        // =============== Boundary Values Tests ==================
        assertEquals(
                new Point(0,0,0),
                p1.subtract(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
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