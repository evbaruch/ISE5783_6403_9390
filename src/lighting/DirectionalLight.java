package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Represents a directional light source in a lighting system.
 * A directional light is a light source that is infinitely far away and emits light in a specific direction.
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * Constructs a DirectionalLight object with the specified intensity and direction.
     *
     * @param intensity The intensity of the directional light.
     * @param direction The direction in which the light is emitted, as a normalized vector.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    /**
     * Retrieves the intensity of the directional light at the specified point.
     * As a directional light is infinitely far away, its intensity is constant regardless of the point's position.
     *
     * @param p The point at which to determine the light intensity.
     * @return The intensity of the directional light.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Retrieves the direction of the directional light at the specified point.
     * As a directional light is infinitely far away, the light direction is constant regardless of the point's position.
     *
     * @param p The point at which to determine the light direction.
     * @return The direction of the directional light.
     */
    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

}
