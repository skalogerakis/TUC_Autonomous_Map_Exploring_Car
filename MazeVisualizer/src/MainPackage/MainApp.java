package MainPackage;

/*
 * MainApp.java
 * Author : Stefanos Kalogerakis, Aris Zervakis
 * Simple java app that is responsible to visualize data from input file.
 * This input file corresponds to maze produced from lego mindstorms.
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class MainApp
{
    public static void main(String[] args) throws IOException, InterruptedException
    {
        try
        {
            int MatrixSize = 10;

            Maze maze = new Maze(); // Constructs the maze object

            JFrame frame = new JFrame("Maze Visualizer");
            MazePanel panel = new MazePanel(maze); // Constructs the panel to hold the maze

            JScrollPane scrollPane = new JScrollPane(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);                                        //Window is not resisable
            frame.setSize(MatrixSize * 60, MatrixSize * 60);    //TOOD maybe find a better formula
            frame.add(panel, BorderLayout.CENTER);

            frame.setVisible(true);                                             //make window visible

        }
        catch(NumberFormatException exception)
        {
            System.out.println("The input number for the maze size must be an integer") ;
        }
    }
}