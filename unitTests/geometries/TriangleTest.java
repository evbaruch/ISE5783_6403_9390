package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;
import static primitives.Util.isZero;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Triangle lying on the xy-plane
        Point a = new Point(0, 0, 0);
        Point b = new Point(1, 0, 0);
        Point c = new Point(0, 1, 0);
        Triangle triangleEP = new Triangle(a, b, c);

        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangleEP.getNormal(new Point(0, 0, 0)), "");

        // generate the test result
        Vector result = triangleEP.getNormal(new Point(0, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector");

        // ensure the result matches the expected normal pointing along z-axis
        assertEquals(new Vector(0, 0, 1), result, "Triangle's normal is incorrect");

        // ============ Boundary Value Tests ==============

        // TC02: Triangle lying on the yz-plane
        a = new Point(0, 0, 0);
        b = new Point(0, 1, 0);
        c = new Point(0, 0, 1);
        Triangle triangleBV = new Triangle(a, b, c);

        // Ensure there are no exceptions
        assertDoesNotThrow(() -> triangleBV.getNormal(new Point(0, 0, 0)), "Unexpected exception in TC02");

        // Generate the test result
        result = triangleBV.getNormal(new Point(0, 0, 0));

        // Ensure |result| = 1
        assertEquals(1, result.length(), 0.00000001, "Triangle's normal is not a unit vector in TC02");

        // Ensure the result is orthogonal to all the edges
        assertTrue(isZero(result.dotProduct(b.subtract(a))), "Triangle's normal is not orthogonal to edge AB in TC02");
        assertTrue(isZero(result.dotProduct(c.subtract(b))), "Triangle's normal is not orthogonal to edge BC in TC02");
        assertTrue(isZero(result.dotProduct(a.subtract(c))), "Triangle's normal is not orthogonal to edge CA in TC02");
    }
}