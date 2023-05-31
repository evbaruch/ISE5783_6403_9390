package primitives;

/**
 * Represents the material properties of an object in a scene.
 * Material properties include the diffuse coefficient (kd), specular coefficient (Ks), and shininess.
 */
public class Material {
    /**
     * The default refractive index for materials.
     */
    public static final double REFRACTIVE_INDEX_VACUUM = 1.0;

    /**
     * Refractive index for air at STP.
     */
    public static final double REFRACTIVE_INDEX_AIR = 1.000273;

    /**
     * Refractive index for carbon dioxide gas.
     */
    public static final double REFRACTIVE_INDEX_CARBON_DIOXIDE = 1.00045;

    /**
     * Refractive index for helium gas.
     */
    public static final double REFRACTIVE_INDEX_HELIUM = 1.000036;

    /**
     * Refractive index for hydrogen gas.
     */
    public static final double REFRACTIVE_INDEX_HYDROGEN = 1.000132;

    /**
     * Refractive index for arsenic trisulfide and sulfur in methylene iodide (liquid).
     */
    public static final double REFRACTIVE_INDEX_ARSENIC_TRISULFIDE = 1.9;

    /**
     * Refractive index for carbon disulfide (liquid).
     */
    public static final double REFRACTIVE_INDEX_CARBON_DISULFIDE = 1.628;

    /**
     * Refractive index for benzene (liquid).
     */
    public static final double REFRACTIVE_INDEX_BENZENE = 1.501;

    /**
     * Refractive index for carbon tetrachloride (liquid).
     */
    public static final double REFRACTIVE_INDEX_CARBON_TETRACHLORIDE = 1.461;

    /**
     * Refractive index for silicone oil (nD25) (liquid).
     */
    public static final double REFRACTIVE_INDEX_SILICONE_OIL = 1.393;

    /**
     * Refractive index for kerosene (liquid).
     */
    public static final double REFRACTIVE_INDEX_KEROSENE = 1.39;

    /**
     * Refractive index for ethanol (liquid).
     */
    public static final double REFRACTIVE_INDEX_ETHANOL = 1.361;

    /**
     * Refractive index for acetone (liquid).
     */
    public static final double REFRACTIVE_INDEX_ACETONE = 1.36;

    /**
     * Refractive index for water (liquid).
     */
    public static final double REFRACTIVE_INDEX_WATER = 1.333;

    /**
     * Refractive index for a 10% glucose solution in water (liquid).
     */
    public static final double REFRACTIVE_INDEX_GLUCOSE_10_PERCENT = 1.3477;

    /**
     * Refractive index for a 20% glucose solution in water (liquid).
     */
    public static final double REFRACTIVE_INDEX_GLUCOSE_20_PERCENT = 1.3635;

    /**
     * Refractive index for a 60% glucose solution in water (liquid).
     */
    public static final double REFRACTIVE_INDEX_GLUCOSE_60_PERCENT = 1.4394;

    /**
     * Refractive index for silicon carbide (moissanite; 6H form) (solid).
     */
    public static final double REFRACTIVE_INDEX_SILICON_CARBIDE = 2.65;

    /**
     * Refractive index for titanium dioxide (rutile phase) (solid).
     */
    public static final double REFRACTIVE_INDEX_TITANIUM_DIOXIDE = 2.614;

    /**
     * Refractive index for diamond (solid).
     */
    public static final double REFRACTIVE_INDEX_DIAMOND = 2.417;

    /**
     * Refractive index for strontium titanate (solid).
     */
    public static final double REFRACTIVE_INDEX_STRONTIUM_TITANATE = 2.41;

    /**
     * Refractive index for tantalum pentoxide (solid).
     */
    public static final double REFRACTIVE_INDEX_TANTALUM_PENTOXIDE = 2.15;

    /**
     * Refractive index for amber (solid).
     */
    public static final double REFRACTIVE_INDEX_AMBER = 1.55;

    /**
     * Refractive index for sodium chloride (solid).
     */
    public static final double REFRACTIVE_INDEX_SODIUM_CHLORIDE = 1.544;

    /**
     * Refractive index for fused silica (pure form of glass, also called fused quartz) (solid).
     */
    public static final double REFRACTIVE_INDEX_FUSED_SILICA = 1.458;

    /**
     * Refractive index for liquid helium.
     */
    public static final double REFRACTIVE_INDEX_LIQUID_HELIUM = 1.025;

    /**
     * Refractive index for perfluorohexane (Fluorinert FC-72) (liquid).
     */
    public static final double REFRACTIVE_INDEX_PERFLUOROHEXANE = 1.251;

    /**
     * Refractive index for water ice (solid).
     */
    public static final double REFRACTIVE_INDEX_ICE = 1.31;

    /**
     * Refractive index for TFE/PDD (Teflon AF) (liquid).
     */
    public static final double REFRACTIVE_INDEX_TFE_PDD = 1.315;

    /**
     * Refractive index for cryolite (solid).
     */
    public static final double REFRACTIVE_INDEX_CRYOLITE = 1.338;

    /**
     * Refractive index for Cytop (liquid).
     */
    public static final double REFRACTIVE_INDEX_CYTOP = 1.34;

    /**
     * Refractive index for polytetrafluoroethylene (Teflon) (solid).
     */
    public static final double REFRACTIVE_INDEX_POLYTETRAFLUOROETHYLENE = 1.35;

    /**
     * Refractive index for a 25% sugar solution (liquid).
     */
    public static final double REFRACTIVE_INDEX_SUGAR_25_PERCENT = 1.3723;

    /**
     * Refractive index for cornea (human) (solid).
     */
    public static final double REFRACTIVE_INDEX_CORNEA = 1.373;

    /**
     * Refractive index for lens (human) (solid).
     */
    public static final double REFRACTIVE_INDEX_LENS = 1.386;

    /**
     * Refractive index for liver (human) (solid).
     */
    public static final double REFRACTIVE_INDEX_LIVER = 1.369;

    /**
     * Refractive index for intestinal mucosa (human) (solid).
     */
    public static final double REFRACTIVE_INDEX_INTESTINAL_MUCOSA = 1.329;

    /**
     * Refractive index for ethylene tetrafluoroethylene (ETFE) (solid).
     */
    public static final double REFRACTIVE_INDEX_ETFE = 1.403;

    /**
     * Refractive index for Sylgard 184 (polydimethylsiloxane) (solid).
     */
    public static final double REFRACTIVE_INDEX_SYLGARD_184 = 1.4118;

    /**
     * Refractive index for a 50% sugar solution (liquid).
     */
    public static final double REFRACTIVE_INDEX_SUGAR_50_PERCENT = 1.42;

    /**
     * Refractive index for polylactic acid (solid).
     */
    public static final double REFRACTIVE_INDEX_POLYLACTIC_ACID = 1.46;

    /**
     * Refractive index for Pyrex (a borosilicate glass) (solid).
     */
    public static final double REFRACTIVE_INDEX_PYREX = 1.47;

    /**
     * Refractive index for vegetable oil (liquid).
     */
    public static final double REFRACTIVE_INDEX_VEGETABLE_OIL = 1.47;

    /**
     * Refractive index for glycerol (liquid).
     */
    public static final double REFRACTIVE_INDEX_GLYCEROL = 1.4729;

    /**
     * Refractive index for a 75% sugar solution (liquid).
     */
    public static final double REFRACTIVE_INDEX_SUGAR_75_PERCENT = 1.4774;

    /**
     * Refractive index for poly(methyl methacrylate) (PMMA) (solid).
     */
    public static final double REFRACTIVE_INDEX_PMMA = 1.4893;

    /**
     * Refractive index for halite (rock salt) (solid).
     */
    public static final double REFRACTIVE_INDEX_HALITE = 1.516;

    /**
     * Refractive index for plate glass (window glass) (solid).
     */
    public static final double REFRACTIVE_INDEX_PLATE_GLASS = 1.52;

    /**
     * Refractive index for crown glass (pure) (solid).
     */
    public static final double REFRACTIVE_INDEX_CROWN_GLASS_PURE = 1.50;

    /**
     * Refractive index for PETg (solid).
     */
    public static final double REFRACTIVE_INDEX_PETG = 1.57;

    /**
     * Refractive index for polyethylene terephthalate (PET) (solid).
     */
    public static final double REFRACTIVE_INDEX_PET = 1.575;

    /**
     * Refractive index for polycarbonate (solid).
     */
    public static final double REFRACTIVE_INDEX_POLYCARBONATE = 1.60;

    /**
     * Refractive index for crown glass (impure) (solid).
     */
    public static final double REFRACTIVE_INDEX_CROWN_GLASS_IMPURE = 1.485;

    /**
     * Refractive index for flint glass (pure) (solid).
     */
    public static final double REFRACTIVE_INDEX_FLINT_GLASS_PURE = 1.60;

    /**
     * Refractive index for bromine (liquid).
     */
    public static final double REFRACTIVE_INDEX_BROMINE = 1.661;

    /**
     * Refractive index for flint glass (impure) (solid).
     */
    public static final double REFRACTIVE_INDEX_FLINT_GLASS_IMPURE = 1.523;

    /**
     * Refractive index for sapphire (solid).
     */
    public static final double REFRACTIVE_INDEX_SAPPHIRE = 1.762;

    /**
     * Refractive index for boron nitride (solid).
     */
    public static final double REFRACTIVE_INDEX_BORON_NITRIDE = 2.0;

    /**
     * Refractive index for cubic zirconia (solid).
     */
    public static final double REFRACTIVE_INDEX_CUBIC_ZIRCONIA = 2.15;

    /**
     * Refractive index for potassium niobate (KNbO3) (solid).
     */
    public static final double REFRACTIVE_INDEX_POTASSIUM_NIOBATE = 2.28;

    /**
     * Refractive index for zinc oxide (solid).
     */
    public static final double REFRACTIVE_INDEX_ZINC_OXIDE = 2.4;

    /**
     * Refractive index for cinnabar (mercury sulfide) (solid).
     */
    public static final double REFRACTIVE_INDEX_CINNABAR = 3.02;

    /**
     * Refractive index for silicon (solid).
     */
    public static final double REFRACTIVE_INDEX_SILICON = 3.42;

    /**
     * Refractive index for gallium(III) phosphide (solid).
     */
    public static final double REFRACTIVE_INDEX_GALLIUM_PHOSPHIDE = 3.45;

    /**
     * Refractive index for germanium (solid).
     */
    public static final double REFRACTIVE_INDEX_GERMANIUM = 4.0;

    /**
     * Refractive index for zinc selenide (solid).
     */
    public static final double REFRACTIVE_INDEX_ZINC_SELENIDE = 2.4;

    /**
     * Refractive index for zinc sulfide (solid).
     */
    public static final double REFRACTIVE_INDEX_ZINC_SULFIDE = 2.35;

    /**
     * Refractive index for diamond-like carbon (DLC) (solid).
     */
    public static final double REFRACTIVE_INDEX_DIAMOND_LIKE_CARBON = 2.5;

    /**
     * Refractive index for yttrium barium copper oxide (YBa2Cu3O7) (solid).
     */
    public static final double REFRACTIVE_INDEX_YBCO = 3.4;

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
