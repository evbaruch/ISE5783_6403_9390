package mainModel;

import geometries.Geometries;
import geometries.Sphere;
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
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class KnightDragon {

    @Test
    public void cloud(){
        Material material = new Material()
                .setShininess(30)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection

        Color color = new Color(50, 50, 50);

        Geometries geometries = Stl.ConvertStlToGeometrys("files/StlFiles/KnightDragon.stl",material,color);
        //geometries.add(new Sphere(new Point(0,0,100),10).setMaterial(material).setEmission(new Color(500, 500, 500)));

        Camera camera = new Camera(new Point(150, -200, 70), new Vector(-15, 22, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(200);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setAmbientLight(new AmbientLight(new Color(100, 100, 100),0.5))
                .setBackground(new Color(100, 150, 300))
                .setLights(
                        new DirectionalLight(
                                new Color(100,100,100),
                                new Vector(0,0,-1)
                        ))
                .build();

        camera.setImageWriter(new ImageWriter("cloud", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void KnightDragon(){
        Material KnightMaterial = new Material()
                .setShininess(10)
                .setKd(0.4)//diffuse
                .setKs(0.6)//specular
                .setKt(0)//transmission
                .setKr(0.3);//reflection
        Color KnightColor = new Color(60, 50, 100);

        Material cloudMaterial = new Material()
                .setShininess(0)
                .setKd(0.4)//diffuse
                .setKs(0.2)//specular
                .setKt(0.1)//transmission
                .setKr(0);//reflection
        Color cloudColor = new Color(50, 50, 50);

        Material dragonMaterial = new Material()
                .setShininess(50)
                .setKd(0.6)//diffuse
                .setKs(0.4)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection
        Color dragonColor = new Color(100, 0, 0);

        Material groundMaterial = new Material()
                .setShininess(0)
                .setKd(0.7)//diffuse
                .setKs(0)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection
        Color groundColor = new Color(80, 60, 40);

        Material sunMaterial = new Material()
                .setShininess(0)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0.6)//transmission
                .setKr(0);//reflection
        Color sunColor = new Color(1000, 150, 150);

        Geometries KnightGeometries = Stl.ConvertStlToGeometrysB("files/StlFiles/knight.stl",KnightMaterial,KnightColor);
        Geometries cloudGeometries = Stl.ConvertStlToGeometrysB("files/StlFiles/cloud.stl",cloudMaterial,cloudColor);
        Geometries dragonGeometries = Stl.ConvertStlToGeometrysB("files/StlFiles/dragon.stl",dragonMaterial,dragonColor);
        Geometries grounGeometries = Stl.ConvertStlToGeometrysB("files/StlFiles/ground.stl",groundMaterial,groundColor);
        Geometries sunGeometries = new Geometries(new Sphere(new Point(-25,350,180), 15).setMaterial(sunMaterial).setEmission(sunColor));


        Geometries geometries = new Geometries(KnightGeometries,cloudGeometries,dragonGeometries,grounGeometries,sunGeometries);


        Camera camera = new Camera(new Point(150, -200, 70), new Vector(-15, 22, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(200);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setBackground(new Color(150, 100, 300))
                .setLights(
                        new PointLight(
                                new Color(100,0,0),
                                new Point(0,15,70)
                        ),
                        new PointLight(
                                new Color(100,100,100),
                                new Point(20,-110,85)
                        ),
                        new PointLight(
                                new Color(1000,800,800),
                                new Point(-25,350,200)
                        ),
                        new SpotLight(
                                new Color(0,0,100),
                                new Point(0,-40,100),
                                new Vector(0,-1,-4)
                        ),
                        new DirectionalLight(
                                new Color(80,50,70),
                                new Vector(25,-350,-180)
                        ))
                .build();

        camera.setImageWriter(new ImageWriter("KnightDragon3", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }

    @Test
    public void test(){

        Material material1 = new Material()
                .setShininess(0)
                .setKd(0.5)//diffuse
                .setKs(0.5)//specular
                .setKt(0.6)//transmission
                .setKr(0);//reflection
        Color color1 = new Color(1000, 150, 150);

        Material material2 = new Material()
                .setShininess(0)
                .setKd(0.7)//diffuse
                .setKs(0)//specular
                .setKt(0)//transmission
                .setKr(0);//reflection
        Color color2 = new Color(155, 118, 83);


        Geometries geometries = new Geometries(
                new Sphere(
                        new Point(0,-12,0),
                        10
                ).setMaterial(material1).setEmission(color1),
                new Sphere(
                        new Point(0,12,0),
                        10
                ).setMaterial(material2).setEmission(color2)
        );



        Camera camera = new Camera(new Point(50, -50, 0), new Vector(-1, 1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(200);

        Scene scene = new Scene.SceneBuilder("Test scene")
                .setGeometries(geometries)
                .setBackground(new Color(100, 150, 300))
                .setLights(
                        new PointLight(
                                new Color(1000,800,800),
                                new Point(0,-12,0)
                        ),
                        new DirectionalLight(
                                new Color(0,0,0),
                                new Vector(0,0,-1)
                        ))
                .build();

        camera.setImageWriter(new ImageWriter("test", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();
    }
}
