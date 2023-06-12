package primitives;

import renderer.Camera;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Beam {
    List<Ray> rays;

    public Beam() {
        this.rays = new LinkedList();
    }

    public void add(Ray... rays){
        Collections.addAll(this.rays , rays);
    }
    public List<Ray> getRays() {
        return rays;
    }

//    public List<Ray> constructBeam(double Rx, double Ry, Point Pij , int precision ,Vector Vup,Vector Vright ,Point location ) {
//        Random random = new Random();
//        for (int k = 0; k < precision; k++) {
//
//            double xJitt = random.nextDouble(-Rx /2, Rx /2);
//            double yJitt = random.nextDouble(-Ry /2, Ry /2);
//
//            //Returns the ray from the point by i j
//            this.add(CalcRay(Pij.movePointOnViewPlane(Vup ,Vright,yJitt,xJitt, Rx, Ry),location));
//        }
//        return this.getRays();
//    }

    public List<Ray> constructBeam(double Rx, double Ry, Point Pij , int precision , Ray ray) {
        Random random = new Random();
        for (int k = 0; k < precision; k++) {

            double xJitt = random.nextDouble(-Rx /2, Rx /2);
            double yJitt = random.nextDouble(-Ry /2, Ry /2);

            //Returns the ray from the point by i j
            this.add(CalcRay(Pij.movePointOnViewPlane(ray.getVup(),ray.getVright(),yJitt,xJitt, Rx, Ry),ray.getP0().add(ray.getDir().scale(0.01))));
        }
        return this.getRays();
    }

    public Ray CalcRay (Point point,Point base){
        Vector newVector = point.subtract(base);
        // Create and return the updated ray with the same direction
        return new Ray(base, newVector);
    }


}