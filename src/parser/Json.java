package parser;

import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import scene.Scene;
import lighting.AmbientLight;

import static java.nio.file.Files.exists;

class JsonScene {
    SceneData scene;
}

class SceneData {
    String background;
    JsonAmbientLight ambientLight;
    GeometryData geometries;
}

class JsonAmbientLight {
    String intensity;
    String emission;
}

class GeometryData {
    SphereData[] sphere;
    TriangleData[] triangle;
}

class SphereData {
    String center;
    String radius;
    String emission;
}

class TriangleData {
    String p0;
    String p1;
    String p2;
    String emission;
}
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

            // Parsing triangles
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
}
