package lighting;

import primitives.Color;

/**
 * The abstract base class for different types of lights in a lighting system.
 */
abstract class Light {
    private Color intensity;

    /**
     * Retrieves the intensity of the light.
     *
     * @return The intensity of the light.
     */
    public Color getIntensity() {
        return intensity;
    }

    /**
     * Constructs a Light object with the specified intensity.
     *
     * @param intensity The intensity of the light.
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }
}
