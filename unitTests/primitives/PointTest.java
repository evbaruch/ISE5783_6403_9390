package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test for primitives.Point claas
 * @author yehuda
 */
class PointTest {

    /**
     *
     */
    @Test
    void testTestEquals() {
    }

    @Test
    void testAdd() {
        Point p1 = new Point(1, 2, 3);
        assertEquals(
                new Point(0,0,0),
                p1.add(new Vector(-1,-2,-3)),
                "ERROR: Point + Vector does not work correctly");
    }

    @Test
    void testSubtract() {
    }

    @Test
    void testDistanceSquared() {
    }

    @Test
    void testDistance() {
    }
}