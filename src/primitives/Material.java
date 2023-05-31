package primitives;

/**
 * Represents the material properties of an object in a scene.
 * Material properties include the diffuse coefficient (kd), specular coefficient (Ks), and shininess.
 */
public class Material {
    /**
     * The default refractive index for materials.
     */
    public static final double VACUUM_REFRACTIVE_INDEX = 1.0;

    /**
     * Refractive index for glass material.
     */
    public static final double GLASS_REFRACTIVE_INDEX = 1.5;

    /**
     * Refractive index for air at STP.
     */
    public static final double AIR_REFRACTIVE_INDEX = 1.000273;

    /**
     * Refractive index for carbon dioxide gas.
     */
    public static final double CARBON_DIOXIDE_REFRACTIVE_INDEX = 1.00045;

    /**
     * Refractive index for helium gas.
     */
    public static final double HELIUM_REFRACTIVE_INDEX = 1.000036;

    /**
     * Refractive index for hydrogen gas.
     */
    public static final double HYDROGEN_REFRACTIVE_INDEX = 1.000132;

    /**
     * Refractive index for arsenic trisulfide and sulfur in methylene iodide (liquid).
     */
    public static final double ARSENIC_TRISULFIDE_REFRACTIVE_INDEX = 1.9;

    /**
     * Refractive index for carbon disulfide (liquid).
     */
    public static final double CARBON_DISULFIDE_REFRACTIVE_INDEX = 1.628;

    /**
     * Refractive index for benzene (liquid).
     */
    public static final double BENZENE_REFRACTIVE_INDEX = 1.501;

    /**
     * Refractive index for carbon tetrachloride (liquid).
     */
    public static final double CARBON_TETRACHLORIDE_REFRACTIVE_INDEX = 1.461;

    /**
     * Refractive index for silicone oil (nD25) (liquid).
     */
    public static final double SILICONE_OIL_REFRACTIVE_INDEX = 1.393;

    /**
     * Refractive index for kerosene (liquid).
     */
    public static final double KEROSENE_REFRACTIVE_INDEX = 1.39;

    /**
     * Refractive index for ethanol (liquid).
     */
    public static final double ETHANOL_REFRACTIVE_INDEX = 1.361;

    /**
     * Refractive index for acetone (liquid).
     */
    public static final double ACETONE_REFRACTIVE_INDEX = 1.36;

    /**
     * Refractive index for water (liquid).
     */
    public static final double WATER_REFRACTIVE_INDEX = 1.333;

    /**
     * Refractive index for a 10% glucose solution in water (liquid).
     */
    public static final double GLUCOSE_10_PERCENT_REFRACTIVE_INDEX = 1.3477;

    /**
     * Refractive index for a 20% glucose solution in water (liquid).
     */
    public static final double GLUCOSE_20_PERCENT_REFRACTIVE_INDEX = 1.3635;

    /**
     * Refractive index for a 60% glucose solution in water (liquid).
     */
    public static final double GLUCOSE_60_PERCENT_REFRACTIVE_INDEX = 1.4394;

    /**
     * Refractive index for silicon carbide (moissanite; 6H form) (solid).
     */
    public static final double SILICON_CARBIDE_REFRACTIVE_INDEX = 2.65;

    /**
     * Refractive index for titanium dioxide (rutile phase) (solid).
     */
    public static final double TITANIUM_DIOXIDE_REFRACTIVE_INDEX = 2.614;

    /**
     * Refractive index for diamond (solid).
     */
    public static final double DIAMOND_REFRACTIVE_INDEX = 2.417;

    /**
     * Refractive index for strontium titanate (solid).
     */
    public static final double STRONTIUM_TITANATE_REFRACTIVE_INDEX = 2.41;

    /**
     * Refractive index for tantalum pentoxide (solid).
     */
    public static final double TANTALUM_PENTOXIDE_REFRACTIVE_INDEX = 2.25;


    /**
     * The diffuse reflection coefficient (kd) of the material.
     */
    public Double3 kd = Double3.ZERO;

    /**
     * The specular reflection coefficient (Ks) of the material.
     */
    public Double3 Ks = Double3.ZERO;

    /**
     * The transmission coefficient (Kt) of the material.
     */
    public Double3 Kt = Double3.ZERO;

    /**
     * The reflection coefficient (Kr) of the material.
     */
    public Double3 Kr = Double3.ZERO;

    /**
     * The shininess factor (n) of the material, used for specular highlights.
     */
    public int nShininess = 0;

    /**
     * The refractive index of the material, used in Snell's law calculations.
     */
    public double refractiveIndex = 1;

    /**
     * Sets the refractive index of the material.
     *
     * @param refractiveIndex The refractive index to set.
     * @return The Material instance with the updated refractive index.
     */
    public Material setRefractiveIndex(double refractiveIndex) {
        this.refractiveIndex = refractiveIndex;
        return this;
    }

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
    public Material setKt(Double3 kt) {
        Kt = kt;
        return this;
    }

    public Material setKr(Double3 kr) {
        Kr = kr;
        return this;
    }

    public Material setKt(double kt) {
        Kt = new Double3(kt);
        return this;
    }

    public Material setKr(double kr) {
        Kr = new Double3(kr);
        return this;
    }
}
