package renderer;

import static java.awt.Color.*;

import parser.Json;
import parser.Xml;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import primitives.*;
import scene.Scene;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class RenderTests {

    /**
     * Produce a scene with basic 3D model and render it into a png image with a
     * grid
     */
    @Test
    public void basicRenderTwoColorTest() {
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setBackground(new Color(75, 127, 90))
                .setAmbientLight(new AmbientLight(
                        new Color(255, 191, 191), //
                        new Double3(1, 1, 1)))
                .build();//

        scene.getGeometries().add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100),
                        new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("base render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
    }

    // For stage 6 - please disregard in stage 5

    /**
     * Produce a scene with basic 3D model - including individual lights of the
     * bodies and render it into a png image with a grid
     */
    @Test
    public void basicRenderMultiColorTest() {
        Scene scene = new Scene.SceneBuilder("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2)))
                .build(); //

        scene.getGeometries().add( // center
                new Sphere(new Point(0, 0, -100), 50),
                // up left
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                        .setEmission(new Color(GREEN)),
                // down left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                        .setEmission(new Color(RED)),
                // down right
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                        .setEmission(new Color(BLUE)));

        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("color render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));

        camera.renderImage();
        camera.printGrid(100, new Color(WHITE));
        camera.writeToImage();
    }

    /**
     * Test for XML based scene - for bonus
     */
    @Test
    public void basicRenderXml() {
        Scene scene = new Scene.SceneBuilder("XML Test scene").build();
        ImageWriter imageWriter;
        // enter XML file name and parse from XML file into scene object
        // using the code you added in appropriate packages
        // ...
        // NB: unit tests is not the correct place to put XML parsing code
        String xmlFileName = "files/basicRenderTestTwoColors.xml";
        try {
           // scene = Xml.parseSceneFromXml(xmlFileName, scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String xmlImageWriterDefinition = "files/ImageWriterdefinitionA.xml";
        try {
            //imageWriter = Xml.parseImageWriterFromXml(xmlImageWriterDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
                .setVPDistance(100)                                                                //
                .setVPSize(500, 500).setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();


        xmlFileName = "files/basicRenderMultiColors.xml";
        try {
            //scene = Xml.parseSceneFromXml(xmlFileName, scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        xmlImageWriterDefinition = "files/ImageWriterdefinitionColor.xml";
        try {
            //imageWriter = Xml.parseImageWriterFromXml(xmlImageWriterDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
                .setVPDistance(100)                                                                //
                .setVPSize(500, 500).setImageWriter(imageWriter)
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();


        String jsonFileName = "files/basicRenderMultiColors.json";
        try {
            scene = Json.parseSceneFromJson(jsonFileName, scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
                .setVPDistance(100)                                                                //
                .setVPSize(500, 500).setImageWriter(new ImageWriter("json color render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();


        jsonFileName = "files/basicRenderTestTwoColors.json";
        try {
            scene = Json.parseSceneFromJson(jsonFileName, scene);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0))     //
                .setVPDistance(100)                                                                //
                .setVPSize(500, 500).setImageWriter(new ImageWriter("json render test", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();

    }
}
