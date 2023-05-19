package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource{

    private Point position;
    private double Kc = 1, Kl = 0, Kq = 0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.Kc = Kc;
        this.Kl = Kl;
        this.Kq = Kq;
    }

    @Override
    public Color getIntensity(Point p) {
        return null;
    }

    @Override
    public Vector getL(Point p) {
        return null;
    }

    public PointLight setKc(double kc) {
        this.Kc = kc;
        return this;
    }

    public PointLight setKl(double kL) {
        this.Kl = kL;
        return this;
    }

    public PointLight setKq(double kq) {
        this.Kq = kq;
        return this;
    }
}
