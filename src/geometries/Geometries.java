package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable {
    List<Intersectable> intersectables;

    public Geometries() {
        this.intersectables = new LinkedList();
    }

    public Geometries(Intersectable... geometries) {
        this();
        add(geometries);
    }

    public void add(Intersectable... geometries) {
        Collections.addAll(this.intersectables, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {

        List<GeoPoint> result = null;
        for (Intersectable shape : intersectables) {
            List<GeoPoint> shapePoints = shape.findGeoIntersections(ray, maxDistance);
            if (shapePoints != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(shapePoints);
            }
        }
        return result;
    }

}
