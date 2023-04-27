package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for geometries.Cylinder claas
 * @author evyatar
 */
class CylinderTest {
    /**
     * Test method for {@link geometries.Cylinder#getNormal(Point)}
     */
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
        assertEquals(new Vector(0,0,1),cylinder.getNormal(test2),"GetNormal() test 2");

        // =============== Boundary Values Tests ==================
        assertEquals(new Vector(0,0,1),cylinder.getNormal(test3),"GetNormal() test 3");

    }

    @Test
    void findIntsersections() {
    }
}
class CylinderTests {

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        Cylinder cyl = new Cylinder(new Ray(new Point(0, 0, 1), new Vector(0, 1, 0)),1.0, 1d);

        // ============ Equivalence Partitions Tests ==============
        // TC01: Point at a side of the cylinder
        assertEquals(new Vector(0, 0, 1), cyl.getNormal(new Point(0, 0.5, 2)), "Bad normal to cylinder");

        // TC02: Point at a 1st base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1.5)), "Bad normal to lower base of cylinder");

        // TC03: Point at a 2nd base of the cylinder
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 0.5)), "Bad normal to upper base of cylinder");

        // =============== Boundary Values Tests ==================
        //  TC11: Point at the center of 1st base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 1)), "Bad normal to center of lower base");

        // TC12: Point at the center of 2nd base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 1)), "Bad normal to center of upper base");

        // TC13: Point at the edge with 1st base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 0, 2)), "Bad normal to edge with lower base");

        // TC14: Point at the edge with 2nd base
        assertEquals(new Vector(0, 1, 0), cyl.getNormal(new Point(0, 1, 2)), "Bad normal to edge with upper base");

    }

}