package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void findIntsersections() {
    }
}