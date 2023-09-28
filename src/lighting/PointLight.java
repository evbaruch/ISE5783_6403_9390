package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static primitives.Util.alignZero;

/**
 * Represents a point light source in a lighting system.
 * A point light is a light source located at a specific position and emits light in all directions.
 */
public class PointLight extends Light implements LightSource{

    private Point position;



    /**

     The constant coefficient (Kc) determines the attenuation of light intensity
     with respect to distance. It represents the constant term in the attenuation
     equation and is used to calculate the overall light intensity. A higher Kc value
     results in less attenuation and a longer reach of the light.
     */

    /**

     The linear coefficient (Kl) determines the linear attenuation of light intensity
     with respect to distance. It represents the linear term in the attenuation equation
     and is used to calculate the overall light intensity. A higher Kl value results in
     a more rapid decrease in light intensity with increasing distance.
     */

    /**

     The quadratic coefficient (Kq) determines the quadratic attenuation of light intensity
     with respect to distance. It represents the quadratic term in the attenuation equation
     and is used to calculate the overall light intensity. A higher Kq value results in a
     more significant decrease in light intensity with increasing distance.
     */
    private double Kc = 1, Kl = 0, Kq = 0;

    //The radius is the size of the light source
    private double radius = 0;

    /**
     * Constructs a PointLight object with the specified intensity and position.
     *
     * @param intensity The intensity of the point light.
     * @param position  The position of the light source.
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.Kc = Kc;
        this.Kl = Kl;
        this.Kq = Kq;
    }

    /**
     *
     * @param p The point at which to determine the light intensity.
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        Vector lightDirection = position.subtract(p).normalize(); // Calculate the direction from the point to the light source
        double distance = position.distance(p); // Calculate the distance between the point and the light source

        // Calculate the attenuation factor
        double attenuation = 1 / (Kc + Kl * distance + Kq * distance * distance);

        // Calculate the intensity by scaling the light's intensity with the attenuation factor
        Color scaledIntensity = getIntensity().scale(attenuation);

        return scaledIntensity;
    }

    /**
     * Retrieves the direction of the light at the specified point as a normalized vector.
     * The vector points from the point towards the light source.
     *
     * @param p The point at which to determine the light direction.
     * @return The direction of the light at the specified point.
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize(); // Calculate the direction vector from the point to the light source
    }


    /**
     * Calculates and returns a list of normalized vectors pointing from the given point
     * to random points on the surface of a sphere with the specified radius and position.
     * The number of vectors to be generated is determined by the numOfgetL parameter.
     *
     * @param p          The reference point from which the vectors will be calculated.
     * @param numOfgetL  The number of vectors to generate.
     * @return A list of normalized vectors pointing from the reference point to random points on the sphere.
     */
    @Override
    public List<Vector> getL(Point p, int numOfgetL) {

        List<Vector> listVector = new LinkedList<>();

        if (numOfgetL <= 1 && alignZero(this.radius) == 0) {
            // If only one vector is requested and the sphere has no radius,
            // add a normalized vector pointing from the reference point to the sphere's position.
            listVector.add(p.subtract(position).normalize());
        } else {
            Random random = new Random();

            for (int k = 0; k < numOfgetL; k++) {
                // Generate random coordinates within the range of -1 to 1.
                double x = random.nextDouble(-1, 1);
                double y = random.nextDouble(-1, 1);
                double z = random.nextDouble(-1, 1);

                // Create a vector with the generated coordinates, normalize it, and scale it by the sphere's radius.
                Vector v = new Vector(x, y, z).normalize().scale(this.radius);

                // Calculate a point on the surface of the sphere by adding the generated vector to the reference point.
                Point pointOnBall = p.add(v);

                // Add a normalized vector pointing from the reference point to the calculated point on the sphere.
                listVector.add(pointOnBall.subtract(position).normalize());
            }
        }

        // Return the list of generated vectors.
        return listVector;
    }


    /**
     * Sets the constant attenuation factor for the point light.
     *
     * @param kc The constant attenuation factor to set.
     * @return This PointLight object.
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;//
        return this;
    }

    /**
     * Sets the linear attenuation factor for the point light.
     *
     * @param kL The linear attenuation factor to set.
     * @return This PointLight object.
     */
    public PointLight setKl(double kL) {
        this.Kl = kL;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor for the point light.
     *
     * @param kq The quadratic attenuation factor to set.
     * @return This PointLight object.
     */
    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }

    /**
     * Sets the radius of the PointLight.
     *
     * @param r The radius of the PointLight.
     * @return The updated PointLight object.
     */
    public PointLight setRadius(double r) {
        this.radius = r;
        return this;
    }

    /**
     * Retrieves the radius of the PointLight.
     *
     * @return The radius of the PointLight.
     */
    public double getRadius() {
        return this.radius;
    }


    /**
     * Calculates the distance between this point and the specified point.
     *
     * @param point The point to calculate the distance to.
     * @return The distance between this point and the specified point.
     */
    @Override
    public double getDistance(Point point) {
        return point.distance(position);
    }
}
