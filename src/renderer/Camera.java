package renderer;

import primitives.*;

import javax.print.CancelablePrintJob;
import java.util.stream.*;
import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

import static java.lang.System.out;
import static primitives.Util.*;

public class Camera {
    private Point location;
    private Vector Vto, Vup, Vright;
    private double width, height, distance;
    private ImageWriter imageWriter;
    private RayTracerBase rayTracer;
    private  int rayNum = 1;
    private  boolean superSampling = false;

    public Camera setSuperSampling(boolean superSampling) {
        this.superSampling = superSampling;
        return this;
    }

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
     * @param rayTracer the ray tracer base to be used for rendering the scene.
     * @return the camera instance with the updated ray tracer base.
     */
    public Camera setRayTracer(RayTracerBase rayTracer) {
        this.rayTracer = rayTracer;
        return this;
    }

    /**
     *
     * @param rayNum
     * @return
     */
    public Camera setRayNum(int rayNum) {
        this.rayNum = rayNum;
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
     * Calculates the middle point of a pixel in the image plane.
     *
     * @param nX the number of pixels in the X-axis
     * @param nY the number of pixels in the Y-axis
     * @param j the column index of the pixel
     * @param i the row index of the pixel
     * @param Rx the pixel width
     * @param Ry the pixel height
     * @return the middle point of the pixel
     */
    public Point middlePoint(int nX, int nY, int j, int i,double Rx,double Ry){

        //Image center
        Point Pc = location.add(Vto.scale(distance));

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
        return Pij;
    }

    /**
     * Constructs the adaptive supersampling for a specific pixel.
     *
     * @param nX the number of pixels in the X-axis
     * @param nY the number of pixels in the Y-axis
     * @param j the column index of the pixel
     * @param i the row index of the pixel
     * @return the ColorRay representing the computed color of the pixel
     */
    public ColorRay constructAdaptiveSuperSampling(int nX, int nY, int j, int i) {
        //Calculate the size of each pixel
        double Rx = width / nX;
        double Ry = height / nY;

        Point mid = middlePoint(nX, nY,j,i,Rx,Ry);
        return AdaptiveSuperSampling(mid,Rx,Ry,this.rayNum);
    }


    /**
     * Constructs a list of rays for a given pixel position.
     *
     * @param nX The number of pixels in the X direction.
     * @param nY The number of pixels in the Y direction.
     * @param j  The column index of the pixel.
     * @param i  The row index of the pixel.
     * @return A list of rays for the given pixel position.
     */
    public List<Ray> constructRays(int nX, int nY, int j, int i ) {

        //Image center
        Point Pc = location.add(Vto.scale(distance));

        //Calculate the size of each pixel
        double Rx = width / nX;
        double Ry = height / nY;

        //Calculation of displacement according to i j
        double Xj = (j - (double) (nX - 1) / 2) * Rx;
        double Yi = -(i - (double) (nY - 1) / 2) * Ry;

        //Calculating the pixels function according to i j and gives a point
        Point Pij = Pc;
        if (alignZero(Xj) != 0) {
            Pij = Pij.add(Vright.scale(Xj));
        }
        if (alignZero(Yi) != 0) {
            Pij = Pij.add(Vup.scale(Yi));
        }



        // Create a list to store the rays
        List<Ray> listRay = new LinkedList<>();
        Random random = new Random();

        // Generate rays with random offsets within the pixel
        for (int k = 0;k < this.rayNum; k++){
            Point p;

            // Calculate the random position within the pixel
            p = Pij.add(Vright.scale(random.nextDouble(-Rx /2, Rx /2)));
            p = p.add(Vup.scale(random.nextDouble(-Ry /2, Ry /2)));

            //Calculation of the vector from the point to the screen according to i j with the addition of randomization
            Vector Vij =  p.subtract(location);

            // Create a ray from the location to the point
            Ray ray = new Ray(location, Vij);
            listRay.add(ray);
        }

        return listRay;
    }


    /**
     * Performs adaptive supersampling to compute the color of a pixel.
     *
     * @param rayIntersect the point of intersection between the ray and the scene
     * @param Rx the pixel width
     * @param Ry the pixel height
     * @param rayNum the number of rays to cast for supersampling
     * @return the ColorRay representing the computed color of the pixel
     */
    public ColorRay AdaptiveSuperSampling(Point rayIntersect, double Rx, double Ry ,int rayNum) {

        // Cast rays for the four corners of the pixel
        ColorRay topLeft = castRay(Rx, Ry, -1, -1 ,rayIntersect);
        ColorRay topRight = castRay( Rx, Ry, 1, -1,rayIntersect);
        ColorRay bottomLeft = castRay( Rx, Ry, -1, 1,rayIntersect);
        ColorRay bottomRight = castRay(Rx, Ry, 1, 1,rayIntersect);

        // Check if all four colors are similar or the rayNum has reached the limit
        if ((topLeft.getColor().equals(topRight.getColor())
                && topLeft.getColor().equals(bottomLeft.getColor())
                && topLeft.getColor().equals(bottomRight.getColor()))
        || rayNum <=0 ) {

            // Return the colorRay if all four colors are similar or rayNum limit reached
            return topLeft;
        } else {
            // Recursively divide the pixel and perform adaptive supersampling
            double newRx = Rx / 2;
            double newRy = Ry / 2;

            // Compute the four subpixel points within the pixel
            Point A = rayIntersect
                    .add(this.Vup.scale((Ry/2)*-1)
                    .add(this.Vright.scale((Rx/2)*-1)));

            Point B = rayIntersect
                    .add(this.Vup.scale((Ry/2)*1)
                            .add(this.Vright.scale((Rx/2)*-1)));

            Point C = rayIntersect
                    .add(this.Vup.scale((Ry/2)*-1)
                            .add(this.Vright.scale((Rx/2)*1)));

            Point D = rayIntersect
                    .add(this.Vup.scale((Ry/2)*1)
                            .add(this.Vright.scale((Rx/2)*1)));

            // Recursively compute the color of the subpixels
            ColorRay topLeftSubpixel = AdaptiveSuperSampling(A, newRx, newRy, rayNum / 4);
            ColorRay topRightSubpixel = AdaptiveSuperSampling(B, newRx, newRy, rayNum / 4);
            ColorRay bottomLeftSubpixel = AdaptiveSuperSampling(C, newRx, newRy, rayNum / 4);
            ColorRay bottomRightSubpixel = AdaptiveSuperSampling(C, newRx, newRy, rayNum / 4);

            // Compute the average color of the subpixels
            Color averageColor = topLeftSubpixel.getColor()
                    .add(topRightSubpixel.getColor())
                    .add(bottomLeftSubpixel.getColor())
                    .add(bottomRightSubpixel.getColor())
                    .reduce(4);

            // Return the ColorRay representing the average color
            return new ColorRay(new Ray(location, rayIntersect.subtract(location)), averageColor);
        }
    }

    /**
     * Casts a ray from a specific corner of the pixel.
     *
     * @param Rx the pixel width
     * @param Ry the pixel height
     * @param offsetX the offset along the X-axis for the corner
     * @param offsetY the offset along the Y-axis for the corner
     * @param mid the midpoint of the pixel
     * @return the ColorRay representing the computed color of the ray
     */
    private ColorRay castRay(double Rx, double Ry, int offsetX, int offsetY, Point mid) {
        // Calculate the displaced ray at the corner of the pixel
        Point cornerPoint = mid
                .add(this.Vup.scale((Ry/2)*offsetY)
                .add(this.Vright.scale((Rx/2)*offsetX)));
        Ray cornerRay = new Ray(location, cornerPoint.subtract(location));

        // Perform ray casting and return the ColorRay
        Color color = castRay(cornerRay);
        return new ColorRay(cornerRay, color);
    }



    /**
     * Renders the image of the current scene using the implemented ray tracing algorithm.
     *
     * @throws MissingResourceException if any of the necessary fields are null.
     * @throws UnsupportedOperationException if the implementation is not supported.
     */
    public Camera renderImage() throws MissingResourceException, UnsupportedOperationException {
        // Check if all necessary fields are provided
        if (Vto == null || Vup == null || Vright == null || imageWriter == null || location == null || rayTracer == null || distance <= 0.0|| height <= 0.0|| width <= 0.0) {
            throw new MissingResourceException("One or more necessary fields are null.", "Camera", "");
        }
        try {
            // Get the dimensions of the image
            int nx = imageWriter.getNx();
            int ny = imageWriter.getNy();

            // Validate the image dimensions
            if (nx <= 0 || ny <= 0) {
                throw new IllegalArgumentException("Invalid image dimensions");
            }

            Camera c = this;
            Pixel.initialize(ny,nx,1);
            IntStream.range(0,ny).parallel().forEach(i -> {
                IntStream.range(0,nx).parallel().forEach(j -> {
                // Check if multiple rays per pixel are used
                    //out.println("i - " + i + ", j - " + j );
                    if (this.rayNum > 1) {
                        if(!this.superSampling) {
                            // Construct a list of rays for the current pixel
                            List<Ray> rays = constructRays(nx, ny, i, j);

                            // Cast the rays and write the resulting color to the image
                            imageWriter.writePixel(i, j, castRays(rays));
                        }
                        else {
                            ColorRay colorRay = constructAdaptiveSuperSampling(nx, ny, i, j);
                            imageWriter.writePixel(i, j, colorRay.getColor());
                        }

                    }
                    else {
                        // Construct a single ray for the current pixel
                        Ray ray = constructRay(nx, ny, i, j);
                        // Cast the ray and write the resulting color to the image
                        imageWriter.writePixel(i, j, castRay(ray));
                    }
                });
            });

        } catch (Exception e) {
            throw new UnsupportedOperationException("Failed to render image", e);
        }
        return this;
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

    /**
     * Casts a single ray and retrieves the color of the traced ray.
     *
     * @param ray The ray to trace.
     * @return The color obtained by tracing the ray.
     */
    private Color castRay(Ray ray){

        return this.rayTracer.traceRay(ray);
    }

    /**
     * Casts a list of rays and computes the average color of the traced rays.
     *
     * @param rays The list of rays to trace.
     * @return The average color obtained by tracing the rays.
     */
    private Color castRays(List<Ray> rays) {
        Color color = new Color(0,0,0);

        // Trace each ray and accumulate the colors
        for (Ray ray : rays) {
            color = color.add( this.rayTracer.traceRay(ray));
        }

        // Reduce the color by the number of rays to compute the average
        return color.reduce(rays.size());
    }


    /**
     * Adjusts the horizontal rotation of the camera left/right around the Vup vector.
     * @param angle The angle in degrees (0 to 360) representing the horizontal rotation.
     * @return The camera object after the rotation.
     */
    public Camera adjustHorizontalRotation(double angle) {
        // Convert the angle from degrees to radians.
        double angleInRadians = Math.toRadians(angle);

        // Calculate the new Vto vector by scaling the current VTo vector by the cosine of the angle
        // and adding the scaled Vright vector multiplied by the sine of the angle.
        this.Vto = Vto.scale(Math.cos(angleInRadians)).add(Vright.scale(Math.sin(angleInRadians)));

        // Calculate the new VRight vector by scaling the current VRight vector by the cosine of the angle
        // and subtracting the scaled Vto vector multiplied by the sine of the angle.
        this.Vright = Vright.scale(Math.cos(angleInRadians)).subtract(Vto.scale(Math.sin(angleInRadians)));

        // Return the camera after the horizontal rotation.
        return this;
    }


    /**
     * Adjusts the longitudinal rotation of the camera around the Vto vector.
     * @param angle the angle in degrees (0 to 360)
     * @return the camera after the rotation
     */
    public Camera rotateLongitudinally(double angle) {
        // Convert the angle from degrees to radians.
        double angleInRadian = Math.toRadians(angle);

        // Calculate the new Vup vector by scaling the current Vup vector by the cosine of the angle
        // and subtracting the scaled Vright vector multiplied by the sine of the angle.
        this.Vup = Vup.scale(Math.cos(angleInRadian)).subtract(Vright.scale(Math.sin(angleInRadian)));

        // Calculate the new Vright vector by scaling the current Vright vector by the cosine of the angle
        // and adding the scaled Vup vector multiplied by the sine of the angle.
        this.Vright = Vright.scale(Math.cos(angleInRadian)).add(Vup.scale(Math.sin(angleInRadian)));

        // Return the updated Camera object with the rotated orientation.
        return this;
    }

    /**
     * tilt is the rotation of the camera up/down around the Vright vector
     * @param angle the angle in degree (0 to 360)
     * @return the camera after the rotation
     */
    public Camera tiltCamera(double angle) {
        // Convert the angle from degrees to radians.
        double angleInRadian = Math.toRadians(angle);

        Vector to = this.Vto;
        Vector up = this.Vup;
        // Calculate the new VTo vector by scaling the current VTo vector by the cosine of the angle
        // and subtracting the scaled VUp vector multiplied by the sine of the angle.
        this.Vto = (to.scale(Math.cos(angleInRadian)).subtract(up.scale(Math.sin(angleInRadian))));

        this.Vup = this.Vright.crossProduct(this.Vto);
        // Calculate the new VUp vector by scaling the current VUp vector by the cosine of the angle
        // and adding the scaled VTo vector multiplied by the sine of the angle.
        //this.Vup = (up.scale(Math.sin(angleInRadian)).add(to.scale(Math.cos(angleInRadian))));

        // Return the camera after the tilt rotation.
        return this;
    }

    /**
     * Moves the camera up or down by the specified amount.
     *
     * @param num The amount to move the camera up or down.
     * @return A new camera object with the updated position and orientation.
     */
    public Point upDown(double num){
        return location.add(this.Vup.scale(num));
    }

    /**
     * Moves the camera in or out by the specified amount.
     *
     * @param num The amount to move the camera in or out.
     * @return A new camera object with the updated position and orientation.
     */
    public Point inOut(double num){
        return location.add(this.Vto.scale(num));
    }

    /**
     * Moves the camera right or left by the specified amount..
     *
     * @param num The amount to move the camera right or left.
     * @return A new camera object with the updated position and orientation.
     */
    public Point rightLeft(double num){
        return location.add(this.Vright.scale(num));
    }

    /**
     * Moves the camera on a sphere around a specified axis.
     *
     * @param radius the radius of the sphere
     * @param axis the axis point around which the camera moves
     * @param alpha the angle (in degrees) along the latitude of the sphere
     * @param beta the angle (in degrees) along the longitude of the sphere
     * @return the updated camera object with the new position
     */
    public Camera moveCameraOnSphere(double radius ,Point axis, double alpha , double beta) {

        // Calculate the coordinates on the sphere using spherical coordinates
        double a = radius * Math.cos(Math.toRadians(alpha)) * Math.sin(Math.toRadians(beta));
        double b = radius * Math.sin(Math.toRadians(alpha)) * Math.cos(Math.toRadians(beta));
        double c = radius * Math.cos(Math.toRadians(beta));

        Point point = new Point(a, b, c);

        // Calculate the new direction vector from the axis to the point on the sphere
        Vto = axis.subtract(point);

        // Create a new camera object with the updated position and orientation
        return new Camera(point,this.Vto,this.Vright.crossProduct(this.Vto))
                .setVPSize(width, height).setVPDistance(distance).setRayNum(this.rayNum);
    }


    public Camera moveCameraOnSphereSimply(double radius , Point axis, double alpha , double beta) {
        //alpha
        double height = Math.sin(Math.toRadians(alpha))*radius;
        double length = -Math.sin(Math.toRadians(180-alpha-90))*radius + radius;

        location = inOut(length);
        location = upDown(height);

        // Calculate the new direction vector from the axis to the point on the sphere
        Vto = axis.subtract(location).normalize();
        Vup = this.Vright.crossProduct(this.Vto).normalize();

        //beta
        height = Math.sin(Math.toRadians(beta))*radius;
        length = -Math.sin(Math.toRadians(180-beta-90))*radius + radius;

        location = inOut(length);
        location = rightLeft(height);

        // Calculate the new direction vector from the axis to the point on the sphere
        Vto = axis.subtract(location).normalize();
        Vright = this.Vup.crossProduct(this.Vto).normalize();

        // Create a new camera object with the updated position and orientation
        return new Camera(location,this.Vto,this.Vup)
                .setVPSize(width, this.height).setVPDistance(distance).setRayNum(this.rayNum);
    }



}
