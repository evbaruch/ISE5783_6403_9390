package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**

 The RayTracerBasic class is an implementation of the RayTracerBase class, which
 provides a basic ray tracing algorithm.
 @author evyatar
 @version 1.0
 */
public class RayTracerBasic extends RayTracerBase {

    /**

     Constructs a new instance of the RayTracerBasic class with the specified scene.
     @param scene The scene to be rendered.
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Traces a ray using a basic ray tracing algorithm.
     *
     * @return The color of the traced ray.
     */
    @Override
    public Color traceRay(Ray ray) {
        List<Point> intersections = scene.geometries.findIntersections(ray);
        if(intersections == null){
            return this.scene.background;
        }
        Point closestPoint = ray.findClosestPoint(intersections);
        return calcColor(closestPoint);
    }

    private Color calcColor(Point point){
        return this.scene.background;
    }
}
