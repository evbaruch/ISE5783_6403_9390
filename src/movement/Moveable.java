package movement;

import geometries.Geometries;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;

public abstract class Moveable {

    protected Vector Vto;
    protected Vector Vup;
    protected Vector Vright;
    protected Point location;

    /**
     * Moves the camera up or down by the specified amount.
     *
     * @param num The amount to move the camera up or down.
     * @return A new camera object with the updated position and orientation.
     */
    public Point upDown(double num){
        return location.add(this.Vup.scale(num));
    }

    /**
     * Moves the camera in or out by the specified amount.
     *
     * @param num The amount to move the camera in or out.
     * @return A new camera object with the updated position and orientation.
     */
    public Point inOut(double num){
        return location.add(this.Vto.scale(num));
    }

    /**
     * Moves the camera right or left by the specified amount.
     *
     * @param num The amount to move the camera right or left.
     * @return A new camera object with the updated position and orientation.
     */
    public Point rightLeft(double num){
        return location.add(this.Vright.scale(num));
    }

    public Moveable move(double radius , Point axis, double alpha , double beta) {
        //alpha
        double height = Math.sin(Math.toRadians(alpha))*radius;
        double length = -Math.sin(Math.toRadians(180-alpha-90))*radius + radius;

        location = inOut(length);
        location = upDown(height);

        // Calculate the new direction vector from the axis to the point on the sphere
        Vto = axis.subtract(location).normalize();
        Vup = this.Vright.crossProduct(this.Vto).normalize();

        //beta
        height = Math.sin(Math.toRadians(beta))*radius;
        length = -Math.sin(Math.toRadians(180-beta-90))*radius + radius;

        location = inOut(length);
        location = rightLeft(height);

        // Calculate the new direction vector from the axis to the point on the sphere
        Vto = axis.subtract(location).normalize();
        Vright = this.Vup.crossProduct(this.Vto).normalize();

        return this;
    }
}
