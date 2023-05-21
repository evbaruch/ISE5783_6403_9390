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
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if(intersections == null){
            return this.scene.background;
        }
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }



//    private Color calcColor(GeoPoint geoPoint){
//        //
//        return scene.ambientLight.getIntensity()
//                .add(geoPoint.geometry.getEmission());
//    }
//    private Color calcColor(GeoPoint geoPoint) {
//        Color ambientLightIntensity = scene.ambientLight.getIntensity();
//        Color emissionColor = geoPoint.geometry.getEmission();
//        Color lightIntensity = Color.BLACK; // Initialize with no light intensity
//
//        // Iterate over the light sources in the scene and calculate the total light intensity
//        for (LightSource lightSource : scene.lights) {
//            lightIntensity = lightIntensity.add(lightSource.getIntensity(geoPoint.point));
//        }
//
//        // Combine the ambient light intensity, emission color, and total light intensity
//        return ambientLightIntensity.add(emissionColor).add(lightIntensity);
//    }

//    private Color calcColor(GeoPoint geoPoint) {
//        Color ambientLightIntensity = scene.ambientLight.getIntensity();
//        Color emissionColor = geoPoint.geometry.getEmission();
//        Color diffuseColor = Color.BLACK; // Initialize with no diffuse color
//        Color specularColor = Color.BLACK; // Initialize with no specular color
//
//        // Iterate over the light sources in the scene and calculate the diffuse and specular components
//        for (LightSource lightSource : scene.lights) {
//            // Calculate the diffuse component
//            Vector l = lightSource.getL(geoPoint.point).normalize(); // Light direction vector
//            Vector n = geoPoint.geometry.getNormal(geoPoint.point).normalize(); // Surface normal vector
//            double ln = Math.max(0, l.dotProduct(n)); // Dot product of light direction and surface normal
//            Color lightDiffuseColor = lightSource.getIntensity(geoPoint.point).scale(ln);
//            diffuseColor = diffuseColor.add(lightDiffuseColor);
//
//            // Calculate the specular component
//            double shininess = geoPoint.geometry.getMaterial().nShininess; // Shininess factor
//            Vector r = l.subtract(n.scale(2 * ln)).normalize(); // Reflection vector
//            double specularFactor = Math.pow(Math.max(0, r.dotProduct(l)), shininess); // Specular factor
//            Color lightSpecularColor = lightSource.getIntensity(geoPoint.point).scale(specularFactor).scale(geoPoint.geometry.getMaterial().Ks);
//            specularColor = specularColor.add(lightSpecularColor);
//        }
//
//        // Combine the ambient light intensity, emission color, diffuse color, and specular color
//        return ambientLightIntensity.scale(geoPoint.geometry.getMaterial().kd)
//                .add(emissionColor)
//                .add(diffuseColor)
//                .add(specularColor);
//    }


//    private Color calcColor(GeoPoint geoPoint) {
//        Color ambientLightIntensity = scene.ambientLight.getIntensity();
//        Color emissionColor = geoPoint.geometry.getEmission();
//
//        Color diffuseColor = calculateDiffuseColor(geoPoint);
//        Color specularColor = calculateSpecularColor(geoPoint);
//
//        return ambientLightIntensity.scale(geoPoint.geometry.getMaterial().kd)
//                .add(emissionColor)
//                .add(diffuseColor)
//                .add(specularColor);
//    }
//
//    private Color calculateDiffuseColor(GeoPoint geoPoint) {
//        Color diffuseColor = Color.BLACK;
//
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(geoPoint.point).normalize();
//            Vector n = geoPoint.geometry.getNormal(geoPoint.point).normalize();
//            double ln = Math.max(0, l.dotProduct(n));
//
//            Color lightDiffuseColor = lightSource.getIntensity(geoPoint.point).scale(ln);
//            diffuseColor = diffuseColor.add(lightDiffuseColor);
//        }
//
//        return diffuseColor;
//    }
//
//    private Color calculateSpecularColor(GeoPoint geoPoint) {
//        Color specularColor = Color.BLACK;
//
//        for (LightSource lightSource : scene.lights) {
//            Vector l = lightSource.getL(geoPoint.point).normalize();
//            Vector n = geoPoint.geometry.getNormal(geoPoint.point).normalize();
//            double ln = Math.max(0, l.dotProduct(n));
//            double shininess = geoPoint.geometry.getMaterial().nShininess;
//            Vector r = l.subtract(n.scale(2 * ln)).normalize();
//            double specularFactor = Math.pow(Math.max(0, r.dotProduct(-geoPoint.point.getV())), shininess);
//
//            Color lightSpecularColor = lightSource.getIntensity(geoPoint.point)
//                    .scale(specularFactor)
//                    .scale(geoPoint.geometry.getMaterial().Ks);
//
//            specularColor = specularColor.add(lightSpecularColor);
//        }
//
//        return specularColor;
//    }



//    private Color calcColor(GeoPoint geoPoint,Ray ray) {
//        Color ambientLightIntensity = scene.ambientLight.getIntensity();
//        Color emissionColor = geoPoint.geometry.getEmission();
//
//        Color diffuseColor = calculateDiffuseColor(geoPoint);
//        Color specularColor = calculateSpecularColor(geoPoint,ray);
//
//        return ambientLightIntensity.scale(geoPoint.geometry.getMaterial().kd)
//                .add(emissionColor)
//                .add(diffuseColor)
//                .add(specularColor);
//    }
//
//    private Color calculateDiffuseColor(GeoPoint geoPoint) {
//        Color diffuseColor = Color.BLACK;
//        Material material = geoPoint.geometry.getMaterial();
//
//        for (LightSource lightSource : scene.lights) {
//            Vector lightDirection = lightSource.getL(geoPoint.point).normalize();
//            Vector surfaceNormal = geoPoint.geometry.getNormal(geoPoint.point).normalize();
//            double ln = Math.max(0, lightDirection.dotProduct(surfaceNormal));
//
//            Color lightDiffuseColor = lightSource.getIntensity(geoPoint.point).scale(ln);
//            diffuseColor = diffuseColor.add(lightDiffuseColor);
//        }
//
//        diffuseColor = diffuseColor.scale(material.kd);
//        return diffuseColor;
//    }
//
//    private Color calculateSpecularColor(GeoPoint geoPoint, Ray ray) {
//        Color specularColor = Color.BLACK;
//        Material material = geoPoint.geometry.getMaterial();
//        Vector viewDirection = geoPoint.point.subtract(ray.getDir()).normalize();
//
//        for (LightSource lightSource : scene.lights) {
//            Vector lightDirection = lightSource.getL(geoPoint.point).normalize();
//            Vector surfaceNormal = geoPoint.geometry.getNormal(geoPoint.point).normalize();
//            double ln = Math.max(0, lightDirection.dotProduct(surfaceNormal));
//            double shininess = material.nShininess;
//
//            Vector reflectionDirection = lightDirection.subtract(surfaceNormal.scale(2 * ln)).normalize();
//
//            double specularFactor = Math.pow(Math.max(0, reflectionDirection.dotProduct(viewDirection)), shininess);
//
//            Color lightSpecularColor = lightSource.getIntensity(geoPoint.point).scale(specularFactor).scale(material.Ks);
//            specularColor = specularColor.add(lightSpecularColor);
//        }
//
//        return specularColor;
//    }


    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
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
        for (LightSource lightSource : scene.lights) {

            // Get the light direction at the geometric point
            Vector l = lightSource.getL(gp.point);

            // Calculate the dot product of the normal and the light direction
            double nl = alignZero(n.dotProduct(l));

            // If the signs of nl and nv are the same, calculate the diffuse and specular effects
            if (nl * nv > 0) { // sign(nl) == sing(nv)

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


    private Double3 calcDiffusive(Material material, double nl) {
        double absNl = (nl < 0) ? -nl : nl;
        return material.kd.scale(absNl);
    }

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
