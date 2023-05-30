package renderer;

import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;


/**

 The RayTracerBasic class is an implementation of the RayTracerBase class, which
 provides a basic ray tracing algorithm.
 @author evyatar
 @version 1.0
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;

    /**
     * Checks if a point is unshaded by a specific light source.
     *
     * @param gp       The geometric point representing the intersection.
     * @param light    The light source.
     * @param l        The direction vector from the point to the light source.
     * @param n        The normal vector at the intersection point.
     * @param nl       The dot product between the normal vector and the direction vector to the light source.
     * @return True if the point is unshaded by the light source, false otherwise.
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l,  Vector n, double nl) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector epsVector = n.scale(nl < 0 ? DELTA : -DELTA);//(-1)

        Point point = gp.point.add(epsVector);
        Ray lightRay = new Ray(point, lightDirection);
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, light.getDistance(gp.point));
        return intersections == null ? true : false;
    }



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
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(ray);
        if(intersections == null){
            return this.scene.getBackground();
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }

    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.getAmbientLight().getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * Calculates the local effects (diffuse and specular) at a given geometric point.
     *
     * @param gp  The geometric point.
     * @param ray The ray.
     * @return The color with local effects applied.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        // Start with the emission color of the geometry
        Color color = gp.geometry.getEmission();

        // Get the direction of the ray and the normal vector at the geometric point
        Vector v = ray.getDir();
        Vector n = gp.geometry.getNormal(gp.point);

        // Calculate the dot product of the normal and the view direction
        double nv = alignZero(n.dotProduct(v));

        // If the dot product is close to zero, return the emission color
        if (nv == 0) return color;

        // Get the material of the geometry
        Material material = gp.geometry.getMaterial();

        // Iterate over each light source in the scene
        for (LightSource lightSource : scene.getLights()) {

            // Get the light direction at the geometric point
            Vector l = lightSource.getL(gp.point);

            // Calculate the dot product of the normal and the light direction
            double nl = alignZero(n.dotProduct(l));

            // If the signs of nl and nv are the same, calculate the diffuse and specular effects
            if (nl * nv > 0 && unshaded(gp, lightSource, l, n, nl)) { // sign(nl) == sing(nv)

                // Get the intensity of the light source at the geometric point
                Color iL = lightSource.getIntensity(gp.point);

                // Add the diffuse and specular components to the color
                color = color.add(
                        iL.scale(calcDiffusive(material, nl)),
                        iL.scale(calcSpecular(material, n, l, nl, v))
                );
            }
        }
        return color;
    }

    /**
     * Calculates the diffusive reflection contribution for a given material and dot product between the normal vector
     * and the direction vector to the light source.
     *
     * @param material The material of the object.
     * @param nl       The dot product between the normal vector and the direction vector to the light source.
     * @return The diffusive reflection contribution as a Double3 color value.
     */
    private Double3 calcDiffusive(Material material, double nl) {
        double absNl = (nl < 0) ? -nl : nl;
        return material.kd.scale(absNl);
    }

    /**
     * Calculates the specular reflection contribution for a given material and lighting conditions.
     *
     * @param material The material of the object.
     * @param n        The normal vector at the intersection point.
     * @param l        The direction vector from the point to the light source.
     * @param nl       The dot product between the normal vector and the direction vector to the light source.
     * @param v        The direction vector from the point to the viewer.
     * @return The specular reflection contribution as a Double3 color value.
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Double3 ks = material.Ks;
        int shininess = material.nShininess;

        Vector r = l.subtract(n.scale(2 * nl)).normalize();
        double vr = v.scale(-1).dotProduct(r);
        vr = (vr > 0) ? vr : 0; // Ensure vr is non-negative


        if (vr <= 0) return new Double3(0); // No specular reflection in this case

        double result = 1.0;
        for (int i = 0; i < shininess; i++) {
            result *= vr;
        }

        return ks.scale(result);
    }

}
