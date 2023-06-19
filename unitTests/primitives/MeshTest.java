package primitives;

import geometries.Triangle;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MeshTest {
    @Test
    void testEquals() {
        Mesh mesh = new Mesh();

        List<Triangle> triangles1 = new LinkedList<>();
        List<Triangle> triangles2 = new LinkedList<>();

        Collections.addAll(triangles1,
                new Triangle(
                        new Point(0.0, 0.0, 0.0),
                        new Point(0.0, -10.0, 0.0),
                        new Point(0.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(0.0, 0.0, 10.0),
                        new Point(0.0, -10.0, 0.0),
                        new Point(0.0, -10.0, 10.0)
                ),
                new Triangle(
                        new Point(10.0, 0.0, 0.0),
                        new Point(0.0, 0.0, 0.0),
                        new Point(10.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(10.0, 0.0, 10.0),
                        new Point(0.0, 0.0, 0.0),
                        new Point(0.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(10.0, -10.0, 0.0),
                        new Point(10.0, 0.0, 0.0),
                        new Point(10.0, -10.0, 10.0)
                ),
                new Triangle(
                        new Point(10.0, -10.0, 10.0),
                        new Point(10.0, 0.0, 0.0),
                        new Point(10.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(0.0, -10.0, 0.0),
                        new Point(10.0, -10.0, 0.0),
                        new Point(0.0, -10.0, 10.0)
                ),
                new Triangle(
                        new Point(0.0, -10.0, 10.0),
                        new Point(10.0, -10.0, 0.0),
                        new Point(10.0, -10.0, 10.0)
                ),
                new Triangle(
                        new Point(0.0, -10.0, 10.0),
                        new Point(10.0, -10.0, 10.0),
                        new Point(0.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(0.0, 0.0, 10.0),
                        new Point(10.0, -10.0, 10.0),
                        new Point(10.0, 0.0, 10.0)
                ),
                new Triangle(
                        new Point(10.0, -10.0, 0.0),
                        new Point(0.0, -10.0, 0.0),
                        new Point(10.0, 0.0, 0.0)
                ),
                new Triangle(
                        new Point(10.0, 0.0, 0.0),
                        new Point(0.0, -10.0, 0.0),
                        new Point(0.0, 0.0, 0.0)
                )
        );

        mesh = Mesh.trianglesToMesh(triangles1);

        mesh = Mesh.edgeCollapseAlgorithm(mesh,10);

        triangles2 = Mesh.meshToTriangles(mesh);
    }
}
