package renderer;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import parser.Stl;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

public class StlTest {
    @Test
    public void Test1() {
        Material material = new Material()
                .setRefractiveIndex(Material.DIAMOND_REFRACTIVE_INDEX)
                .setShininess(60)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection

        Color color = new Color(0,100,0);

        Geometries geometries = Stl.ConvertStlToGeometrys("files/StlFiles/cuteDragon0.01.stl",material,color);

        Camera camera = new Camera(new Point(0, 300, 100), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(500).tiltCamera(17).adjustHorizontalRotation(0)
                .moveCameraOnSphereSimply(300,new Point(0,0,0),0,90)
                .moveCameraOnSphereSimply(300,new Point(0,0,0),0,90)
                .moveCameraOnSphereSimply(300,new Point(0,0,0),50,0);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.1))
                .setLights(
                        new PointLight(
                                new Color(100,200,100),
                                new Point(1,0,30)),
                        new DirectionalLight(
                                new Color(200,100,100),
                                new Vector(1,1,-1)
                        ),
                        new PointLight(new Color(144,238,144),new Point(-10.0047,-78.9381,-6.14289))
                        ,
                        new PointLight(new Color(40.4601,19.3441,0.0).scale(100),new Point(4.09299,-66.977,49.2683))
                        ,
                        new SpotLight(
                                new Color(10000,10000,10000),
                                new Point(-32.4067, -45.266, 55.6412),
                                new Vector(-66.0842, -62.0074, 22.5319)).setNarrowBeam(15)
                        ,
                        new SpotLight(
                                new Color(10000,10000,10000),
                                new Point(55.7231, -55.6738, 47.8222),
                                new Vector(53.5638, -51.8935, 91.3832)).setNarrowBeam(15))

                .build();

        camera.setImageWriter(new ImageWriter("stl2", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }

    @Test
    public void Test2() {
        Material material = new Material()
                .setRefractiveIndex(Material.DIAMOND_REFRACTIVE_INDEX)
                .setShininess(60)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection

        Color color = new Color(100,0,0);

        Geometries hand = Stl.ConvertStlToGeometrys("files/StlFiles/handHeart.stl",material,color);
        Geometries surface = Stl.ConvertStlToGeometrys("files/StlFiles/handHeartSurface.stl",material,new Color(255,255,255));

        Geometries geometries = new Geometries(hand,surface);

        Camera camera = new Camera(new Point(0 , -126.978 , 60.8055), new Vector(0, 1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(166).tiltCamera(17).adjustHorizontalRotation(0);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.1))
                .setLights(
                        new PointLight(
                                new Color(100,200,100),
                                new Point(1,0,30)),
                        new DirectionalLight(
                                new Color(200,100,100),
                                new Vector(1,1,-1)
                        ),
                        new PointLight(new Color(144,238,144),new Point(0.472467 ,6.2596 ,25.3015))
                       )

                .build();

        camera.setImageWriter(new ImageWriter("hands heart", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }

    @Test
    public void Test3() {
        Material material = new Material()
                .setRefractiveIndex(Material.DIAMOND_REFRACTIVE_INDEX)
                .setShininess(60)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection

        Color color = new Color(186,135,89);

        Geometries geometries = Stl.ConvertStlToGeometrys("files/StlFiles/deerHead.stl",material,color);

        Camera camera = new Camera(new Point(5.5621  , -503.66  , 272.173 ), new Vector(0, 1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(503.66).tiltCamera(17).adjustHorizontalRotation(0);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setAmbientLight(new AmbientLight(new Color(10, 10, 10), 0.1))
                .setLights(
                        new PointLight(
                                new Color(100,200,100),
                                new Point(1,0,30)),
                        new DirectionalLight(
                                new Color(200,100,100),
                                new Vector(1,1,-1)
                        ),
                        new PointLight(new Color(144,238,144),new Point(1.55844 ,-161.065 ,142.227 ))
                )

                .build();

        camera.setImageWriter(new ImageWriter("deer head", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }
}
