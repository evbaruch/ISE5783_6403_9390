package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> intersectables;
    public Geometries() {
        this.intersectables = new LinkedList();
    }

    public Geometries(Intersectable... geometries) {
        this.intersectables = new LinkedList();
        for (Intersectable shape: geometries) {
            this.intersectables.add(shape);
        }
    }

    public void add(Intersectable... geometries){
        for (Intersectable shape: geometries) {
            this.intersectables.add(shape);
        }
    }

    @Override
    public List<Point> findIntsersections(Ray ray) {
        return null;
    }
}
