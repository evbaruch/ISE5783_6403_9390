package renderer;

import geometries.Geometries;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.WHITE;

public class improvementsTest {
    @Test
    public void trianglesSphere() {

        Geometries geometries = new Geometries(
                new Triangle(
                        new Point(-150, -150, -115),
                        new Point(150, -150, -135),
                        new Point(75, 75, -150)
                )
                        .setMaterial(new Material().setKs(0.8).setShininess(60)),

                new Triangle(
                        new Point(-150, -150, -115),
                        new Point(-70, 70, -140),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKs(0.8).setShininess(60)),

                new Sphere(
                        new Point(0, 0, -11),
                        30d)
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
        );

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setSoftShade(30)
                .setAmbientLight(new AmbientLight(new Color(WHITE), 0.15))
                .setLights(
                        new SpotLight(
                                new Color(700, 400, 400),
                                new Point(40, 40, 115),
                                new Vector(-1, -1, -4))
                                .setRadius(20)
                                .setKl(4E-4).setKq(2E-5))
                .build();



        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setVPSize(200, 200)
                .setVPDistance(1000)
                .setRayTracer(new RayTracerBasic(scene))
                .setRayNum(30);


        camera.setImageWriter(new ImageWriter("improvementsShadowTrianglesSphere", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0)) //
                .setVPSize(200, 200)
                .setVPDistance(1000)
                .setRayNum(30);

        Geometries gGeometries = new Geometries(
                new Triangle(
                        new Point(-150, -150, -115),
                        new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(
                        new Point(-150, -150, -115),
                        new Point(-70, 70, -140),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(
                        new Point(60, 50, -50), 30d)
                        .setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));


        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(gGeometries)
                .setSoftShade(30)
                .setLights(
                        new SpotLight(
                                new Color(700, 400, 400),
                                new Point(60, 50, 0),
                                new Vector(0, 0, -1))
                                .setRadius(10)
                                .setKl(4E-5).setKq(2E-7))
                .build();



        ImageWriter imageWriter = new ImageWriter("improvementsRefractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }
}
