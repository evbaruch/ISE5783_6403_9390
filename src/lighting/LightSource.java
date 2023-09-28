package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.List;

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

    /**
     * Retrieves a list of directions from the specified point towards the light source.
     * The number of directions returned is determined by the "numOfgetL" parameter.
     *
     * @param p           The point at which to determine the light directions.
     * @param numOfgetL   The number of light directions to retrieve.
     * @return A list of light directions at the specified point.
     */
    public List<Vector> getL(Point p, int numOfgetL);

    /**
     * Retrieves the distance between the specified point and the light source.
     *
     * @param point The point for which to determine the distance to the light source.
     * @return The distance between the point and the light source.
     */
    double getDistance(Point point);

}
