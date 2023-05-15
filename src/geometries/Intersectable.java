package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/** The Intersectable interface represents objects that can be intersected
        *
        * @author Evyatar */
public abstract class Intersectable {

   public static class GeoPoint {
      public Geometry geometry;
      public Point point;

      public GeoPoint(Geometry geometry, Point point) {
         this.geometry = geometry;
         this.point = point;
      }

      @Override
      public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         GeoPoint geoPoint = (GeoPoint) o;
         return geometry.equals(geoPoint.geometry) && point.equals(geoPoint.point);
      }

      @Override
      public String toString() {
         return "GeoPoint{" +
                 "geometry=" + geometry +
                 ", point=" + point +
                 '}';
      }
   }

   public List<Point> findIntersections(Ray ray) {
      var geoList = findGeoIntersections(ray);
      return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
   }

   public abstract List<GeoPoint> findGeoIntersections(Ray ray);
   protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

}
