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

    Camera camera = new Camera(new Point(1,1,1),new Vector(1,0,0),new Vector(0,0,1))
                    .setVPSize(3,3)
                    .setVPDistance(10);

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
        Sphere sphere = new Sphere(new Point(20,1,1),2);

        assertEquals(10, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        sphere = new Sphere(new Point(1,1,1),2);

        assertEquals(9, rays(camera,sphere),"Failed to find all intersection point with the sphere");

        sphere = new Sphere(new Point(-1,1,1),2);

        assertEquals(0, rays(camera,sphere),"Failed to find all intersection point with the sphere");


    }

    @Test
    void testTriangle() {
        Triangle triangle = new Triangle(new Point(20,5,5),new Point(20,0,0),new Point(20,5,0));

        assertEquals(1, rays(camera,triangle),"Failed to find all intersection point with the triangle");

        triangle = new Triangle(new Point(-1,5,5),new Point(-1,0,0),new Point(-1,5,0));

        assertEquals(0, rays(camera,triangle),"Failed to find all intersection point with the triangle");


    }

    @Test
    void testPlane() {
        Plane plane = new Plane(new Point(20,0,0), new Vector(1,0,0));

        assertEquals(9, rays(camera,plane),"Failed to find all intersection point with the plane");

        plane = new Plane(new Point(-1,0,0), new Vector(1,0,0));

        assertEquals(0, rays(camera,plane),"Failed to find all intersection point with the plane");
    }
}
