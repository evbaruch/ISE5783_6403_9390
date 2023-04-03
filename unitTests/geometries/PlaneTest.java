package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Plane with normal pointing along z-axis
        Point q0 = new Point(1, 2, 3);
        Vector normal = new Vector(0, 0, 1);
        Plane planeEP = new Plane(q0, normal);


        // ensure there are no exceptions
        assertDoesNotThrow(() -> planeEP.getNormal(new Point(0, 0, 0)), "");

        // generate the test result
        Vector result = planeEP.getNormal(new Point(0, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // ensure the result matches the expected normal
        assertEquals(normal, result, "Plane's normal is incorrect");

        // ============ Boundary Value Tests ==============
        // TC02: Plane with normal pointing along x-axis
        q0 = new Point(1, 2, 3);
        normal = new Vector(1, 0, 0);
        Plane planeBV = new Plane(q0, normal);

        // ensure there are no exceptions
        assertDoesNotThrow(() -> planeBV.getNormal(new Point(0, 0, 0)), "");

        // generate the test result
        result = planeBV.getNormal(new Point(0, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Plane's normal is not a unit vector");

        // ensure the result matches the expected normal
        assertEquals(normal, result, "Plane's normal is incorrect");
    }

}