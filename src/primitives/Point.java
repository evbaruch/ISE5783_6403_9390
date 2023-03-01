package primitives;

import java.nio.channels.Pipe;
import java.util.Objects;

public class Point {
    Double3 xyz;

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    Point(double x,double y,double z) {
        this.xyz = new Double3(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public String toString() {
        return "Point{" +
                "(x,y,z)=" + xyz +
                '}';
    }

    public Point add(Vector v){

        return new Point(this.xyz.add(v.xyz));
    }

    public Vector subtract(Point p)
    {
        return new Vector(this.xyz.subtract(p.xyz));
    }

    public double distanceSquared(Point p){
        return (p.xyz.d1 - this.xyz.d1)*(p.xyz.d1 - this.xyz.d1) +
               (p.xyz.d2 - this.xyz.d2)*(p.xyz.d2 - this.xyz.d2) +
               (p.xyz.d3 - this.xyz.d3)*(p.xyz.d3 - this.xyz.d3);
    }

    public double distance(Point p){
        return Math.sqrt(distanceSquared(p));
    }
}
