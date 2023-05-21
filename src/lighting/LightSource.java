package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a light source in a lighting system.
 */
public interface LightSource {

    /**
     * Retrieves the intensity of the light at the specified point.
     *
     * @param p The point at which to determine the light intensity.
     * @return The intensity of the light at the specified point.
     */
    public Color getIntensity(Point p);

    /**
     * Retrieves the direction of the light at the specified point as a normalized vector.
     * The vector points from the point towards the light source.
     *
     * @param p The point at which to determine the light direction.
     * @return The direction of the light at the specified point.
     */
    public Vector getL(Point p);
}
