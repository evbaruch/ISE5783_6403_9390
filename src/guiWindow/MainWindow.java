package guiWindow;
import geometries.Geometries;
import geometries.Sphere;
import lighting.SpotLight;
import org.w3c.dom.Element;
import primitives.Material;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class MainWindow {

    private static int currentCameraIndex = 0;

    private static List<Camera> cameras = new LinkedList<>();
    private static JLabel imageLabel = new JLabel();

    // Add this method to display the image from the current camera
    private static void displayImageFromCurrentCamera() {
        if (currentCameraIndex >= 0 && currentCameraIndex < cameras.size()) {
            Camera currentCamera = cameras.get(currentCameraIndex);
            currentCamera.renderImage().writeToImage();
            ImageIcon image = new ImageIcon("images/GUIProjectImage" + currentCameraIndex + ".png");
            imageLabel.setIcon(image);
        }
    }
    public static void main(String[] args) {
        // Create the main JFrame
        JFrame frame = new JFrame("Render Project GUI");

        // Set the default close operation (exit when the window is closed)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Center the frame on the screen
        frame.setLocation( 350, 100);

        // Make the frame visible
        frame.setSize(800, 600);

        // Create a panel for the buttons at the top
        frame.setLayout(new BorderLayout());

        // Create a split panel for the image and tabs
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

        // Set the divider location (between the image and tabs)
        splitPane.setDividerLocation(0.6); // 80% for the image

        // Add the split pane to the frame
        frame.add(splitPane);

        // Create a new panel for the image and the Previous & Next buttons(80%)
        JPanel leftPanel = new JPanel();

        // Create a panel for the image (90% of the left panel)
        JPanel imagePanel = new JPanel();
        // Create a panel for the "Previous" and "Next" buttons (10% of the left panel)
        JPanel buttonPanel = new JPanel();
        splitPane.setLeftComponent(leftPanel);

        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(imagePanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the image label to the image panel
        imagePanel.add(imageLabel);
        splitPane.setResizeWeight(0.8169);

        // Add navigation buttons for the slideshow
        JButton prevButton = new JButton("Previous");
        JButton nextButton = new JButton("Next");

        // Add the "Previous" and "Next" buttons to the button panel
        buttonPanel.add(prevButton);
        buttonPanel.add(nextButton);

        // Set the frame to be visible
        frame.setVisible(true);

        // Add action listeners for navigation buttons
        prevButton.addActionListener(e -> {
            if (currentCameraIndex > 0) {
                currentCameraIndex--;
                displayImageFromCurrentCamera();
                // clear the image panel
                imagePanel.removeAll();

                // Add the image label to the image panel
                imagePanel.add(imageLabel);

                // Refresh the image panel
                imagePanel.revalidate();

                // Repaint the image panel (refresh the panel in case image is not shown)
                imagePanel.repaint();
            }
        });

        nextButton.addActionListener(e -> {
            if (currentCameraIndex < cameras.size() - 1) {
                currentCameraIndex++;
                displayImageFromCurrentCamera();

                // clear the image panel
                imagePanel.removeAll();
                // Add the image label to the image panel
                imagePanel.add(imageLabel);

                // Refresh the image panel
                imagePanel.revalidate();

                // Repaint the image panel (refresh the panel in case image is not shown)
                imagePanel.repaint();

            }
        });




        // You can customize this part according to your rendering project

        // Create a tabbed pane for the tabs on the right (20%)
        JTabbedPane tabbedPane = new JTabbedPane();
        splitPane.setRightComponent(tabbedPane);

        // Create the Geometries tab
        JPanel geometriesPanel = new JPanel();
        tabbedPane.addTab("Geometries", geometriesPanel);

        // Add components to the geometriesPanel as needed

        // Create the Light tab
        JPanel lightPanel = new JPanel();
        tabbedPane.addTab("Light", lightPanel);

        // Add components to the lightPanel as needed

        // Create the Camera tab with three rows
        // one for adding a camera
        // second for cameras list
        // third for running the image
        JPanel cameraPanel = new JPanel();
        tabbedPane.addTab("Camera", cameraPanel);
        cameraPanel.setLayout(new GridLayout(3, 1));


        // Create a panel for the "Add Camera" button
        JPanel addCameraPanel = new JPanel();
        addCameraPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton addCameraButton = new JButton("Add Camera");
        addCameraPanel.add(addCameraButton);
        cameraPanel.add(addCameraPanel);

        // Create a panel for the cameras list
        JPanel camerasListPanel = new JPanel();
        camerasListPanel.setLayout(new BorderLayout());
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> camerasNames = new JList<>(model);
        camerasListPanel.add(new JScrollPane(camerasNames), BorderLayout.CENTER);

        // Create the Camera settings window (JDialog)
        JDialog cameraSettingsDialog = new JDialog(frame, "Camera Settings", true);
        cameraSettingsDialog.setSize(870, 200);
        cameraSettingsDialog.setLayout(new GridLayout(4, 3));

        // Add the cameras list panel to the camera tab
        cameraPanel.add(camerasListPanel);

        // Create a panel for the "Run" and "Save" buttons
        JPanel runButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton runButton = new JButton("Run");
        JButton saveImageButton = new JButton("Save");

        runButtonPanel.add(saveImageButton);
        runButtonPanel.add(runButton);

        cameraPanel.add(runButtonPanel);

        // Add input fields and labels for the Camera settings
        JPanel locationPanel = new JPanel();
        locationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        locationPanel.add(new JLabel("Location (X, Y, Z):"));
        JTextField locationX = new JTextField("");
        locationX.setPreferredSize(new Dimension(30, 30));
        JTextField locationY = new JTextField("");
        locationY.setPreferredSize(new Dimension(30, 30));
        JTextField locationZ = new JTextField("");
        locationZ.setPreferredSize(new Dimension(30, 30));
        locationPanel.add(locationX);
        locationPanel.add(locationY);
        locationPanel.add(locationZ);
        cameraSettingsDialog.add(locationPanel);

        JPanel directionPanel = new JPanel();
        directionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        directionPanel.add(new JLabel("Direction (X, Y, Z):"));
        JTextField directionX = new JTextField("");
        directionX.setPreferredSize(new Dimension(30, 30));
        JTextField directionY = new JTextField("");
        directionY.setPreferredSize(new Dimension(30, 30));
        JTextField directionZ = new JTextField("");
        directionZ.setPreferredSize(new Dimension(30, 30));
        directionPanel.add(directionX);
        directionPanel.add(directionY);
        directionPanel.add(directionZ);
        cameraSettingsDialog.add(directionPanel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rightPanel.add(new JLabel("Up (X, Y, Z):"));
        JTextField UpX = new JTextField("");
        UpX.setPreferredSize(new Dimension(30, 30));
        JTextField UpY = new JTextField("");
        UpY.setPreferredSize(new Dimension(30, 30));
        JTextField UpZ = new JTextField("");
        UpZ.setPreferredSize(new Dimension(30, 30));
        rightPanel.add(UpX);
        rightPanel.add(UpY);
        rightPanel.add(UpZ);
        cameraSettingsDialog.add(rightPanel);

        JPanel NxPanel = new JPanel();
        NxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        NxPanel.add(new JLabel("Nx:"));
        JTextField Nx = new JTextField("");
        Nx.setPreferredSize(new Dimension(40, 30));
        NxPanel.add(Nx);
        cameraSettingsDialog.add(NxPanel);

        JPanel NyPanel = new JPanel();
        NyPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        NyPanel.add(new JLabel("Ny:"));
        JTextField Ny = new JTextField("");
        Ny.setPreferredSize(new Dimension(40, 30));
        NyPanel.add(Ny);
        cameraSettingsDialog.add(NyPanel);


        JPanel vpSizePanel = new JPanel();
        vpSizePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        vpSizePanel.add(new JLabel("View Plane Size (Width, Height):"));
        JTextField vpWidth = new JTextField("");
        vpWidth.setPreferredSize(new Dimension(40, 30));
        JTextField vpHeight = new JTextField("");
        vpHeight.setPreferredSize(new Dimension(40, 30));
        vpSizePanel.add(vpWidth);
        vpSizePanel.add(vpHeight);
        cameraSettingsDialog.add(vpSizePanel);

        JPanel vpDistancePanel = new JPanel();
        vpDistancePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        vpDistancePanel.add(new JLabel("View Plane Distance:"));
        JTextField vpDistance = new JTextField("");
        vpDistance.setPreferredSize(new Dimension(40, 30));
        vpDistancePanel.add(vpDistance);
        cameraSettingsDialog.add(vpDistancePanel);

        JPanel numOfRaysPanel = new JPanel();
        numOfRaysPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        numOfRaysPanel.add(new JLabel("Number of Rays:"));
        JTextField numOfRays = new JTextField("");
        numOfRays.setPreferredSize(new Dimension(40, 30));
        numOfRaysPanel.add(numOfRays);
        cameraSettingsDialog.add(numOfRaysPanel);

        //the super sampling is boolean ,so we will use it with a checkbox
        JPanel superSamplingPanel = new JPanel();
        superSamplingPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        superSamplingPanel.add(new JLabel("Super Sampling:"));
        JCheckBox superSampling = new JCheckBox();
        superSamplingPanel.add(superSampling);
        cameraSettingsDialog.add(superSamplingPanel);

        // Add a ListSelectionListener to the camera names list
        camerasNames.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = camerasNames.getSelectedIndex();
                    if (selectedIndex >= 0) {
                        // Populate the camera settings dialog with the settings of the selected camera
                        Camera selectedCamera = cameras.get(selectedIndex);
                        // Set the text fields and checkboxes in the dialog based on selectedCamera's settings
                        locationX.setText(String.valueOf(selectedCamera.getLocation().getX()));
                        locationY.setText(String.valueOf(selectedCamera.getLocation().getY()));
                        locationZ.setText(String.valueOf(selectedCamera.getLocation().getZ()));

                        directionX.setText(String.valueOf(selectedCamera.getDirection().getX()));
                        directionY.setText(String.valueOf(selectedCamera.getDirection().getY()));
                        directionZ.setText(String.valueOf(selectedCamera.getDirection().getZ()));

                        UpX.setText(String.valueOf(selectedCamera.getUpX()));
                        UpY.setText(String.valueOf(selectedCamera.getUpY()));
                        UpZ.setText(String.valueOf(selectedCamera.getUpZ()));

                        Nx.setText(String.valueOf(selectedCamera.getImageWriterNx()));
                        Ny.setText(String.valueOf(selectedCamera.getImageWriterNy()));

                        vpWidth.setText(String.valueOf(selectedCamera.getWidth()));
                        vpHeight.setText(String.valueOf(selectedCamera.getHeight()));

                        vpDistance.setText(String.valueOf(selectedCamera.getDistance()));

                        numOfRays.setText(String.valueOf(selectedCamera.getnumOfRays()));

                        superSampling.setSelected(selectedCamera.isSuperSampling());
                    }

                        // Show the camera settings dialog
                        cameraSettingsDialog.setVisible(true);
                    }
                }
        });



        // Create a button for saving the Camera settings
        JPanel saveButtonCameraPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveCameraButton = new JButton("Save");
        saveButtonCameraPanel.add(saveCameraButton);
        cameraSettingsDialog.add(saveButtonCameraPanel);

        // Add an action listener to the "Save" button on camera setting to close the window
        saveCameraButton.addActionListener(e -> {

            // insert the camera to the list of cameras variable by variable and use try catch to check if the input is valid

            double x, y, z;
            try {
                x = Double.parseDouble(locationX.getText());
            }
            catch (Exception exception) {
                x = 0d;
            }
            try {
                y = Double.parseDouble(locationY.getText());
            }
            catch (Exception exception) {
                y = 0d;
            }
            try {
                z = Double.parseDouble(locationZ.getText());
            }
            catch (Exception exception) {
                z = 0d;
            }
            Point location = new Point(x, y, z);

            try {
                x = Double.parseDouble(directionX.getText());
            }
            catch (Exception exception) {
                x = 0d;
            }
            try {
                y = Double.parseDouble(directionY.getText());
            }
            catch (Exception exception) {
                y = 0d;
            }
            try {
                z = Double.parseDouble(directionZ.getText());
            }
            catch (Exception exception) {
                z = 0d;
            }
            //check if the direction is not zero vector
            if (x == 0 && y == 0 && z == 0) {
                //if it is zero vector we will pop an error message
                JOptionPane.showMessageDialog(cameraSettingsDialog, "Vector can't be zero vector", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Vector direction = new Vector(x, y, z);


            try {
                x = Double.parseDouble(UpX.getText());
            }
            catch (Exception exception) {
                x = 0d;
            }
            try {
                y = Double.parseDouble(UpY.getText());
            }
            catch (Exception exception) {
                y = 0d;
            }
            try {
                z = Double.parseDouble(UpZ.getText());
            }
            catch (Exception exception) {
                z = 0d;
            }
            //check if the right is not zero vector
            if (x == 0 && y == 0 && z == 0) {
                //if it is zero vector we will pop an error message
                JOptionPane.showMessageDialog(cameraSettingsDialog, "A Vector can't be zero vector", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Vector Up = new Vector(x, y, z);

            int nx , ny;

            try {
                nx = Integer.parseInt(Nx.getText());
            }
            catch (Exception exception) {
                nx = +0;
            }
            try {
                ny = Integer.parseInt(Ny.getText());
            }
            catch (Exception exception) {
                ny = +0;
            }

            Double width, height;
            try {
                width = Double.parseDouble(vpWidth.getText());
            }
            catch (Exception exception) {
                width = 0d;
            }
            try {
                height = Double.parseDouble(vpHeight.getText());
            }
            catch (Exception exception) {
                height = 0d;
            }

            double distance;
            try {
                distance = Double.parseDouble(vpDistance.getText());
            }
            catch (Exception exception) {
                distance = 0d;
            }

            int rays;
            try {
                rays = Integer.parseInt(numOfRays.getText());
            }
            catch (Exception exception) {
                rays = +0;
            }

            boolean sampling = superSampling.isSelected();

            Camera camera = new Camera(location, direction, Up)
                    .setVPSize(width, height).setVPDistance(distance)
                    .setRayNum(rays).setSuperSampling(sampling);

            ImageWriter imageWriter = new ImageWriter("GUIProjectImage" + cameras.size(), nx, ny);
            // Add the camera to the Jlist of cameras
            cameras.add(camera.setImageWriter(imageWriter));
            model.add(model.size(),"Camera " + cameras.size());

            //refresh the main window in order to see the new camera
            frame.repaint();

            // Hide the Camera settings window
            cameraSettingsDialog.setVisible(false);
        });


        // Add an action listener to the "Add Camera" button
        addCameraButton.addActionListener(e -> {
            // Show the Camera settings window
            cameraSettingsDialog.setVisible(true);
        });

        // Add an action listener to the "Save" button to close the window
        saveImageButton.addActionListener(e -> {
            // Hide the Camera settings window
            cameraSettingsDialog.setVisible(false);
        });





        // send the image to the renderer
        runButton.addActionListener(e -> {


//            Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0))
//                    .setVPSize(150, 150).setVPDistance(1000);
            Geometries gGeometries = new Geometries(
                    new Sphere(
                            new Point(5, 0, 30),
                            30d)
                            .setEmission(new primitives.Color(BLUE))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)
                            ),
                    new Sphere(
                            new Point(-5, 0, -30),
                            30d)
                            .setEmission(new primitives.Color(RED))
                            .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30))
            );

            Scene scene = new Scene.SceneBuilder("Test scene")
                    .setGeometries(gGeometries)
                    .setLights(
                            new SpotLight(
                                    new primitives.Color(1000, 600, 0),
                                    new Point(-100, -100, 500),
                                    new Vector(-1, -1, -2))
                                    .setKl(0.0004)
                                    .setKq(0.0000006))
                    .build();

            // run on all the cameras in the list
            for (Camera camera : cameras) {
                // Render all the images from all cameras and save it to the files "GUIProjectImageX.png" in the images folder
                camera.setRayTracer(new RayTracerBasic(scene))
                        .renderImage()
                        .writeToImage();
            }

            imagePanel.removeAll();

            // Load and display the image "KnightDragon.png"
            ImageIcon image = new ImageIcon("images/GUIProjectImage0.png");

            // Create a label for the image
            JLabel imageLabel = new JLabel(image);

            // Add the image label to the image panel
            imagePanel.add(imageLabel);

            // Set the divider location to 80%
            splitPane.setResizeWeight(0.8169);

            // Refresh the image panel
            imagePanel.revalidate();

            // Repaint the image panel (refresh the panel in case image is not shown)
            imagePanel.repaint();

                });

        // Set a fixed divider location for the split pane
        splitPane.setResizeWeight(0.8169); // 80% for the image

        // Set the frame to be visible
        frame.setVisible(true);
    }


}


