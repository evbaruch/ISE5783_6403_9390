package primitives;

/**
 * Represents a color associated with a ray in the context of adaptive supersampling.
 * It serves as a container for the ray and its corresponding color.
 */
public class ColorRay {
    private final Ray ray;
    private final Color color;

    /**
     * Constructs a new ColorRay with the given ray and color.
     *
     * @param ray   The ray.
     * @param color The color.
     */
    public ColorRay(Ray ray, Color color) {
        this.ray = ray;
        this.color = color;
    }

    /**
     * Returns the ray.
     *
     * @return The ray.
     */
    public Ray getRay() {
        return ray;
    }

    /**
     * Returns the color.
     *
     * @return The color.
     */
    public Color getColor() {
        return color;
    }
}
