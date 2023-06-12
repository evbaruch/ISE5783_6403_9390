package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import primitives.Material;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Scene {

    /**

     The refractive index of the scene content.
     */
    private final double refractiveIndex;
    /**

     Retrieves the refractive index of the scene content.
     @return The refractive index of the scene content.
     */
    public double getRefractiveIndex() {
        return refractiveIndex;
    }
    private final String name;
    private final Color background ;
    private AmbientLight ambientLight;
    private final List<LightSource> lights;
    private final Geometries geometries ;
    private int softShade = 1;

    public String getName() {
        return name;
    }

    public Color getBackground() {
        return background;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public List<LightSource> getLights() {
        return lights;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public int getSoftShade() {
        return softShade;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    private Scene(SceneBuilder sceneBuilder){
        this.name = sceneBuilder.name;
        this.background = sceneBuilder.background;
        this.ambientLight = sceneBuilder.ambientLight;
        this.lights = sceneBuilder.lights;
        this.geometries = sceneBuilder.geometries;
        this.refractiveIndex = sceneBuilder.refractiveIndex;
        this.softShade = sceneBuilder.softShade;
    }

    public static class SceneBuilder {

        /**

         The refractive index of the scene content.
         */
        public double refractiveIndex = 1;
        public final String name;
        public Color background = Color.BLACK;
        public AmbientLight ambientLight = AmbientLight.NONE;
        public List<LightSource> lights = new LinkedList<>();

        public Geometries geometries = new Geometries();
        private int softShade = 1;

        public SceneBuilder(String name){
            this.name = name;
        }
          public SceneBuilder setBackground(Color background) {
            this.background = background;
            return this;
        }
        public SceneBuilder setAmbientLight(AmbientLight ambientLight) {
            this.ambientLight = ambientLight;
            return this;
        }
        public SceneBuilder setLights(LightSource... lights) {
            Collections.addAll(this.lights,lights);
            return this;
        }
        public SceneBuilder setGeometries(Geometries geometries) {
            this.geometries = geometries;
            return this;
        }
        public SceneBuilder setRefractiveIndex(double refractiveIndex) {
            this.refractiveIndex = refractiveIndex;
            return this;
        }

        public SceneBuilder setSoftShade(int softShade) {
            this.softShade = softShade;
            return this;
        }

        public Scene build(){
            Scene scene = new Scene(this);
            return scene;
        }
    }
}
