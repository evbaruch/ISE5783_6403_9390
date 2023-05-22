package parser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;

import geometries.*;
import primitives.*;
import scene.Scene;
import lighting.AmbientLight;

import static java.nio.file.Files.exists;

public class Json {

    public static Scene parseSceneFromJson(String filename, Scene scene) throws Exception {

        Path path = Paths.get(filename);
        if (!exists(path)) {
            return null;
        }

        try {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(filename);
            JsonScene jsonScene = gson.fromJson(fileReader, JsonScene.class);

            // Parsing background color
            String background = jsonScene.scene.background;
            Point temp = parsePointFromString(background);
            scene.setBackground(new Color(temp.getX(), temp.getY(), temp.getZ()));

            // Parsing ambient light intensity

            Point ambientLightIntensity = parsePointFromString(jsonScene.scene.ambientLight.intensity);

            try {
                double ambientLightEmission = Double.parseDouble(jsonScene.scene.ambientLight.emission);

                scene.setAmbientLight(
                        new AmbientLight(
                                new Color(
                                        ambientLightIntensity.getX(),
                                        ambientLightIntensity.getY(),
                                        ambientLightIntensity.getZ()),
                                ambientLightEmission));
            } catch (Exception a) {

                scene.setAmbientLight(
                        new AmbientLight(
                                new Color(
                                        ambientLightIntensity.getX(),
                                        ambientLightIntensity.getY(),
                                        ambientLightIntensity.getZ()),
                                new Double3(1, 1, 1)));
            }


            // Parsing geometries
            GeometryData geometryData = jsonScene.scene.geometries;

            Geometries geometries = new Geometries();

            // Parsing sphere
            if(geometryData.sphere != null) {
                for (SphereData sphereData : geometryData.sphere) {
                    Point center = parsePointFromString(sphereData.center);
                    double radius = Double.parseDouble(sphereData.radius);


                    try {
                        Point emission = parsePointFromString(sphereData.emission);

                        geometries.add(new Sphere(center, radius)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Sphere(center, radius));
                    }


                }
            }

            // Parsing triangles
            if(geometryData.triangle != null) {
                for (TriangleData triangleData : geometryData.triangle) {
                    Point p0 = parsePointFromString(triangleData.p0);
                    Point p1 = parsePointFromString(triangleData.p1);
                    Point p2 = parsePointFromString(triangleData.p2);


                    try {
                        Point emission = parsePointFromString(triangleData.emission);

                        geometries.add(new Triangle(p0, p1, p2)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Triangle(p0, p1, p2));
                    }

                }
            }

            // Parsing planes
            if(geometryData.plane != null) {
                for (PlaneData planeData : geometryData.plane) {
                    Point p0 = parsePointFromString(planeData.p0);
                    Point p1 = parsePointFromString(planeData.p1);
                    Point p2 = parsePointFromString(planeData.p2);


                    try {
                        Point emission = parsePointFromString(planeData.emission);

                        geometries.add(new Plane(p0, p1, p2)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Plane(p0, p1, p2));
                    }

                }
            }

            if(geometryData.polygon != null) {
                // Parsing polygon
                for (PolygonData polygonData : geometryData.polygon) {
                    List<Point> vertices = new LinkedList<>();
                    for (String vertice : polygonData.vertices) {
                        vertices.add(parsePointFromString(vertice));
                    }
                    Point[] points = vertices.toArray(new Point[0]);


                    try {
                        Point emission = parsePointFromString(polygonData.emission);

                        geometries.add(new Polygon(points)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Polygon(points));
                    }

                }
            }


            // Parsing tube
            if(geometryData.tube != null) {
                for (TubeData tubeData : geometryData.tube) {

                    Point dir = parsePointFromString(tubeData.axisRay.dir);
                    Point p0 = parsePointFromString(tubeData.axisRay.p0);
                    double radius = Double.parseDouble(tubeData.radius);
                    Ray ray = new Ray(p0, new Vector(dir.getX(), dir.getY(), dir.getZ()));


                    try {
                        Point emission = parsePointFromString(tubeData.emission);

                        geometries.add(new Tube(ray, radius)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Tube(ray, radius));
                    }

                }
            }

            // Parsing cylinder
            if(geometryData.cylinder != null) {
                for (CylinderData cylinderData : geometryData.cylinder) {

                    Point dir = parsePointFromString(cylinderData.axisRay.dir);
                    Point p0 = parsePointFromString(cylinderData.axisRay.p0);
                    double radius = Double.parseDouble(cylinderData.radius);
                    Ray ray = new Ray(p0, new Vector(dir.getX(), dir.getY(), dir.getZ()));
                    double height = Double.parseDouble(cylinderData.height);

                    try {
                        Point emission = parsePointFromString(cylinderData.emission);

                        geometries.add(new Cylinder(ray, radius, height)
                                .setEmission(new Color(emission.getX(), emission.getY(), emission.getZ())));
                    } catch (Exception a) {

                        geometries.add(new Tube(ray, radius));
                    }

                }
            }



            scene.setGeometries(geometries);

            return scene;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Point parsePointFromString(String pointString) {
        String[] values = pointString.split(" ");
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        int z = Integer.parseInt(values[2]);
        return new Point(x, y, z);
    }

    static class CylinderData extends TubeData{
        String height;
    }

    static class GeometryData {
        SphereData[] sphere;
        TriangleData[] triangle;
        PlaneData[] plane;
        PolygonData[] polygon;
        TubeData[] tube;
        CylinderData[] cylinder;
    }

    static class PlaneData {
        String p0;
        String p1;
        String p2;
        String emission;
    }

    static class PolygonData {
        List<String> vertices;
        String emission;
    }

    static class RayData {
        String p0;
        String dir;
    }

    static class SceneData {
        String background;
        JsonAmbientLight ambientLight;
        GeometryData geometries;
    }

    static class SphereData {
        String center;
        String radius;
        String emission;
    }

    static class TriangleData {
        String p0;
        String p1;
        String p2;
        String emission;
    }

    static class TubeData {
        RayData axisRay;
        String radius;
        String emission;
    }

    static class JsonAmbientLight {
        String intensity;
        String emission;
    }

    static class JsonScene {
        SceneData scene;
    }
}
