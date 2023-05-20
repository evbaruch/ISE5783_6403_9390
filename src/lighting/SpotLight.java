package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;



/**
 * Represents a spotlight in a lighting system.
 * A spotlight is a type of point light source that emits light in a specific direction with a narrow beam.
 */
public class SpotLight extends PointLight{

    private Vector direction;

    /**
     * Constructs a SpotLight object with the specified intensity, position, and direction.
     *
     * @param intensity The intensity of the spotlight.
     * @param position The position of the spotlight.
     * @param direction The direction in which the spotlight emits light, as a normalized vector.
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    /**
     * Sets the narrowness of the spotlight beam.
     *
     * @param n The level of narrowness to set.
     * @return This SpotLight object.
     */
    public SpotLight setNarrowBeam(int n) {
        // Calculate the angle of the spotlight beam based on the provided level of narrowness
        double beamWidth = Math.toRadians(n);

        // Calculate the direction vector of the spotlight beam
        Vector beamDirection = direction.normalize();

        // Update the direction vector by narrowing the beam
        beamDirection = beamDirection.scale(1 / Math.tan(beamWidth / 2));

        // Set the updated direction vector
        this.direction = beamDirection;

        return this;
    }


}
