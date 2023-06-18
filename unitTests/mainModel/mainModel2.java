package mainModel;

import geometries.*;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class mainModel2 {



    @Test
    void diamond() {

        Material material = new Material()
                .setRefractiveIndex(Material.DIAMOND_REFRACTIVE_INDEX)
                .setShininess(7)
                .setKd(0.1)//diffuse
                .setKs(0.9)//specular
                .setKt(0.85)//transmission
                .setKr(0);//reflection

        Color color = new Color(1, 1, 10);

        Geometries geometries = new Geometries(
                new Polygon(
                        new Point(5.700000, -0.000000, 3.225308),
                        new Point(4.030509, 4.030509, 3.225308),
                        new Point(0.000000, 5.700000, 3.225308),
                        new Point(-4.030509, 4.030509, 3.225308),
                        new Point(-5.700000, 0.000000, 3.225308),
                        new Point(-4.030509, -4.030508, 3.225308),
                        new Point(-0.000000, -5.700000, 3.225308),
                        new Point(4.030508, -4.030509, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(6.880509, -2.850000, 2.413967),
                        new Point(4.030508, -4.030509, 3.225308),
                        new Point(5.700000, -0.000000, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(6.880508, 2.850000, 2.413967),
                        new Point(5.700000, -0.000000, 3.225308),
                        new Point(4.030509, 4.030509, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(2.850000, 6.880508, 2.413967),
                        new Point(4.030509, 4.030509, 3.225308),
                        new Point(0.000000, 5.700000, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-2.850000, 6.880509, 2.413967),
                        new Point(0.000000, 5.700000, 3.225308),
                        new Point(-4.030508, 4.030509, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-6.880508, 2.850000, 2.413967),
                        new Point(-4.030508, 4.030509, 3.225308),
                        new Point(-5.700000, 0.000000, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-6.880509, -2.849999, 2.413967),
                        new Point(-5.700000, 0.000000, 3.225308),
                        new Point(-4.030509, -4.030508, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-2.850000, -6.880508, 2.413967),
                        new Point(-4.030509, -4.030508, 3.225308),
                        new Point(-0.000000, -5.700000, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(2.849999, -6.880509, 2.413967),
                        new Point(-0.000000, -5.700000, 3.225308),
                        new Point(4.030508, -4.030509, 3.225308))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(5.700000, -0.000000, 3.225308),
                        new Point(6.880509, -2.850000, 2.413967),
                        new Point(6.880509, 2.850001, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000002, -0.000001, 0.269999),
                        new Point(6.880509, -2.850000, 2.413967),
                        new Point(6.880509, 2.850001, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(4.030509, 4.030509, 3.225308),
                        new Point(6.880509, 2.850000, 2.413967),
                        new Point(2.850000, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071069, 7.071068, 0.269999),
                        new Point(6.880509, 2.850000, 2.413967),
                        new Point(2.850000, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 5.700000, 3.225308),
                        new Point(2.850001, 6.880509, 2.413967),
                        new Point(-2.850001, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000001, 0.269999),
                        new Point(2.850001, 6.880509, 2.413967),
                        new Point(-2.850001, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-4.030508, 4.030509, 3.225308),
                        new Point(-2.849999, 6.880509, 2.413967),
                        new Point(-6.880509, 2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071069, 0.269999),
                        new Point(-2.849999, 6.880509, 2.413967),
                        new Point(-6.880509, 2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-5.700000, 0.000000, 3.225308),
                        new Point(-6.880509, 2.850001, 2.413967),
                        new Point(-6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000001, 0.000001, 0.269999),
                        new Point(-6.880509, 2.850001, 2.413967),
                        new Point(-6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-4.030509, -4.030508, 3.225308),
                        new Point(-6.880509, -2.849999, 2.413967),
                        new Point(-2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071069, -7.071068, 0.269999),
                        new Point(-6.880509, -2.849999, 2.413967),
                        new Point(-2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, -5.700000, 3.225308),
                        new Point(-2.850001, -6.880509, 2.413967),
                        new Point(2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000001, 0.269999),
                        new Point(-2.850001, -6.880509, 2.413967),
                        new Point(2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(4.030508, -4.030509, 3.225308),
                        new Point(2.849999, -6.880510, 2.413967),
                        new Point(6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, -7.071069, 0.269999),
                        new Point(2.849999, -6.880510, 2.413967),
                        new Point(6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000002, -0.000001, 0.269999),
                        new Point(9.529282, -3.947159, 0.005913),
                        new Point(6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000002, -0.000001, 0.269999),
                        new Point(9.529282, 3.947158, 0.005913),
                        new Point(6.880509, 2.850001, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071069, 7.071068, 0.269999),
                        new Point(9.529282, 3.947158, 0.005913),
                        new Point(6.880509, 2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071069, 7.071068, 0.269999),
                        new Point(3.947158, 9.529282, 0.005913),
                        new Point(2.850000, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000001, 0.269999),
                        new Point(3.947158, 9.529282, 0.005913),
                        new Point(2.850001, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000001, 0.269999),
                        new Point(-3.947158, 9.529282, 0.005913),
                        new Point(-2.850001, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071069, 0.269999),
                        new Point(-3.947158, 9.529282, 0.005913),
                        new Point(-2.849999, 6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071069, 0.269999),
                        new Point(-9.529282, 3.947158, 0.005913),
                        new Point(-6.880509, 2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000001, 0.000001, 0.269999),
                        new Point(-9.529282, 3.947158, 0.005913),
                        new Point(-6.880509, 2.850001, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000001, 0.000001, 0.269999),
                        new Point(-9.529282, -3.947157, 0.005913),
                        new Point(-6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071069, -7.071068, 0.269999),
                        new Point(-9.529282, -3.947157, 0.005913),
                        new Point(-6.880509, -2.849999, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071069, -7.071068, 0.269999),
                        new Point(-3.947159, -9.529282, 0.005913),
                        new Point(-2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000001, 0.269999),
                        new Point(-3.947159, -9.529282, 0.005913),
                        new Point(-2.850001, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000001, 0.269999),
                        new Point(3.947157, -9.529282, 0.005913),
                        new Point(2.850000, -6.880509, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, -7.071069, 0.269999),
                        new Point(3.947157, -9.529282, 0.005913),
                        new Point(2.849999, -6.880510, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, -7.071069, 0.269999),
                        new Point(9.529282, -3.947159, 0.005913),
                        new Point(6.880509, -2.850000, 2.413967))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000006, -0.000001, -0.269994),
                        new Point(9.529282, -3.947159, 0.005913),
                        new Point(1.799999, -0.745584, -7.335573))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000006, -0.000001, -0.269994),
                        new Point(9.529282, 3.947158, 0.005913),
                        new Point(1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, 7.071068, -0.269999),
                        new Point(9.529282, 3.947158, 0.005913),
                        new Point(1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, 7.071068, -0.269999),
                        new Point(3.947158, 9.529282, 0.005913),
                        new Point(0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000000, -0.269999),
                        new Point(3.947158, 9.529282, 0.005913),
                        new Point(0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000000, -0.269999),
                        new Point(-3.947158, 9.529282, 0.005913),
                        new Point(-0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071068, -0.269999),
                        new Point(-3.947158, 9.529282, 0.005913),
                        new Point(-0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071068, -0.269999),
                        new Point(-9.529282, 3.947158, 0.005913),
                        new Point(-1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000000, 0.000001, -0.269999),
                        new Point(-9.529282, 3.947158, 0.005913),
                        new Point(-1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000000, 0.000001, -0.269999),
                        new Point(-9.529282, -3.947157, 0.005913),
                        new Point(-1.800000, -0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, -7.071068, -0.269999),
                        new Point(-9.529282, -3.947157, 0.005913),
                        new Point(-1.800000, -0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, -7.071068, -0.269999),
                        new Point(-3.947159, -9.529282, 0.005913),
                        new Point(-0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000000, -0.269999),
                        new Point(-3.947159, -9.529282, 0.005913),
                        new Point(-0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000000, -0.269999),
                        new Point(3.947157, -9.529282, 0.005913),
                        new Point(0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071067, -7.071069, -0.269999),
                        new Point(3.947157, -9.529282, 0.005913),
                        new Point(0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071067, -7.071069, -0.269999),
                        new Point(9.529282, -3.947159, 0.005913),
                        new Point(1.800000, -0.745585, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(10.000006, -0.000001, -0.269994),
                        new Point(1.800000, 0.745584, -7.335572),
                        new Point(1.799999, -0.745584, -7.335573))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, 0.000000, -8.886552),
                        new Point(1.800000, 0.745584, -7.335572),
                        new Point(1.799999, -0.745584, -7.335573))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071068, 7.071068, -0.269999),
                        new Point(0.745584, 1.800000, -7.335572),
                        new Point(1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, -0.000000, -8.886552),
                        new Point(0.745584, 1.800000, -7.335572),
                        new Point(1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 10.000000, -0.269999),
                        new Point(-0.745584, 1.800000, -7.335572),
                        new Point(0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, -0.000000, -8.886552),
                        new Point(-0.745584, 1.800000, -7.335572),
                        new Point(0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, 7.071068, -0.269999),
                        new Point(-1.800000, 0.745584, -7.335572),
                        new Point(-0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, 0.000000, -8.886552),
                        new Point(-1.800000, 0.745584, -7.335572),
                        new Point(-0.745584, 1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-10.000000, 0.000001, -0.269999),
                        new Point(-1.800000, -0.745584, -7.335572),
                        new Point(-1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, 0.000000, -8.886552),
                        new Point(-1.800000, -0.745584, -7.335572),
                        new Point(-1.800000, 0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-7.071068, -7.071068, -0.269999),
                        new Point(-0.745584, -1.800000, -7.335572),
                        new Point(-1.800000, -0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, -0.000000, -8.886552),
                        new Point(-0.745584, -1.800000, -7.335572),
                        new Point(-1.800000, -0.745584, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000001, -10.000000, -0.269999),
                        new Point(0.745584, -1.800000, -7.335572),
                        new Point(-0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(0.000000, -0.000000, -8.886552),
                        new Point(0.745584, -1.800000, -7.335572),
                        new Point(-0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(7.071067, -7.071069, -0.269999),
                        new Point(1.800000, -0.745585, -7.335572),
                        new Point(0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Triangle(
                        new Point(-0.000000, -0.000000, -8.886552),
                        new Point(1.800000, -0.745585, -7.335572),
                        new Point(0.745584, -1.800000, -7.335572))
                        .setEmission(color)
                        .setMaterial(material),
                new Plane(
                    new Point(0, 0, -9),
                    new Vector(0,0,1))
                    .setEmission(new Color(100,100,100))
                     .setMaterial(new Material()
                             .setShininess(60)
                             .setKd(0.2)//diffuse
                             .setKs(0.2)//specular
                             .setKt(0)//transmission
                             .setKr(0.5))//reflection
        );

        Camera camera = new Camera(new Point(0, 130, 30), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(500).setRayNum(100).tiltCamera(-15).adjustHorizontalRotation(0);



        //Camera camera = new Camera(new Point(0, 0, 100), new Vector(0, 0, -1), new Vector(0, 1, 0))
        //        .setVPSize(150, 150).setVPDistance(500).tiltCamera(0).adjustHorizontalRotation(0);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.1))
                .setLights(
                        new PointLight(
                                new Color(200,100,100),
                                new Point(10,-1,2)
                        ),
                        new PointLight(
                                new Color(100,200,100),
                                new Point(-10,-1,2)
                        ),
                        new SpotLight(
                                new Color(150,50,100),
                                new Point(-5,10,10),
                                new Vector(1,-2,-2)
                        ),
                        new SpotLight(
                                new Color(50,150,100),
                                new Point(5,10,10),
                                new Vector(-1,-2,-2)
                        ),
                        new DirectionalLight(
                                new Color(100,100,100),
                                new Vector(0,0,-1)
                        ))
                .build();


        camera.setImageWriter(new ImageWriter("diamond", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
