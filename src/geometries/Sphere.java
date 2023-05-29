package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static primitives.Util.alignZero;

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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result;
        //A special case that Ray starts in the center
        if(ray.getP0().equals(center)) {
            Point point = ray.getPoint(radius);
            double distance = ray.getP0().distance(point);

            if(alignZero(maxDistance - distance) >= 0){
                result = List.of(new GeoPoint(this, point));
                return result;
            }
            return null;
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
            Point point = ray.getPoint(t2);
            double distance = ray.getP0().distance(point);

            if (alignZero(maxDistance - distance) >= 0){
                // One intersection point is behind the ray, the other is in front
                result = List.of(new GeoPoint(this,point));
            }

            result = null;
        } else {
            // Both intersection points are in front of the ray
            result = List.of(new GeoPoint(this ,ray.getPoint(t1)),new GeoPoint(this ,ray.getPoint(t2)));
        }

        return result;
    }
}
