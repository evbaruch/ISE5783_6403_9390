package lighting;
import primitives.Color;
import primitives.Double3;

/**
 * Represents an ambient light source in a lighting system.
 * Ambient light is a non-directional light that illuminates all objects uniformly.
 */
public class AmbientLight extends Light {
    //A New Beginning

    /**
     * Represents the absence of ambient light.
     */
    public static final AmbientLight NONE  = new AmbientLight(Color.BLACK,Double3.ZERO);

    /**
     * Constructs an AmbientLight object with the specified color and intensity.
     *
     * @param color The color of the ambient light.
     * @param double3 The intensity of the ambient light as a 3D vector.
     */
    public AmbientLight(Color color, Double3 double3){

        super(color.scale(double3));
    }

    /**
     * Constructs an AmbientLight object with the specified color and intensity.
     * The intensity is specified as a single scalar value that is applied to all three components (R, G, B).
     *
     * @param color The color of the ambient light.
     * @param d The intensity of the ambient light as a scalar value.
     */
    public AmbientLight(Color color, double d ){

        super(color.scale(d));
    }

}
