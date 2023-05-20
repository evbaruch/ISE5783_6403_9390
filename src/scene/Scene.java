package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Scene {
    public String name;
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;

    public List<LightSource> lights = new LinkedList<>();

    public Geometries geometries = new Geometries();

    public Scene(String name){
        this.name = name;
    }

    public Scene setName(String name) {
        this.name = name;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public Scene setLights(LightSource... lights) {
        Collections.addAll(this.lights,lights);
        return this;
    }
}
