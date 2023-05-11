package lighting;
import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    Color intensity;

    public static final AmbientLight NONE  = new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color color, Double3 double3){
        this.intensity = color.reduce(double3);
    }

    AmbientLight(Color color, double d ){
        this.intensity = color.reduce(d);
    }

    Color getIntensity(){
        return this.intensity;
    }
}
