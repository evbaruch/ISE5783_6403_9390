package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CylinderTest {
    @Test
    void testGetNormal() {
        // Create a new sphere with center (1,1,1) and radius 2.
        Cylinder cylinder = new Cylinder(new Ray(new Point(1,1,1),new Vector(0,0,1)),2,3);

        // Define some test points.
        Point test1 = new Point(1,2,3);
        Point test2 = new Point(-1,-2,-3);
        Point test3 = new Point(1,2,4);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(new Vector(0,1,0),cylinder.getNormal(test1),"GetNormal() test 1");
        assertEquals(new Vector(0,0,-1),cylinder.getNormal(test2),"GetNormal() test 2");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(0,0,1),cylinder.getNormal(test3),"GetNormal() test 3");

    }
}