package lighting;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.Collections;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class LightsTests {
    private final Scene scene1 = new Scene.SceneBuilder("Test scene").build();
    private final Scene scene2 = new Scene.SceneBuilder("Test scene")
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15))).build();

    private final Camera camera1 = new Camera(new Point(0, 0, 1000),
            new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(150, 150).setVPDistance(1000);
    private final Camera camera2 = new Camera(new Point(0, 0, 1000),
            new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVPSize(200, 200).setVPDistance(1000);

    private static final int SHININESS = 301;
    private static final double KD = 0.5;
    private static final Double3 KD3 = new Double3(0.2, 0.6, 0.4);

    private static final double KS = 0.5;
    private static final Double3 KS3 = new Double3(0.2, 0.4, 0.3);

    private final Material material = new Material().setKd(KD3).setKs(KS3).setShininess(SHININESS);
    private final Color trianglesLightColor = new Color(800, 500, 250);
    private final Color sphereLightColor = new Color(800, 500, 0);
    private final Color sphereColor = new Color(BLUE).reduce(2);

    private final Point sphereCenter = new Point(0, 0, -50);
    private static final double SPHERE_RADIUS = 50d;

    // The triangles' vertices:
    private final Point[] vertices =
            {
                    // the shared left-bottom:
                    new Point(-110, -110, -150),
                    // the shared right-top:
                    new Point(95, 100, -150),
                    // the right-bottom
                    new Point(110, -110, -150),
                    // the left-top
                    new Point(-75, 78, 100)
            };
    private final Point sphereLightPosition = new Point(-50, -50, 25);
    private final Point trianglesLightPosition = new Point(30, 10, -100);
    private final Vector trianglesLightDirection = new Vector(-2, -2, -2);

    private final Geometry sphere = new Sphere(sphereCenter, SPHERE_RADIUS)
            .setEmission(sphereColor).setMaterial(new Material().setKd(KD).setKs(KS).setShininess(SHININESS));
    private final Geometry triangle1 = new Triangle(vertices[0], vertices[1], vertices[2])
            .setMaterial(material);
    private final Geometry triangle2 = new Triangle(vertices[0], vertices[1], vertices[3])
            .setMaterial(material);

    /**
     * Produce a picture of a sphere lighted by a directional light
     */
    @Test
    public void sphereDirectional() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new DirectionalLight(sphereLightColor, new Vector(1, 1, -0.5)));

        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a point light
     */
    @Test
    public void spherePoint() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new PointLight(sphereLightColor, sphereLightPosition)
                .setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a spotlight
     */
    @Test
    public void sphereSpot() {
        scene1.getGeometries().add(sphere);
        scene1.getLights().add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5))
                .setKl(0.001).setKq(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a directional light
     */
    @Test
    public void trianglesDirectional() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new DirectionalLight(trianglesLightColor, trianglesLightDirection));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a point light
     */
    @Test
    public void trianglesPoint() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new PointLight(trianglesLightColor, trianglesLightPosition)
                .setKl(0.001).setKq(0.0002));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a spotlight
     */
    @Test
    public void trianglesSpot() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                .setKl(0.001).setKq(0.0001));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of a sphere lighted by a narrow spotlight
     */
    @Test
    public void sphereSpotSharp() {
        scene1.getGeometries().add(sphere);
        scene1.getLights()
                .add(new SpotLight(sphereLightColor, sphereLightPosition, new Vector(1, 1, -0.5))
                        .setNarrowBeam(10).setKl(0.001).setKq(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    /**
     * Produce a picture of two triangles lighted by a narrow spotlight
     */
    @Test
    public void trianglesSpotSharp() {
        scene2.getGeometries().add(triangle1, triangle2);
        scene2.getLights().add(new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                .setNarrowBeam(10).setKl(0.001).setKq(0.00004));

        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }


    @Test
    public void trianglesMulti() {
        scene2.getGeometries().add(triangle1, triangle2);
        Collections.addAll(scene2.getLights(),
                new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                        .setNarrowBeam(10).setKl(0.001).setKq(0.00004),
                new PointLight(trianglesLightColor, trianglesLightPosition)
                        .setKl(0.001).setKq(0.0002),
                new DirectionalLight(trianglesLightColor, trianglesLightDirection));

        ImageWriter imageWriter = new ImageWriter("triangles multi", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    @Test
    public void sphereMulti() {
        scene1.getGeometries().add(sphere);
        Collections.addAll(scene1.getLights(),
                new SpotLight(trianglesLightColor, trianglesLightPosition, trianglesLightDirection)
                        .setNarrowBeam(10).setKl(0.001).setKq(0.00004),
                new PointLight(trianglesLightColor, trianglesLightPosition)
                        .setKl(0.001).setKq(0.0002),
                new DirectionalLight(trianglesLightColor, trianglesLightDirection));

        ImageWriter imageWriter = new ImageWriter("sphere multi", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }

    @Test
    public void trianglesMultiLight() {
        scene2.getGeometries().add(triangle1, triangle2);

        Collections.addAll(scene2.getLights(),
                new SpotLight(new Color(600, 400, 1000), new Point(30, 30, -100), new Vector(-1, -1, -2))
                        .setNarrowBeam(10).setKl(0.001).setKq(0.00004),
                new PointLight(new Color(2000, 400, 600), new Point(100, -100, -140))
                        .setKl(0.001).setKq(0.0002),
                new DirectionalLight(new Color(500, 300, 500), new Vector(-1, 1, 1)));

        ImageWriter imageWriter = new ImageWriter("triangles multi light", 500, 500);
        camera2.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene2)) //
                .renderImage() //
                .writeToImage(); //
    }

    @Test
    public void sphereMultiLight() {
        scene1.getGeometries().add(sphere);
        Collections.addAll(scene1.getLights(),
                new SpotLight(new Color(400, 800, 200), new Point(0, 0, 10), new Vector(1, 1, -2))
                        .setNarrowBeam(10).setKl(0.001).setKq(0.00004),
                new PointLight(new Color(600, 400, 200), new Point(-50, 0, 0))
                        .setKl(0.001).setKq(0.0002),
                new DirectionalLight(new Color(300, 300, 100), new Vector(-1, -1, -1)));

        ImageWriter imageWriter = new ImageWriter("sphere multi light", 500, 500);
        camera1.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene1)) //
                .renderImage() //
                .writeToImage(); //
    }


}
