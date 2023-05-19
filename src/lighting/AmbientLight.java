package lighting;
import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light {
    //A New Beginning
    public static final AmbientLight NONE  = new AmbientLight(Color.BLACK,Double3.ZERO);

    public AmbientLight(Color color, Double3 double3){

        super(color.scale(double3));
    }

    public AmbientLight(Color color, double d ){

        super(color.scale(d));
    }

}
