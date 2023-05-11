package primitives;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RayTest {
    @Test
    void testFindClosestPoint() {
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(1, 1, 1));
        Point A = new Point(2, 2, 2);
        Point B = new Point(3, 3, 3);
        Point C = new Point(4, 4, 4);

        // =============== Boundary Values Tests ==================
        List<Point> points = new LinkedList<>();
        assertNull(ray.findClosestPoint(points), "Expected closest point to be null when the list is empty");

        points.add(A);
        points.add(B);
        points.add(C);
        assertEquals(A, ray.findClosestPoint(points), "Expected A to be the closest point");

        points.clear();
        points.add(C);
        points.add(B);
        points.add(A);
        assertEquals(A, ray.findClosestPoint(points), "Expected A to be the closest point when points are in a different order");

        // ============ Equivalence Partitions Tests ==============
        points.clear();
        points.add(C);
        points.add(A);
        points.add(B);
        assertEquals(A, ray.findClosestPoint(points), "Expected A to be the closest point among the given points");
    }




}
