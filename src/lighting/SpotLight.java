package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;


/**
 * The SpotLight class represents a type of light source that emits light in a specific direction
 * with a narrow beam width.
 */
public class SpotLight extends PointLight {

    private Vector direction;
    private double beamWidth;

    /**
     * Constructs a SpotLight object with the specified intensity, position, and direction.
     *
     * @param intensity The intensity of the spotlight.
     * @param position  The position of the spotlight.
     * @param direction The direction in which the spotlight emits light, as a normalized vector.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
        this.beamWidth = 1;
    }

    /**
     * Sets the narrowness of the spotlight beam.
     *
     * @param n The level of narrowness to set.
     * @return This SpotLight object.
     */
    public SpotLight setNarrowBeam(int n) {
        // Calculate the angle of the spotlight beam based on the provided level of narrowness
        this.beamWidth = n;
        return this;
    }

    /**
     * Retrieves the intensity of the spotlight at a given point.
     *
     * @param p The point at which to calculate the intensity.
     * @return The intensity of the spotlight at the given point.
     */
    @Override
    public Color getIntensity(Point p) {
        // Calculate the intensity of the spotlight at the given point
        double dotProduct = Math.max(0, this.direction.normalize().dotProduct(super.getL(p)));
        Color intensity = super.getIntensity(p);
        return intensity.scale(Math.pow(dotProduct, this.beamWidth));
    }
}

