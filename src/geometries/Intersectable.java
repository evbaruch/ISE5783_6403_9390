package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
/** The Intersectable interface represents objects that can be intersected
        *
        * @author Evyatar */
public interface Intersectable {

    List<Point> findIntersections(Ray ray);

}
