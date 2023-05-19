package primitives;

public class Material {
    public  Double3 kd = new Double3(0,0,0);
    public  Double3 Ks = new Double3(0,0,0);
    public int nShininess = 0;

    public Material setKd(Double3 kd) {
        this.kd = kd;
        return this;
    }

    public Material setKd(double d) {
        this.kd = new Double3(d,d,d);
        return this;
    }

    public Material setKs(Double3 kS) {
        this.Ks = kS;
        return this;
    }

    public Material setKs(double d) {
        this.Ks = new Double3(d,d,d);
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
