package renderer;


import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;
import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;


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



    private GeoPoint findClosestIntersection(Ray ray){
        return ray.findClosestGeoPoint(
                this.scene.getGeometries().findGeoIntersections(ray)
        );
    }


    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.getAmbientLight().getIntensity());
    }


    private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(gp, ray, k);
        return level == 1 ? color : color.add(calcGlobalEffects(gp, ray, level, k));
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


    private Color calcGlobalEffects(GeoPoint gp, Ray inRay, int level, Double3 k) {
        Color color = Color.BLACK;
        Material mat = gp.geometry.getMaterial();

        Double3 kr = mat.Kr;
        Double3 kkr = k.product(kr);
        if (kkr.greaterThan(MIN_CALC_COLOR_K)){
            Ray reflectedRay = constructReflectedRay(gp, inRay);
            GeoPoint reflectedPoint = findClosestIntersection(reflectedRay);
            if(mat.glossMeasure > 0)
            {
                Beam beam = new Beam();
                GeoPoint geoPointBlur = new GeoPoint(gp.geometry, gp.point.add(gp.geometry.getNormal(gp.point)));
                beam.constructBeam( mat.glossMeasure/10,mat.glossMeasure/10, geoPointBlur.point , (int)mat.glossMeasure, reflectedRay);
                for(Ray ray : beam.getRays())
                {
                    color = color.add(calcGlobalEffects(ray,level, k, mat.Kr));
                }
                color = color.reduce(beam.getRays().size());
            }
            if (reflectedPoint == null){
                return  color;
            }

            color = color.add(calcColor(reflectedPoint, reflectedRay,level - 1,kkr).scale(kr));
        }

        Double3 kt = mat.Kt;
        Double3 kkt = k.product(kt);
        if (kkt.greaterThan( MIN_CALC_COLOR_K))
        {
            Ray refractedRay = constructRefractedRay(gp, inRay);

            GeoPoint refractedPoint = findClosestIntersection(refractedRay);

            if (refractedPoint == null){
                return  color;
            }

            if(mat.blurAngle > 0) {
                Beam beam = new Beam();
                beam.constructBeam(mat.blurAngle/8 , mat.blurAngle/8, refractedPoint.point, (int)mat.blurAngle, refractedRay);
                for (Ray ray : beam.getRays()) {
                    color = color.add(calcGlobalEffects(ray, level, k, mat.Kt));
                }
                color = color.reduce(beam.getRays().size());
            }
            color = color.add(calcColor(refractedPoint, refractedRay, level - 1, kkt).scale(kt));
        }

        return color;
    }


    /**
     * calculates the global effects of the point with the help of the
     * reflected ray and the refracted ray
     *
     * @param level The material of the geometry.
     * @param ray The dot product of the normal and the light source.
     * @return the color of the point.
     */
    private Color calcGlobalEffects(Ray ray, int level, Double3 k, Double3 kx) {
        Double3 kkx = k.product(kx);
        if (kkx.lowerThan(MIN_CALC_COLOR_K)) return Color.BLACK;
        GeoPoint gp = findClosestIntersection(ray);
        if (gp == null) return scene.getBackground().scale(kx);

        return isZero(gp.geometry.getNormal(gp.point).dotProduct(ray.getDir()))? Color.BLACK :
                calcColor(gp, ray, level-1, kkx).scale(kx);
    }

    private Ray constructReflectedRay(GeoPoint gp,Ray inRay){
        Vector v = inRay.getDir();
        Vector n = gp.geometry.getNormal(gp.point);
        double vn = v.dotProduct(n);
        Vector r = v.subtract(n.scale(2 * vn));

        Point p = shiptPoint(gp,r);
        return new Ray(p,r);
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
        boolean snell = true;
        if (snell) {
            Vector n = geoPoint.geometry.getNormal(geoPoint.point);
            double Ni = scene.getRefractiveIndex();
            double Nj = geoPoint.geometry.getMaterial().refractiveIndex;

            // Case: Same refractive indices
            if (Ni == Nj) {
                // No refraction occurs, return a new ray with the same direction
                return new Ray(geoPoint.point.add(inRay.getDir().scale(DELTA)), inRay.getDir());
            }

            double incidentAngle = Math.acos(n.dotProduct(inRay.getDir()));
            double sinThetaR = (Ni / Nj) * Math.sin(incidentAngle);

            // Case: Total internal reflection
            if (sinThetaR >= 1.0) {

                //  The incident ray undergoes total internal reflection at the interface between two mediums.
                //  Calculate the reflected direction using the law of reflection.
                //  The reflected direction is obtained by subtracting two times the projection of the incident ray direction onto the surface normal vector,
                //  scaled by the surface normal vector itself.
                Vector reflectedDir = inRay.getDir().subtract(n.scale(2 * inRay.getDir().dotProduct(n)));
                //  Create a new ray representing the reflected ray.
                //  Shift the origin slightly along the incident ray direction and set the direction as the calculated reflected direction.
                return new Ray(geoPoint.point.add(inRay.getDir().scale(DELTA)), reflectedDir);
            }

            double refractedAngle = Math.asin(sinThetaR);

            // Case: Regular refraction
            // Calculate the refracted direction using Snell's law: n1 * sin(theta1) = n2 * sin(theta2)
            // refractedDir = (n1/n2) * incidentDir + (n1/n2 * cos(theta1) - cos(theta2)) * normalDir
            Vector refractedDir = inRay.getDir().scale(Ni / Nj).add(n.scale((Ni / Nj) * Math.cos(incidentAngle) - Math.cos(refractedAngle)));

            // Return a new ray with the refracted direction
            return new Ray(geoPoint.point.add(inRay.getDir().scale(DELTA)), refractedDir);

        } else {

            return new Ray(geoPoint.point.add(inRay.getDir().scale(DELTA)), inRay.getDir());
        }

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
            return Double3.ONE;//צריך עיון
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


    private Point shiptPoint(GeoPoint gp,Vector v)
    {
        //Moves the point on the normal in the direction of the vector
        Vector n = gp.geometry.getNormal(gp.point);
        double d = n.dotProduct(v);
        Vector epsVector = n.scale(d > 0 ? DELTA : -DELTA);
        return gp.point.add(epsVector);
    }

}
