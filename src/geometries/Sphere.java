package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Sphere extends RadialGeometry {
    private Point center;

    public Sphere(Point center,double radius) {
        this.center = center;
        super.radius = radius;
    }

    public Vector getNormal(Point p) {
        Vector v = p.subtract(center);//There may be an exception here that the vector is zero
        return v.normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> result;
        //A special case that Ray starts in the center
        if(ray.getP0().equals(center)) {
            result = List.of(
                    ray.getP0().add(ray.getDir().scale(radius))
            );
            return result;
        }

        Vector u = center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir());
        double dSquared = u.lengthSquared() - tm * tm;

        if (dSquared >= radius * radius) {
            // No collision
            return null;
        }

        double th = Math.sqrt(radius * radius - dSquared);
        double t1 = tm - th;
        double t2 = tm + th;

        if (t2 <= 0) {
            // Both intersection points are behind the ray
            return null;
        }


        if (t1 <= 0) {
            // One intersection point is behind the ray, the other is in front
            result = List.of(ray.getP0().add(ray.getDir().scale(t2)));
        } else {
            // Both intersection points are in front of the ray
            result = List.of(
                    ray.getP0().add(ray.getDir().scale(t1)),
                    ray.getP0().add(ray.getDir().scale(t2))
            );
        }

        return result;
    }


}
