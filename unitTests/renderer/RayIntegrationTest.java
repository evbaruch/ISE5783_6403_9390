package renderer;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class RayIntegrationTest {
    private static final int NX = 3;
    private static final int NY = 3;


    /**
     Returns the number of intersection points between rays cast from a camera and a geometry object.
     @param camera the Camera object used to cast the rays
     @param geometry the Geometry object to intersect with the rays
     @return the number of intersection points found
     */
    int rays(Camera camera, Geometry geometry){
        List<Point> intersects = new LinkedList<Point>();// Initialize an empty list to store intersection points.
        // Iterate over all the pixels in the camera's view plane.
        for (int i = 0; i < NX; i++){

            for (int j = 0; j < NY; j++){
                // Construct a ray from the camera through the current pixel.
                Ray ray = camera.constructRay(NX, NY, i, j);
                // Find all intersection points between the ray and the geometry.
                List<Point> points = geometry.findIntersections(ray);
                if(points != null){// If there are any intersection points, add them to the list.
                    intersects.addAll(points);
                }
            }

        }

        // Return the total number of intersection points found.
        return intersects.size();
    }

    @Test
    void testSphere() {

        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);

        Sphere sphere = new Sphere(new Point(0,0,-3),1);

        // ray intersect the sphere in 5 points
        assertEquals(2, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        camera = new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);
        sphere = new Sphere(new Point(0,0,-2.5),2.5);

        // camara ray start in the sphere and intersect
        assertEquals(18, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        camera = new Camera(new Point(0,0,0.5),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);
        sphere = new Sphere(new Point(0,0,-2),2);

        // ray start after the sphere - does not intersect
        assertEquals(10, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);
        sphere = new Sphere(new Point(0,0,-2),4);

        // ray start after the sphere - does not intersect
        assertEquals(9, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);
        sphere = new Sphere(new Point(0,0,1),0.5);

        // ray start after the sphere - does not intersect
        assertEquals(0, rays(camera,sphere),"Failed to find all intersection point with the sphere");


    }

    /**
     * Tests the `rays` method with different input values of a `Triangle` object.
     *
     * - TC01: Ray intersects the triangle.
     * - TC02: Ray does not intersect the triangle.
     *
     * @see Geometries#findIntersections(Ray)
     */
    @Test
    void testTriangle() {

        // TC01: Ray intersects the triangle
        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);

        Triangle triangle = new Triangle(new Point(0,-1,-2),new Point(1,-1,-3),new Point(-1,-1,-2));
        int expected = 0;

        assertEquals(expected, rays(camera,triangle),"Failed to find all intersection point with the triangle");

        // TC02: Ray does not intersect the triangle
        triangle = new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        expected = 2;
        assertEquals(expected, rays(camera,triangle),"Failed to find all intersection point with the triangle");


    }

    /**
     * Tests the intersection between a camera ray and a plane.
     * The method checks different cases of intersection, including when the ray intersects the plane,
     * when the ray is parallel to the plane, and when the ray does not intersect the plane.
     */
    @Test
    void testPlane() {


        // TC01:
        Camera camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                       .setVPSize(3,3)
                        .setVPDistance(1);
        Point A = new Point(0, 0, -1);
        Vector n = new Vector(0, 0, 2);
        Plane plane = new Plane(A, n);

        int expected = 9;

        assertEquals(expected, rays(camera,plane),"Failed to find all intersection point with the plane");

        // TC02:
        camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);


        A = new Point(0, 0, -1);
        n = new Vector(0, 2, 4);
        plane = new Plane(A, n);
        expected = 9;

        assertEquals(expected, rays(camera,plane),"Failed to find all intersection point with the plane");

        // TC03:
        camera = new Camera(new Point(0,0,0),new Vector(0,0,-1),new Vector(0,-1,0))
                .setVPSize(3,3)
                .setVPDistance(1);

        A = new Point(0, 0, -1);
        n = new Vector(0, 2, 2);
        plane = new Plane(A, n);
        expected = 6;

        assertEquals(expected, rays(camera,plane),"Failed to find all intersection point with the plane");


    }
}
