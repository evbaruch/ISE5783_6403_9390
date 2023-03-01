package primitives;

public class Vector extends Point {

    public Vector(Double3 xyz) {
        super(xyz);
    }

    Vector(double x,double y,double z) {
        super(x,y,z);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public Vector add(Vector p) {
        return new Vector(this.xyz.add(p.xyz));
    }

    public Vector scale(double d) {
        return new Vector(this.xyz.scale(d));
    }

    public Vector crossProduct(Vector v){
        return new Vector(this.xyz.d2*v.xyz.d3 - this.xyz.d3*v.xyz.d2,
                          this.xyz.d3*v.xyz.d1 - this.xyz.d1*v.xyz.d3,
                          this.xyz.d1*v.xyz.d2 - this.xyz.d2*v.xyz.d1);
    }

    public double lengthSquered(){
    return this.distanceSquared(new Point(Double3.ZERO));
    }

    public double length(){
        return Math.sqrt(lengthSquered());
    }

    public Vector normalize(){
        return new Vector(this.xyz.reduce(length()));
    }

    public double dotProduct(Vector v){
        return (this.xyz.d1*v.xyz.d1 + this.xyz.d2*v.xyz.d2 + this.xyz.d3*v.xyz.d3);
    }
    @Override
    public String toString() {
        return "Vector{" +
                "v =" + xyz +
                '}';
    }
}
