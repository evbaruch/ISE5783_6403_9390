package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static primitives.Util.alignZero;

public class Camera {
    private Point location;
    private Vector Vto, Vup, Vright;
    private double width, height, distance;

    public Camera(Point initialPoint, Vector vto, Vector vup) {
         if( alignZero(vto.dotProduct(vup)) != 0) {
             throw new IllegalArgumentException("the vectors ain't perpendicular to each other");
         }

        this.Vto = vto.normalize();
        this.Vup = vup.normalize();

        this.Vright = this.Vto.crossProduct(this.Vup).normalize(); // need to check if the order of the crossProduct is right

        this.location = initialPoint;

    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
    }

    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {

        //Image center
        Point Pc = location.add(Vto.scale(distance));

        //Calculate the size of each pixel
        double Rx = width/nX;
        double Ry = height/nY;

        //Calculation of displacement according to i j
        double Xj = (j - (double)(nX - 1)/2)*Rx;
        double Yi = -(i - (double)(nY - 1)/2)*Ry;

        //Calculating the pixels function according to i j and gives a point
        Point Pij = Pc;
        if (alignZero(Xj) != 0){
            Pij = Pij.add(Vright.scale(Xj));
        }
        if (alignZero(Yi) != 0){
            Pij = Pij.add(Vup.scale(Yi));
        }


        //Calculation of the vector from the point to the screen according to i j
        Vector Vij =  Pij.subtract(location);

        //Returns the ray from the point by i j
        return new Ray(location,Vij);
    }


}
