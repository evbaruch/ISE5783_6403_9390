package primitives;

/**
 * Represents the material properties of an object in a scene.
 * Material properties include the diffuse coefficient (kd), specular coefficient (Ks), and shininess.
 */
public class Material {
    public  Double3 kd = new Double3(0,0,0);
    public  Double3 Ks = new Double3(0,0,0);
    public int nShininess = 0;

    /**
     * Sets the diffuse coefficient (kd) of the material.
     *
     * @param kd The diffuse coefficient to set, specified as a 3D vector.
     * @return This Material object.
     */
    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    /**
     * Sets the diffuse coefficient (kd) of the material to a uniform value.
     *
     * @param d The uniform value to set for the diffuse coefficient.
     * @return This Material object.
     */
    public Material setKd(double d) {
        this.kd = new Double3(d,d,d);
        return this;
    }

    /**
     * Sets the specular coefficient (Ks) of the material.
     *
     * @param kS The specular coefficient to set, specified as a 3D vector.
     * @return This Material object.
     */
    public Material setKs(Double3 kS) {
        this.Ks = kS;
        return this;
    }

    /**
     * Sets the specular coefficient (Ks) of the material to a uniform value.
     *
     * @param d The uniform value to set for the specular coefficient.
     * @return This Material object.
     */
    public Material setKs(double d) {
        this.Ks = new Double3(d,d,d);
        return this;
    }

    /**
     * Sets the shininess of the material.
     *
     * @param nShininess The shininess value to set.
     * @return This Material object.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
