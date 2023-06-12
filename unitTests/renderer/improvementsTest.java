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
                .setSoftShade(50)
                .setAmbientLight(new AmbientLight(new Color(WHITE), 0.15))
                .setLights(
                        new SpotLight(
                                new Color(700, 400, 400),
                                new Point(40, 40, 115),
                                new Vector(-1, -1, -4))
                                .setRadius(5)
                                .setKl(4E-4).setKq(2E-5))
                .build();



        Camera camera = new Camera(
                new Point(0, 0, 1000),
                new Vector(0, 0, -1),
                new Vector(0, 1, 0))
                .setVPSize(200, 200)
                .setVPDistance(1000)
                .setRayTracer(new RayTracerBasic(scene))
                .setRayNum(50);


        camera.setImageWriter(new ImageWriter("improvementsShadowTrianglesSphere", 600, 600)) //
                .renderImage() //
                .writeToImage();
    }
}
