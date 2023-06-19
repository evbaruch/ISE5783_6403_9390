package primitives;

import geometries.Triangle;

import java.util.*;

public class Mesh {
    private List<Point> points;
    private List<List<Integer>> triangles;

    /**
     * Constructs an empty mesh.
     */
    public Mesh() {
        this.points = null;
        this.triangles = null;
    }

    /**
     * Constructs a mesh with the specified points and triangles.
     * @param points The list of points in the mesh.
     * @param triangles The list of triangles in the mesh.
     */
    public Mesh(List<Point> points, List<List<Integer>> triangles) {
        this.points = points;
        this.triangles = triangles;
    }

    /**
     * Converts a Mesh object into a list of Triangle objects.
     * @param mesh The mesh to convert.
     * @return A list of triangles representing the mesh.
     */
    public static List<Triangle> meshToTriangles(Mesh mesh) {
        List<Triangle> triangleList = new ArrayList<>();

        // Iterate over each set of triangle indices in the mesh
        for (List<Integer> triangleIndices : mesh.triangles) {
            // Retrieve the three points of the triangle using the indices
            Point p1 = mesh.points.get(triangleIndices.get(0));
            Point p2 = mesh.points.get(triangleIndices.get(1));
            Point p3 = mesh.points.get(triangleIndices.get(2));

            // Create a Triangle object using the three points
            Triangle triangle = new Triangle(p1, p2, p3);

            // Add the Triangle object to the list
            triangleList.add(triangle);
        }

        // Return the list of triangles
        return triangleList;
    }


    /**
     * Converts a list of Triangle objects into a Mesh object.
     * @param triangles The list of triangles to convert.
     * @return A Mesh object representing the triangles.
     */
    public static Mesh trianglesToMesh(List<Triangle> triangles) {
        // Create lists to store points and triangle indices
        List<Point> pointList = new ArrayList<>();
        List<List<Integer>> triangleIndicesList = new ArrayList<>();

        // Iterate over each triangle in the list
        for (Triangle triangle : triangles) {
            // Create a list to store the triangle indices
            List<Integer> triangleIndices = new ArrayList<>();

            // Iterate over each vertex of the triangle
            for (Point point : triangle.getVertices()) {
                // Check if the point already exists in the point list
                int index = pointList.indexOf(point);

                // If the point does not exist, add it to the list
                if (index == -1) {
                    pointList.add(point);
                    index = pointList.size() - 1;
                }

                // Add the index to the triangle indices
                triangleIndices.add(index);
            }

            // Add the triangle indices to the triangle indices list
            triangleIndicesList.add(triangleIndices);
        }

        // Create a new Mesh object with the point list and triangle indices list
        return new Mesh(pointList, triangleIndicesList);
    }


    /**
     * Performs an edge collapse algorithm on a mesh to reduce the number of triangles.
     * @param mesh The original mesh to collapse.
     * @param numOfTriangles The desired number of triangles after collapsing.
     * @return The collapsed mesh.
     */
    public static Mesh edgeCollapseAlgorithm(Mesh mesh, int numOfTriangles) {
        // Create a copy of the original mesh
        Mesh collapsedMesh = new Mesh(mesh.points, mesh.triangles);

        // Repeat until the number of triangles is reduced to the desired amount
        while (collapsedMesh.triangles.size() > numOfTriangles) {


            // Step 1: Find the shortest edge
            double shortestEdgeLength = Double.MAX_VALUE;
            int shortestEdgeIndex = -1;
            int indexA = -1;
            int indexB = -1;

            // Iterate over each triangle in the mesh
            for (int i = 0; i < collapsedMesh.triangles.size(); i++) {
                List<Integer> triangleIndices = collapsedMesh.triangles.get(i);

                // Iterate over each edge in the triangle
                for (int j = 0; j < 3; j++) {
                    int pointA = triangleIndices.get(j);
                    int pointB = triangleIndices.get((j + 1) % 3);

                    // Get the vertices of the edge
                    Point v1 = collapsedMesh.points.get(pointA);
                    Point v2 = collapsedMesh.points.get(pointB);

                    // Calculate the length of the edge
                    double edgeLength = v1.distance(v2);

                    // Update the shortest edge information if a shorter edge is found
                    if (edgeLength < shortestEdgeLength) {
                        shortestEdgeLength = edgeLength;
                        indexA = j;
                        indexB = (j + 1) % 3;
                        shortestEdgeIndex = i;
                    }
                }
            }

            // If no valid edge is found, exit the loop
            if (shortestEdgeIndex == -1) {
                break;
            }

            // Step 2: Perform edge collapse
            List<Integer> triangleIndices = collapsedMesh.triangles.get(shortestEdgeIndex);
            int pointA = triangleIndices.get(indexA);
            int pointB = triangleIndices.get(indexB);

            // Remove vertex B from the mesh
            collapsedMesh.points.remove(pointB);

            // Update the triangle indices to account for the removed vertex and reassign indices
            Iterator<List<Integer>> iterator = collapsedMesh.triangles.iterator();
            while (iterator.hasNext()) {
                List<Integer> indices = iterator.next();

                for (int i = 0; i < 3; i++) {
                    if (indices.get(i) == pointB) {
                        indices.set(i, pointA);
                    }

                    // Decrement indices greater than or equal to the removed vertex
                    if (indices.get(i) >= pointB) {
                        indices.set(i, indices.get(i) - 1);
                    }
                }

                // Remove any triangles with duplicate vertex indices
                if (hasDuplicatesINT(indices)) {
                    iterator.remove();
                }
            }
        }

        // Return the collapsed mesh
        return collapsedMesh;
    }


    /**
     * Checks if a list of integers contains any duplicates.
     * @param myList The list of integers to check.
     * @return true if the list contains duplicates, false otherwise.
     */
    private static boolean hasDuplicatesINT(List<Integer> myList) {
        boolean hasDuplicates = false;

        // Iterate over each element in the list (excluding the last element)
        for (int i = 0; i < myList.size() - 1; i++) {
            // Iterate over the remaining elements in the list
            for (int j = i + 1; j < myList.size(); j++) {
                // Check if the current element is equal to any of the remaining elements
                if (myList.get(i).equals(myList.get(j))) {
                    hasDuplicates = true;
                    break;
                }
            }

            // If duplicates are found, exit the loop
            if (hasDuplicates) {
                break;
            }
        }

        return hasDuplicates;
    }

    public List<Point> getPoints() {
        return points;
    }

    public List<List<Integer>> getTriangles() {
        return triangles;
    }

}
