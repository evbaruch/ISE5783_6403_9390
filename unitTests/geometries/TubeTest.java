package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {


    @Test
    void testGetNormal() {
        // Create a new sphere with center (1,1,1) and radius 2.
        Tube tube = new Tube(new Ray(new Point(1,1,1),new Vector(0,0,1)),2);

        // Define some test points.
        Point pointOnSurface1 = new Point(1,2,3);
        Point pointOnSurface2 = new Point(-1,-2,-3);
        Point pointOnSurface3 = new Point(3,2,1);
        Point centerPoint = new Point(1,1,4);

        // ============ Equivalence Partitions Tests ==============

        // Test that the normal vector at a point on the surface of the sphere points
        // outwards from the center of the sphere.
        assertEquals(new Vector(0,1,0).normalize(), tube.getNormal(pointOnSurface1), "GetNormal() test for point on surface 1");
        assertEquals(new Vector(-2,-3,-0).normalize(), tube.getNormal(pointOnSurface2), "GetNormal() test for point on surface 2");
        assertEquals(new Vector(2,1,0).normalize(), tube.getNormal(pointOnSurface3), "GetNormal() test for point on surface 3");

        // =============== Boundary Values Tests ==================

        // Test that an IllegalArgumentException is thrown when calling getNormal()
        // with a point at the center of the sphere.
        assertThrows(IllegalArgumentException.class, () -> tube.getNormal(centerPoint), "GetNormal() test for center point");
    }
}