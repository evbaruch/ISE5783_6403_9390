package guiWindow;

import javax.swing.*;

import javax.swing.JFrame;
import java.awt.*;

public class MainWindow {

    public void grid(JFrame frame)
    {
        // Create a JPanel to hold the grid layout
        JPanel panel = new JPanel(new GridLayout(0, 2)); // 0 rows and 2 columns

        // Add labels to the left column
        panel.add(new JLabel("Label 1:"));
        panel.add(new JLabel("Label 2:"));

        // Create a JPanel for the right column with three rows
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));

        // Add labels to the right column
        rightPanel.add(new JLabel("Row 1"));
        rightPanel.add(new JLabel("Row 2"));
        rightPanel.add(new JLabel("Row 3"));

        // Add the left and right panels to the main panel
        panel.add(rightPanel);

        // Add the main panel to the frame
        frame.add(panel);
    }

    public void foo(JFrame frame)
    {
        // Create a list of items
        String[] items = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

        // Create a JList with the items
        JList<String> listBox = new JList<>(items);

        // Create a JScrollPane to hold the JList
        JScrollPane scrollPane = new JScrollPane(listBox);

        // Add the JScrollPane to the frame
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public MainWindow() {
        // Create a JFrame (window) with a title
        JFrame frame = new JFrame("Main window");


        this.foo(frame);
        this.grid(frame);

        // Set the size of the frame
        frame.setSize(400, 200);

        // Set the default close operation (exit when the window is closed)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainWindow(); // Create an instance of MainWindow
    }
}

