package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static primitives.Util.isZero;

/**
 * Unit test for primitives.Point claas
 * @author evyatar
 */
class VectorTest {

    /**
     * Test method for {@link primitives.Vector#equals(Object)}.
     */
    @Test
    void testEquals() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============

        //Checks for equality of members between two vectors
        assertTrue(v1.equals(new Vector(1,2,3)),"Equals() 'false' positive numbers wrong result");
        //Checking the equality of members between two vectors with negative numbers
        assertTrue(v2.equals(new Vector(-1,-2,-3)),"Equals() 'false' negative numbers wrong result");
    }

    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============
        // Test addition of two positive vectors
        assertEquals(new Vector(2,4,6),v1.add(new Vector(1,2,3)),"Add() Adding two positive vectors does not work");
        // Test addition of two negative vectors
        assertEquals(new Vector(-2,-4,-6),v2.add(new Vector(-1,-2,-3)),"Add() Adding two negative vectors does not work");

        // =============== Boundary Values Tests ==================
        //Checks for an exception being thrown in the zero case vector
        assertThrows(IllegalArgumentException.class, () -> v1.add(new Vector(-1,-2,-3)), "Add() Connecting two opposite vectors does not throw an exception");
    }
    
    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    void testScale() {
        Vector v1 = new Vector(1,2,3);

        // ============ Equivalence Partitions Tests ==============
        //checking the Scalar multiplication of a positive number
        assertEquals(new Vector(2,4,6),v1.scale(2),"Scale() Scalar multiplication of a positive number");
        //checking the Scalar multiplication of a negative number
        assertEquals(new Vector(-2,-4,-6),v1.scale(-2),"Scale() Scalar multiplication of a negative number");

        // =============== Boundary Values Tests ==================
        //checking the zero scalar product
        //assertThrows(IllegalArgumentException.class, () -> v1.scale(0),"Scale() zero scalar product");
    }
    
    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(v1.length() * v2.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
        // TC02: Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v2)), "crossProduct() result is not orthogonal to 2nd operand");
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-productof co-lined vectors
        Vector v3 = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v3),
                "crossProduct() for parallel vectors does not throw an exception");
    }

    /**
     * Test method for {@link Vector#lengthSquared()}.
     */
    @Test
    void testLengthSquared() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(14.0,v1.lengthSquared(),"LengthSquared() The distance is a positive number. Invalid");
        assertEquals(14.0,v2.lengthSquared(),"LengthSquared() The distance is a negative number. Invalid");
    }

    /**
     * Test method for {@link Vector#length()}.
     */
    @Test
    void testLength() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(3.7416573867739413,v1.length(),"Length() The distance is a positive number. Invalid");
        assertEquals(3.7416573867739413,v2.length(),"Length() The distance is a negative number. Invalid");
    }

    /**
     * Test method for {@link Vector#normalize()}.
     */
    @Test
    void testNormalize() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(-1,-2,-3);

        // ============ Equivalence Partitions Tests ==============

        assertEquals(new Vector(1/3.7416573867739413,2/3.7416573867739413,3/3.7416573867739413),v1.normalize(),"Normalize()");
        assertEquals(new Vector(-1/3.7416573867739413,-2/3.7416573867739413,-3/3.7416573867739413),v2.normalize(),"Normalize()");
    }

    /**
     * Test method for {@link Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void testDotProduct() {
        Vector v1 = new Vector(1,2,3);
        Vector v2 = new Vector(4,5,6);
        Vector v3 = new Vector(0,-3,2);

        assertEquals(32,v1.dotProduct(v2),"DotProduct()");
        assertEquals(0,v1.dotProduct(v3),"DotProduct()");
    }
}