package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

public class Camera {
    private Point location;

    private Vector Vto;
    private Vector Vup;
    private Vector Vright;

    public Camera(Point initialPoint, Vector vto, Vector vup) {
         if( alignZero(vto.dotProduct(vup)) == 0) {
             throw new IllegalArgumentException("the vectors ain't perpendicular to each other");
         }

        this.Vto = vto.normalize();
        this.Vup = vup.normalize();

        this.Vright = this.Vto.crossProduct(this.Vup).normalize(); // need to check if the order of the crossProduct is right

        this.location = initialPoint;

    }

    public Camera setVPSize(double width, double height) {

        return this;
    }

    public Camera setVPDistance(double distance) {

        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {

        return null;
    }


}
