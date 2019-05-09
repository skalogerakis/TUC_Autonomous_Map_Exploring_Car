package MainPackage;

import javax.swing.*;
import java.awt.*;

// This is the JPanel replacement for mazes that stores as a data
// element the maze and calls the mazes's drawing function
/*
 * MazePanel.java
 * Author: Stefanos Kalogerakis, Aris Zervakis
 * This is the JPanel replacement for mazes that stores as a data
 * element the maze and calls the mazes's drawing function
 */
class MazePanel extends JPanel
{
    private Maze maze; // the maze object

    protected MazePanel(Maze theMaze)
    {
        maze = theMaze;
    }

    // The paintComponent method is called every time
    // that the panel needs to be displayed or refreshed.
    // Anything you want drawn on the panel should be drawn
    // in this method.
    protected void paintComponent(Graphics page)
    {
        super.paintComponent(page);
        setBackground(Color.white); // set preferredSize for JScrollPane

        this.setPreferredSize(maze.windowSize()); // draw the maze and the solution
        maze.drawTester(page);

    }


}