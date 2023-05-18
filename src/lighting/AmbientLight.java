package lighting;
import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    //A New Beginning
    Color intensity;

    public static final AmbientLight NONE  = new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color color, Double3 double3){

        this.intensity = color.scale(double3);
    }

    public AmbientLight(Color color, double d ){

        this.intensity = color.scale(d);
    }

    public Color getIntensity(){
        return this.intensity;
    }
}
