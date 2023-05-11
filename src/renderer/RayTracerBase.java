package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;


/**

 The RayTracerBase class is an abstract base class for implementing ray tracing algorithms.
 It defines the common properties and methods that all RayTracer classes should have.
 @author evyatar
 @version 1.0
 */
public abstract class RayTracerBase {
    protected Scene scene;

    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Abstract method for tracing a ray and returning the resulting color.
     *
     * @return The color of the traced ray.
     */
    abstract public Color traceRay(Ray ray);

}
