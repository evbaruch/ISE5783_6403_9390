package lighting;
import primitives.Color;
import primitives.Double3;

public class AmbientLight {

    Color intensity;

    public static AmbientLight NONE  = new AmbientLight(Color.BLACK,Double3.ZERO);

    AmbientLight(Color color, Double3 double3 ){
        intensity = color.reduce(double3);
    }

    AmbientLight(Color color, double d ){
        intensity = color.reduce(d);
    }
}
