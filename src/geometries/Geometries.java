package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
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
    public List<Point> findIntersections(Ray ray) {

        List<Point> result = null;
        for (Intersectable shape : intersectables) {
            List<Point> shapePoints = shape.findIntersections(ray);
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
