package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static primitives.Util.alignZero;

public class Camera {
    private Point location;
    private Vector Vto, Vup, Vright;
    private double width, height, distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;


    /**

     Sets the image writer to be used by this camera for writing the rendered image.
     @param imageWriter The image writer to be set.
     @return This camera object, with the updated image writer.
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

    /**
     * Sets the ray tracer base to be used for rendering the scene.
     *
     * @param rayTracerBase the ray tracer base to be used for rendering the scene.
     * @return the camera instance with the updated ray tracer base.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }


    /**

     Constructs a new camera object with the specified parameters.
     @param initialPoint the location of the camera in space.
     @param vto the direction the camera is pointing towards.
     @param vup the up direction of the camera.
     @throws IllegalArgumentException if vto and vup are not perpendicular to each other.
     */
    public Camera(Point initialPoint, Vector vto, Vector vup) {
        if (alignZero(vto.dotProduct(vup)) != 0) {
            throw new IllegalArgumentException("vto and vup must be perpendicular to each other");
        }
        this.Vto = vto.normalize();
        this.Vup = vup.normalize();
        this.Vright = this.Vto.crossProduct(this.Vup).normalize();
        this.location = initialPoint;
    }

    /**

     Returns the width of the view plane.
     @return the width of the view plane.
     */

    public double getWidth() {

        return width;
    }

    /**

     Returns the height of the view plane.
     @return the height of the view plane.
     */
    public double getHeight() {

        return height;
    }

    /**

     Returns the distance from the camera to the view plane.
     @return the distance from the camera to the view plane.
     */
    public double getDistance() {

        return distance;
    }

    /**

     Sets the size of the view plane.
     @param width the width of the view plane.
     @param height the height of the view plane.
     @return the camera instance with the updated view plane size.
     */
    public Camera setVPSize(double width, double height) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**

     Sets the distance between the camera and the view plane.
     @param distance the distance between the camera and the view plane.
     @return the camera instance with the updated view plane distance.
     */
    public Camera setVPDistance(double distance) {
        this.distance = distance;
        return this;
    }

    /**

     Constructs a ray from the camera passing through the given pixel coordinates on the view plane.

     @param nX the number of pixels in the X axis.

     @param nY the number of pixels in the Y axis.

     @param j the X coordinate of the pixel.

     @param i the Y coordinate of the pixel.

     @return a ray passing through the given pixel coordinates on the view plane.
     */
    public Ray constructRay(int nX, int nY, int j, int i) {

        //Image center
        Point Pc = location.add(Vto.scale(distance));

        //Calculate the size of each pixel
        double Rx = width/nX;
        double Ry = height/nY;

        //Calculation of displacement according to i j
        double Xj = (j - (double)(nX - 1)/2)*Rx;
        double Yi = -(i - (double)(nY - 1)/2)*Ry;

        //Calculating the pixels function according to i j and gives a point
        Point Pij = Pc;
        if (alignZero(Xj) != 0){
            Pij = Pij.add(Vright.scale(Xj));
        }
        if (alignZero(Yi) != 0){
            Pij = Pij.add(Vup.scale(Yi));
        }




        //Calculation of the vector from the point to the screen according to i j
        Vector Vij =  Pij.subtract(location);

        //Returns the ray from the point by i j
        return new Ray(location,Vij);
    }

    /**
     * Renders the image of the current scene using the implemented ray tracing algorithm.
     *
     * @throws MissingResourceException if any of the necessary fields are null.
     * @throws UnsupportedOperationException if the implementation is not supported.
     */
    public void renderImage() throws MissingResourceException, UnsupportedOperationException {
        if (Vto == null || Vup == null || Vright == null || imageWriter == null || location == null || rayTracer == null) {
            throw new MissingResourceException("One or more necessary fields are null.", "Camera", "");
        }
        try {

            int nx = imageWriter.getNx();
            int ny = imageWriter.getNy();
            if (nx <= 0 || ny <= 0) {
                throw new IllegalArgumentException("Invalid image dimensions");
            }

            for (int i = 0; i < nx; i++) {
                for (int j = 0; j < ny; j++) {
                    Ray ray = constructRay(nx, ny, i, j);
                    imageWriter.writePixel(i, j, castRay(ray));
                }
            }

        } catch (Exception e) {
            throw new UnsupportedOperationException("Failed to render image", e);
        }
    }


    /**

     Prints a grid on the image using the specified color and interval.
     @param interval The spacing between the grid lines.
     @param color The color of the grid lines.
     @throws MissingResourceException if the imageWriter field is null.
     arduino
     Copy code
     The exception message will be "The ImageWriter object is not initialized properly.".
     */
    public void printGrid(int interval ,Color color ){
        if(imageWriter != null){
            for (int i = 0; i<imageWriter.getNx() ; i++){
                for (int j =0;j<imageWriter.getNy() ;j++){
                    if(i % interval  == 0 || j % interval ==  0){
                        imageWriter.writePixel(i,j, color);
                    }
                }
            }
        }
        else {
            throw new MissingResourceException("The ImageWriter object is not initialized properly.", "ImageWriter", "");
        }
    }

    /**

     Writes the rendered image to the output file using the image writer.
     @throws MissingResourceException if the image writer is null.
     */
    public void writeToImage() throws MissingResourceException {
        if (imageWriter == null) {
            throw new MissingResourceException("The image writer is null.", "Camera", "");
        }
        imageWriter.writeToImage();
    }

    private Color castRay(Ray ray){

        return this.rayTracer.traceRay(ray);
    }

}
