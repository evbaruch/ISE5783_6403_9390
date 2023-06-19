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

        Color color = new Color(200, 200, 300);

        Geometries geometries = Stl.ConvertStlToGeometrys("files/StlFiles/cuteDragon.stl",material,color);

        Camera camera = new Camera(new Point(-20, 200, 60), new Vector(0, -1, 0), new Vector(0, 0, 1))
                .setVPSize(150, 150).setVPDistance(500).tiltCamera(17).adjustHorizontalRotation(0);

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
                        ))
                .build();

        camera.setImageWriter(new ImageWriter("stl2", 500, 500))
                .setRayTracer(new RayTracerBasic(scene))
                .renderImage()
                .writeToImage();

    }
}
