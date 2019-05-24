package MainPackage;

/*
 * MainApp.java
 * Author : Stefanos Kalogerakis, Aris Zervakis
 * Simple java app that is responsible to visualize data from input file.
 * This input file corresponds to maze produced from lego mindstorms.
 * Input file is given as parameter from terminal. If file does not
 * exist or it has wrong format the process terminates
 */
import javax.swing.*;
import java.awt.*;


import static java.lang.System.exit;

public class MainApp
{
    public static void main(String[] args)
    {
        try
        {

            FileInputReader fr = new FileInputReader(); //First read input file


            int[][] inData = fr.input2Darr(args[0]); //inData contains the input file in a 2D array

            Maze maze = new Maze(inData); // Constructs the maze object

            JFrame frame = new JFrame("Maze Visualizer");
            MazePanel panel = new MazePanel(maze); // Constructs the panel to hold the maze

            JScrollPane scrollPane = new JScrollPane(panel);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);                                        //Window is not resisable
            frame.setSize(fr.finArrSize * 80, fr.finArrSize * 80);    //Window size works depending on the size of the array
            frame.add(panel, BorderLayout.CENTER);
            frame.setVisible(true);                                             //make window visible

        } catch (ArrayIndexOutOfBoundsException io){
            System.err.println("No input file was given.Please give path of file you wish to print the maze!") ;
            exit(-9);
        }
    }
}