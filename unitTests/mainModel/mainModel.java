package mainModel;

import geometries.*;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class mainModel {

    private Scene scene;
    @Test
    void diamond() {

    }

    @Test
    public void room(){
        Camera camera = new Camera(new Point(20, 20, 700), new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPSize(200, 200).setVPDistance(1000).tiltCamera(-2).adjustHorizontalRotation(-2);

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
                        // Represents the floor of the room
                        new Point(-60.0, -50.0, 200.0),
                        new Point(60.0, -50.0, 200.0),
                        new Point(60.0, -50.0, -200.0),
                        new Point(-60.0, -50.0, -200.0)
                )
                        .setEmission(new Color(79,21,7).scale(5).add(new Color(WHITE).reduce(4)).add(new Color(GRAY).reduce(4)))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-60.0, -49.6, 200.0),
                        new Point(-55.0, -49.6, 200.0),
                        new Point(-55.0, -49.6, -200.0),
                        new Point(-60.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-50.0, -49.6, 200.0),
                        new Point(-45.0, -49.6, 200.0),
                        new Point(-45.0, -49.6, -200.0),
                        new Point(-50.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-40.0, -49.6, 200.0),
                        new Point(-35.0, -49.6, 200.0),
                        new Point(-35.0, -49.6, -200.0),
                        new Point(-40.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-30.0, -49.6, 200.0),
                        new Point(-25.0, -49.6, 200.0),
                        new Point(-25.0, -49.6, -200.0),
                        new Point(-30.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-20.0, -49.6, 200.0),
                        new Point(-15.0, -49.6, 200.0),
                        new Point(-15.0, -49.6, -200.0),
                        new Point(-20.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(-10.0, -49.6, 200.0),
                        new Point(-5.0, -49.6, 200.0),
                        new Point(-5.0, -49.6, -200.0),
                        new Point(-10.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(0.0, -49.6, 200.0),
                        new Point(5.0, -49.6, 200.0),
                        new Point(5.0, -49.6, -200.0),
                        new Point(0.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(10.0, -49.6, 200.0),
                        new Point(15.0, -49.6, 200.0),
                        new Point(15.0, -49.6, -200.0),
                        new Point(10.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(20.0, -49.6, 200.0),
                        new Point(25.0, -49.6, 200.0),
                        new Point(25.0, -49.6, -200.0),
                        new Point(20.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(30.0, -49.6, 200.0),
                        new Point(35.0, -49.6, 200.0),
                        new Point(35.0, -49.6, -200.0),
                        new Point(30.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(40.0, -49.6, 200.0),
                        new Point(45.0, -49.6, 200.0),
                        new Point(45.0, -49.6, -200.0),
                        new Point(40.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the floor z of the room
                        new Point(50.0, -49.6, 200.0),
                        new Point(55.0, -49.6, 200.0),
                        new Point(55.0, -49.6, -200.0),
                        new Point(50.0, -49.6, -200.0)
                )
                        .setEmission(new Color(0,0,0))
                        .setMaterial(new Material()
                                .setKr(0.09).setKs(0.3).setKd(0.7).setKt(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the ceiling of the room
                        new Point(-60.0, 59.9, 200.0),
                        new Point(60.0, 59.9, 200.0),
                        new Point(60.0, 59.9, -200.0),
                        new Point(-60.0, 59.9, -200.0)
                )
                        .setEmission(new Color(WHITE).scale(0.8))
                        .setMaterial(new Material()
                                .setKr(0.1).setKs(0.3).setKd(0.7))
                ,
                new Polygon(
                        // Represents the wall on the positive Z-axis side of the room
                        new Point(-60.0, -50.0, -200.0),
                        new Point(60.0, -50.0, -200.0),
                        new Point(60.0, 60.0, -200.0),
                        new Point(-60.0, 60.0, -200.0)
                )
                        .setEmission(new Color(YELLOW).scale(4).add(new Color(WHITE).reduce(1.4)))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7))
                ,
                new Plane(new Point(0,0,10000),new Vector(0,0,1)).setEmission(new Color(YELLOW).scale(4).add(new Color(WHITE).reduce(1.4)))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7))
                ,
                new Polygon(
                        // Represents the mirror
                        new Point(-20.0, -35.0, -199.0),
                        new Point(20.0, -35.0, -199.0),
                        new Point(20.0, 20.0, -199.0),
                        new Point(-20.0, 20.0, -199.0)
                )
                        .setEmission(new Color(GRAY))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7).setKr(0.4).setRefractiveIndex(Material.GLASS_REFRACTIVE_INDEX))
                ,
                new Polygon(
                        // Represents the wall on the positive y-axis side of the room

                        new Point(-60.0, -50.0, 200.0),
                        new Point(-60.0, -50.0, -200.0),
                        new Point(-60.0, 60.0, -200.0),
                        new Point(-60.0, 60.0, 200.0)
                )
                        .setEmission(new Color(GREEN).scale(4).add(new Color(WHITE).reduce(1.4)))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7)),

                new Polygon(
                        // Represents the wall on the negative Y-axis side of the room
                        new Point(60.0, -50.0, 200.0),
                        new Point(60.0, -50.0, -200.0),
                        new Point(60.0, 60.0, -200.0),
                        new Point(60.0, 60.0, 200.0)
                )
                        .setEmission(new Color(GREEN).scale(4).add(new Color(WHITE).reduce(1.4)))
                        .setMaterial(new Material()
                                .setKs(0.3).setKd(0.7))

                ,
                new Sphere(
                        new Point(0, 50, -50), 3d)
                        .setEmission(new Color(YELLOW).add(new Color(WHITE).reduce(7)))
                        .setMaterial(
                                new Material()
                                        .setKd(0.8)
                                        .setKs(0.6)
                                        .setKt(0.09).setKr(0.4)
                                        .setShininess(100))
                ,
                // Table top
                new Polygon(
                new Point(-30.0, -30.0, 50.0),
                new Point(30.0, -30.0, 50.0),
                new Point(30.0, -30.0, -50.0),
                new Point(-30.0, -30.0, -50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                .setEmission(new Color(139, 69, 19))
                .setMaterial(new Material()
                        .setKd(0.7).setKs(0.3))

                ,
                // Table top
                new Polygon(
                        new Point(-30.0, -32.0, 50.0),
                        new Point(30.0, -32.0, 50.0),
                        new Point(30.0, -32.0, -50.0),
                        new Point(-30.0, -32.0, -50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))

                ,
                // Table top
                new Polygon(
                        new Point(-30.0, -30.0, 50.0),
                        new Point(30.0, -30.0, 50.0),
                        new Point(30.0, -32.0, 50.0),
                        new Point(-30.0, -32.0, 50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))

                ,
                // Table top
                new Polygon(
                        new Point(-30.0, -30.0, -50.0),
                        new Point(30.0, -30.0, -50.0),
                        new Point(30.0, -32.0, -50.0),
                        new Point(-30.0, -32.0, -50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))

                ,
                // Table top
                new Polygon(
                        new Point(-30.0, -32.0, 50.0),
                        new Point(-30.0, -30.0, 50.0),
                        new Point(-30.0, -30.0, -50.0),
                        new Point(-30.0, -32.0, -50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))

                ,
                // Table top
                new Polygon(
                        new Point(30.0, -32.0, 50.0),
                        new Point(30.0, -30.0, 50.0),
                        new Point(30.0, -30.0, -50.0),
                        new Point(30.0, -32.0, -50.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19).add(new Color(WHITE).reduce(4)))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg left depth --
                new Polygon(
                        new Point(25.0, -30.0, 47.0),
                        new Point(25.0, -50.0, 47.0),
                        new Point(27.0, -50.0, 47.0),
                        new Point(27.0, -30.0, 47.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg left depth +-
                new Polygon(
                        new Point(25.0, -30.0, -47.0),
                        new Point(25.0, -50.0, -47.0),
                        new Point(27.0, -50.0, -47.0),
                        new Point(27.0, -30.0, -47.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg right depth +-
                new Polygon(
                        new Point(-25.0, -30.0, -47.0),
                        new Point(-25.0, -50.0, -47.0),
                        new Point(-27.0, -50.0, -47.0),
                        new Point(-27.0, -30.0, -47.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg right depth --
                new Polygon(
                        new Point(-25.0, -30.0, 47.0),
                        new Point(-25.0, -50.0, 47.0),
                        new Point(-27.0, -50.0, 47.0),
                        new Point(-27.0, -30.0, 47.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg left depth -+
        new Polygon(
                new Point(25.0, -30.0, 48.0),
                new Point(25.0, -50.0, 48.0),
                new Point(27.0, -50.0, 48.0),
                new Point(27.0, -30.0, 48.0)
        ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                .setEmission(new Color(139, 69, 19))
                .setMaterial(new Material()
                        .setKd(0.7).setKs(0.3))
                ,
                //leg left depth ++
                new Polygon(
                        new Point(25.0, -30.0, -48.0),
                        new Point(25.0, -50.0, -48.0),
                        new Point(27.0, -50.0, -48.0),
                        new Point(27.0, -30.0, -48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg right depth ++
                new Polygon(
                        new Point(-25.0, -30.0, -48.0),
                        new Point(-25.0, -50.0, -48.0),
                        new Point(-27.0, -50.0, -48.0),
                        new Point(-27.0, -30.0, -48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg right depth --
                new Polygon(
                        new Point(-25.0, -30.0, 48.0),
                        new Point(-25.0, -50.0, 48.0),
                        new Point(-27.0, -50.0, 48.0),
                        new Point(-27.0, -30.0, 48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                        ,
                //leg side
        new Polygon(
                new Point(25.0, -30.0, 48.0),
                new Point(25.0, -50.0, 47.0),
                new Point(27.0, -50.0, 47.0),
                new Point(27.0, -30.0, 48.0)
        ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                .setEmission(new Color(139, 69, 19))
                .setMaterial(new Material()
                        .setKd(0.7).setKs(0.3))
                ,
                //leg side
                new Polygon(
                        new Point(25.0, -30.0, -48.0),
                        new Point(25.0, -50.0, -47.0),
                        new Point(27.0, -50.0, -47.0),
                        new Point(27.0, -30.0, -48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg side
                new Polygon(
                        new Point(-25.0, -30.0, -48.0),
                        new Point(-25.0, -50.0, -47.0),
                        new Point(-27.0, -50.0, -47.0),
                        new Point(-27.0, -30.0, -48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                //leg side
                new Polygon(
                        new Point(-25.0, -30.0, 48.0),
                        new Point(-25.0, -50.0, 47.0),
                        new Point(-27.0, -50.0, 47.0),
                        new Point(-27.0, -30.0, 48.0)
                ).setMaterial(new Material().setKr(0.3).setKd(0.5).setKs(0.4))

                        .setEmission(new Color(139, 69, 19))
                        .setMaterial(new Material()
                                .setKd(0.7).setKs(0.3))
                ,
                new Sphere(new Point(0,-30,0),5d)
                        .setEmission(new Color(BLUE))
                        .setMaterial(
                                new Material()
                                        .setKd(0.7)
                                        .setKs(0.3)
                                        .setKt(0.9)
                                        .setRefractiveIndex(Material.VACUUM_REFRACTIVE_INDEX))
                ,
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
                        .setMaterial(material)
                );




        scene = new Scene.SceneBuilder("Test scene snell")
                .setGeometries(geometries)
                .setLights(
                       // new PointLight(new Color(255,255,255).reduce(1.5),new Point(0, 60, -50))
                          )
                .build();

        ImageWriter imageWriter = new ImageWriter("the room for the diamond", 600, 600);
        camera.setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
