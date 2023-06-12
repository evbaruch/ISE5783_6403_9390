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

    private static final int MAX_CALC_COLOR_LEVEL = 10;

    private static final double MIN_CALC_COLOR_K = 0.001;

    private static final Double3 INITIAL_K = Double3.ONE;

    private  static final boolean SNELL = true;

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
        GeoPoint closestPoint = this.findClosestIntersection(ray);
        if(closestPoint == null){
            return this.scene.getBackground();
        }
        return calcColor(closestPoint,ray);
    }


    /**
     * Finds the closest intersection of a given ray with the geometries in the scene.
     *
     * @param ray The ray for which to find the closest intersection.
     * @return The closest intersection point as a GeoPoint object.
     */
    private GeoPoint findClosestIntersection(Ray ray){
        // Find the intersections of the ray with the geometries in the scene
        // using the findGeoIntersections method of the Geometries class.
        // The method returns a list of GeoPoint objects representing the intersections.
        List<GeoPoint> intersections = this.scene.getGeometries().findGeoIntersections(ray);

        // Find the closest intersection point using the findClosestGeoPoint method of the Ray class.
        // The method takes a list of GeoPoint objects and returns the closest one.
        return ray.findClosestGeoPoint(intersections);
    }


    /**
     * Calculates the color at a given intersection point.
     *
     * @param gp The intersection point as a GeoPoint object.
     * @param ray The ray that intersects with the object.
     * @return The calculated color at the intersection point.
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        // Calculate the color at the intersection point by adding the result of the
        // calcColor method with the ambient light intensity.
        // The calcColor method takes the intersection point, ray, maximum calculation level,
        // and initial K coefficient as parameters.
        // The add method returns a new Color object that is the sum of the two colors.
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.getAmbientLight().getIntensity());
    }


    /**
     * Calculates the color at a given intersection point, taking into account local and global effects.
     *
     * @param gp The intersection point as a GeoPoint object.
     * @param ray The ray that intersects with the object.
     * @param level The current calculation level, used to control recursion depth.
     * @param k The coefficient used for global effects.
     * @return The calculated color at the intersection point, considering local and global effects.
     */
    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        // Calculate the color at the intersection point, taking into account local effects.
        // The calcLocalEffects method takes the intersection point, ray, and coefficient k as parameters.
        // It returns the color calculated based on the local effects.
        Color color = calcLocalEffects(gp, ray, k);

        // Check the current calculation level.
        if (level == 1) {
            // If the current level is 1, return the color without considering global effects.
            return color;
        } else {
            // If the current level is greater than 1, calculate the global effects and add them to the color.
            // The calcGlobalEffects method takes the intersection point, ray, current level, and coefficient k as parameters.
            // It returns the color calculated based on the global effects.
            return color.add(calcGlobalEffects(gp, ray, level, k));
        }
    }


    /**
     * Calculates the local effects (diffuse and specular) at a given geometric point.
     *
     * @param gp  The geometric point.
     * @param ray The ray.
     * @return The color with local effects applied.
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
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
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(gp, lightSource, l);
                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)){
                    // Get the intensity of the light source at the geometric point
                    Color iL = lightSource.getIntensity(gp.point).scale(ktr);

                    // Add the diffuse and specular components to the color
                    color = color.add(
                            iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v))
                    );
                }
            }
        }
        return color;
    }


    /**
     * Calculates the color contribution from global effects (reflection and refraction) at a given intersection point.
     *
     * @param gp The intersection point as a GeoPoint object.
     * @param inRay The incoming ray that intersects with the object.
     * @param level The current calculation level, used to control recursion depth.
     * @param k The coefficient used for global effects.
     * @return The calculated color contribution from global effects at the intersection point.
     */
    private Color calcGlobalEffects(GeoPoint gp, Ray inRay, int level, Double3 k) {
        // Initialize the color as black.
        Color color = Color.BLACK;

        // Get the material of the intersected geometry.
        Material mat = gp.geometry.getMaterial();

        // Calculate the coefficient for transmission (refraction) effects.
        Double3 kt = mat.Kt;
        Double3 kkt = k.product(kt);

        // Check if the transmission coefficient is greater than the minimum calculation coefficient.
        if (kkt.greaterThan(MIN_CALC_COLOR_K)) {
            // Construct the refracted ray based on the intersection point and incoming ray.
            Ray refractedRay = constructRefractedRay(gp, inRay);

            // Find the closest intersection point for the refracted ray.
            GeoPoint refractedPoint = findClosestIntersection(refractedRay);

            // Check if a valid intersection point exists.
            if (refractedPoint != null) {
                // Calculate the color at the refracted intersection point, considering global effects recursively.
                // The calcColor method takes the refracted intersection point, refracted ray, updated level, and kkt coefficient as parameters.
                // It returns the color calculated based on the local and global effects at the refracted point.
                color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
            }
        }

        // Calculate the coefficient for reflection effects.
        Double3 kr = mat.Kr;
        Double3 kkr = k.product(kr);

        // Check if the reflection coefficient is greater than the minimum calculation coefficient.
        if (kkr.greaterThan(MIN_CALC_COLOR_K)) {
            // Construct the reflected ray based on the intersection point and incoming ray.
            Ray reflectedRay = constructReflectedRay(gp, inRay);

            // Find the closest intersection point for the reflected ray.
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);

            // Check if a valid intersection point exists.
            if (reflectedPoint != null) {
                // Calculate the color at the reflected intersection point, considering global effects recursively.
                // The calcColor method takes the reflected intersection point, reflected ray, updated level, and kkr coefficient as parameters.
                // It returns the color calculated based on the local and global effects at the reflected point.
                color = color.add(calcColor(reflectedPoint, reflectedRay, level - 1, kkr).scale(kr));
            }
        }

        return color;
    }


    /**
     * Constructs a reflected ray based on the intersection point and incoming ray.
     *
     * @param gp The intersection point as a GeoPoint object.
     * @param inRay The incoming ray that intersects with the object.
     * @return The reflected ray.
     */
    private Ray constructReflectedRay(GeoPoint gp, Ray inRay) {
        // Get the direction vector of the incoming ray.
        Vector v = inRay.getDir();

        // Get the normal vector of the geometry at the intersection point.
        Vector n = gp.geometry.getNormal(gp.point);

        // Calculate the dot product between the incoming ray direction and the normal vector.
        double vn = v.dotProduct(n);

        // Calculate the reflected vector using the reflection formula: r = v - 2 * (v dot n) * n
        Vector r = v.subtract(n.scale(2 * vn));

        // Shift the intersection point slightly along the reflected vector to avoid self-intersections.
        Point p = shiptPoint(gp, r);

        // Create and return a new ray with the shifted intersection point and reflected direction.
        return new Ray(p, r);
    }



    /**
     * Constructs the refracted ray based on Snell's law at the given intersection point.
     *
     * If the refractive indices of the intersected geometry and the scene are the same,
     * it returns a new ray with the same direction as the incident ray.
     *
     * If total internal reflection occurs, it calculates the reflected ray.
     * Otherwise, it computes the refracted ray according to Snell's law.
     *
     * @param geoPoint The intersection point on the geometry.
     * @param inRay    The incident ray.
     * @return The refracted ray.
     */
    private Ray constructRefractedRay(GeoPoint geoPoint, Ray inRay) {
        if (SNELL) {
            double Ni = scene.getRefractiveIndex();
            double Nj = geoPoint.geometry.getMaterial().refractiveIndex;

            // Case: Same refractive indices
            if (Ni != Nj) {
                Vector n = geoPoint.geometry.getNormal(geoPoint.point);
                double incidentAngle = Math.acos(n.dotProduct(inRay.getDir()));
                double sinThetaR = (Ni / Nj) * Math.sin(incidentAngle);

                //Case: Total internal reflection
                if (sinThetaR >= 1) {

                    //  The incident ray undergoes total internal reflection at the interface between two mediums.
                    //  Calculate the reflected direction using the law of reflection.
                    //  The reflected direction is obtained by subtracting two times the projection of the incident ray direction onto the surface normal vector,
                    //  scaled by the surface normal vector itself.

                    //  Create a new ray representing the reflected ray.
                    //  Shift the origin slightly along the incident ray direction and set the direction as the calculated reflected direction.
                    return  constructReflectedRay(geoPoint,inRay);
                }

                double refractedAngle = Math.asin(sinThetaR);

                // Case: Regular refraction
                // Calculate the refracted direction using Snell's law: n1 * sin(theta1) = n2 * sin(theta2)
                // refractedDir = (n1/n2) * incidentDir + (n1/n2 * cos(theta1) - cos(theta2)) * normalDir
                Vector refractedDir = inRay.getDir().scale(Ni / Nj).add(n.scale((Ni / Nj) * Math.cos(incidentAngle) - Math.cos(refractedAngle)));

                // Shift the intersection point slightly along the reflected vector to avoid self-intersections.
                Point p = shiptPoint(geoPoint, refractedDir);


                // Return a new ray with the refracted direction
                return new Ray(p, refractedDir);
            }

        }

        // Shift the intersection point slightly along the reflected vector to avoid self-intersections.
        Point p = shiptPoint(geoPoint, inRay.getDir());

        return new Ray(p, inRay.getDir());
    }


    /**
     * Checks if a point is unshaded by a specific light source.
     *
     * @param gp       The geometric point representing the intersection.
     * @param light    The light source.
     * @param l        The direction vector from the point to the light source.
     * @return True if the point is unshaded by the light source, false otherwise.
     */
    private Double3 transparency(GeoPoint gp, LightSource light, Vector l) {
        // gp = Point on the shape, light = the light source,
        // l = light direction, n = the normal vector
        // nl = dotProduct of n and l

        Vector lightDirection = l.scale(-1);
        //Find the new point
        Point point = shiptPoint(gp,lightDirection);
        // will be built from the new point  a ray
        Ray lightRay = new Ray(point, lightDirection);
        //We will find the entire score up to the light source
        List<GeoPoint> intersections = scene.getGeometries().findGeoIntersections(lightRay, light.getDistance(gp.point));

        //We will only return false here if there is a point on the path that is not transparent at all
        if (intersections == null){
            return Double3.ONE;
        } else{
            Double3 ktr = Double3.ONE;
            //We will go over all the points and check transparency
            for(GeoPoint intersection : intersections){
                ktr = ktr.product(intersection.geometry.getMaterial().Kt);
            }
            return ktr;
        }
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


    /**
     * Shifts the intersection point along the normal vector in the direction of the given vector.
     *
     * @param gp The intersection point as a GeoPoint object.
     * @param v The vector indicating the direction of the shift.
     * @return The shifted point.
     */
    private Point shiptPoint(GeoPoint gp, Vector v) {
        // Get the normal vector of the geometry at the intersection point.
        Vector n = gp.geometry.getNormal(gp.point);

        // Calculate the dot product between the normal vector and the given vector.
        double d = n.dotProduct(v);

        // Scale the normal vector by the dot product multiplied by the delta constant (positive or negative).
        Vector epsVector = n.scale(d > 0 ? DELTA : -DELTA);

        // Add the scaled vector to the intersection point to obtain the shifted point.
        return gp.point.add(epsVector);
    }


}