package parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.SplittableRandom;

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
            Point  backgroundPoint= parsePointFromString(background);

            // Parsing ambient light intensity

            Point ambientLightIntensity = parsePointFromString(jsonScene.scene.ambientLight.intensity);
            AmbientLight ambientLight;
            try {
                double ambientLightEmission = Double.parseDouble(jsonScene.scene.ambientLight.emission);

                ambientLight = new AmbientLight(new Color(ambientLightIntensity.getX(),ambientLightIntensity.getY(),ambientLightIntensity.getZ()),ambientLightEmission);

            } catch (Exception a) {

               Double3 scalar = new Double3(1, 1, 1);
                ambientLight = new AmbientLight(new Color(ambientLightIntensity.getX(),ambientLightIntensity.getY(),ambientLightIntensity.getZ()),scalar);

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



            return new Scene.SceneBuilder(scene.getName())
                    .setBackground(
                            new Color(
                                    backgroundPoint.getX()
                                    ,backgroundPoint.getY()
                                    ,backgroundPoint.getZ()
                            )
                    )
                    .setAmbientLight(ambientLight)
                    .setGeometries(geometries)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    /**
//     * Inactive
//     * @param scene
//     * @param filename
//     * @throws Exception
//     */
//    public static void createJsonFromScene(Scene scene, String filename) throws Exception {
//        JsonScene jsonScene = new JsonScene();
//
//        // Convert background color to string
//        Color background = scene.background;
//        String stringBackground = String.valueOf(background);
//
//        jsonScene.scene.background = parseStringFromRGB(stringBackground);
//
//        // Convert ambient light intensity to string
//        AmbientLight ambientLight = scene.ambientLight;
//        Color ambientIntensity = ambientLight.getIntensity();
//        String stringAmbientIntensity  = String.valueOf(ambientIntensity);
//        //Point backgroundPoint = parsePointFromString(stringBackground);
//        jsonScene.scene.ambientLight.intensity = parseStringFromRGB(stringAmbientIntensity);
//
//        // Convert ambient light emission to string
//        // jsonScene.scene.ambientLight.emission = String.valueOf(ambientLight.);
//
//        // Convert geometries
////        Geometries geometries = scene.geometries;
////
////        // Convert spheres
////       // for (Geometry geometry: geometries)
////        if (geometries != null) {
////            jsonScene.scene.geometries.sphere = new SphereData[geometries.getSpheres().size()];
////
////            for (int i = 0; i < geometries.getSpheres().size(); i++) {
////                Sphere sphere = geometries.getSpheres().get(i);
////                SphereData sphereData = new SphereData();
////
////                sphereData.center = parseStringFromPoint(sphere.getCenter());
////                sphereData.radius = String.valueOf(sphere.getRadius());
////                sphereData.emission = parseStringFromPoint(sphere.getEmission().toPoint());
////
////                jsonScene.scene.geometries.sphere[i] = sphereData;
////            }
////        }
////
////        // Convert triangles
////        if (geometries != null) {
////            jsonScene.scene.geometries.triangle = new TriangleData[geometries.getTriangles().size()];
////
////            for (int i = 0; i < geometries.getTriangles().size(); i++) {
////                Triangle triangle = geometries.getTriangles().get(i);
////                TriangleData triangleData = new TriangleData();
////
////                triangleData.p0 = parseStringFromPoint(triangle.getP0());
////                triangleData.p1 = parseStringFromPoint(triangle.getP1());
////                triangleData.p2 = parseStringFromPoint(triangle.getP2());
////                triangleData.emission = parseStringFromPoint(triangle.getEmission().toPoint());
////
////                jsonScene.scene.geometries.triangle[i] = triangleData;
////            }
////        }
//
//        // Add similar conversion for other geometry types
//
//        Gson gson = new Gson();
//        String jsonString = gson.toJson(jsonScene);
//
//        FileWriter fileWriter = new FileWriter("files/"+filename+".json");
//        fileWriter.write(jsonString);
//        fileWriter.close();
//    }

    private static Point parsePointFromString(String pointString) {
        String[] values = pointString.split(" ");
        int x = Integer.parseInt(values[0]);
        int y = Integer.parseInt(values[1]);
        int z = Integer.parseInt(values[2]);
        return new Point(x, y, z);
    }

    public static String parseStringFromRGB(String rgbString) {
        // Remove the "rgb:" prefix
        String numericString = rgbString.replace("rgb:", "");

        // Extract the numeric values using regular expressions
        String[] values = numericString.split(",|\\(|\\)");
        StringBuilder resultBuilder = new StringBuilder();

        for (String value : values) {
            try {
                double numericValue = Double.parseDouble(value);
                resultBuilder.append((int) numericValue).append(" ");
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }

        // Remove trailing space and return the result
        return resultBuilder.toString().trim();
    }


    private static String parseStringFromPoint(Point point) {
        return point.getX() + " " + point.getY() + " " + point.getZ();
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
        JsonAmbientLight ambientLight = new JsonAmbientLight();
        GeometryData geometries = new GeometryData();
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
        RayData axisRay = new RayData();
        String radius;
        String emission;
    }

    static class JsonAmbientLight {
        String intensity;
        String emission;
    }

    static class JsonScene {
        SceneData scene = new SceneData();
    }
}
