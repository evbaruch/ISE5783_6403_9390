package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a point light source in a lighting system.
 * A point light is a light source located at a specific position and emits light in all directions.
 */
public class PointLight extends Light implements LightSource{

    private Point position;

    private double Kc = 1, Kl = 0, Kq = 0;

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
     * Sets the constant attenuation factor for the point light.
     *
     * @param kc The constant attenuation factor to set.
     * @return This PointLight object.
     */
    public PointLight setKc(double kc) {
        this.Kc = kc;
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
}
