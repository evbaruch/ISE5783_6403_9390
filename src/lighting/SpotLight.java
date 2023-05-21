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

    private double BeamWidth;

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
        this.BeamWidth = 1;
    }


    /**
     * Sets the narrowness of the spotlight beam.
     *
     * @param n The level of narrowness to set.
     * @return This SpotLight object.
     */
    public SpotLight setNarrowBeam(int n) {
        // Calculate the angle of the spotlight beam based on the provided level of narrowness
        this.BeamWidth = n;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.pow(Math.max(0,this.direction.normalize().dotProduct(super.getL(p))),this.BeamWidth));
    }

}
