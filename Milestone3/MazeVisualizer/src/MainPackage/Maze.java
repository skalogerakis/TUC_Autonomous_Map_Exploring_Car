package MainPackage;

/*
 * Maze.java
 * Author: Stefanos Kalogerakis, Aris Zervakis
 * This is where most of the stuff is implemented. We take the input as a 2d array
 * and in case the value is 1 we consider it is in our path and we draw it. In case value is 2 we
 * have our termination point. We represent that with a red dot. In case our value is 0 we don't do anything
 */
import javax.swing.*;
import java.awt.*;



public class Maze extends JFrame
{
    private static final int CELL_WIDTH = 70; // maze square size
    private static final int MARGIN = 70; // buffer between window edge and maze
    private static final int DOT_SIZE = 10; // size of maze solution dot
    protected int[][] maze;

    public Maze(int[][] myMaze){
        maze = myMaze;
    }

    /*
     * Static declaration of initial maze.TODO change that when we learn the final format
     * THIS WAS OUR INITIAL APPROACH
     */
//    public int[][] mazeStatic={							// initialize the maze 2D array
//            {1,0,1,0,0,1,0,0,0,1},
//            {1,0,1,1,1,1,1,1,1,1},
//            {1,0,1,0,0,0,0,1,0,1},
//            {1,1,1,0,0,0,0,0,0,0},
//            {0,0,1,1,1,1,1,1,1,0},
//            {0,0,0,0,1,0,0,0,1,1},
//            {0,0,0,0,1,1,0,0,0,0},
//            {0,0,0,0,0,1,0,0,1,0},
//            {0,0,0,0,1,1,1,1,1,2},
//            {0,0,0,0,0,1,0,1,1,0}};


    protected void drawTester(Graphics g){

        FileInputReader fr = new FileInputReader();
        g.setColor(Color.BLACK);

        for(int i=0;i< fr.finArrSize;i++ ){
            for(int j=0;j< fr.finArrSize;j++){

                //Unless we find path or termination point continue
                if(maze[i][j]!=1 && maze[i][j]!=2){
                    continue;
                }

                try{
                    if(maze[i+1][j] != 0 ){        //THIS IS SOUTH CASE
                        g.drawLine(j * CELL_WIDTH + MARGIN, i * CELL_WIDTH + MARGIN, j * CELL_WIDTH + MARGIN, (i+1) * CELL_WIDTH + MARGIN);
                    }


                }catch(ArrayIndexOutOfBoundsException oe){

                }

                try{
                    if(maze[i][j+1] != 0){        //THIS IS WEST CASE
                        //System.out.println("EAST Coor X "+i+" Coor Y "+j+ " VAL "+ mazeStatic[i][j+1]);
                        g.drawLine((j) * CELL_WIDTH + MARGIN, i * CELL_WIDTH + MARGIN, (j+1) * CELL_WIDTH + MARGIN, i * CELL_WIDTH + MARGIN);
                    }

                }catch(ArrayIndexOutOfBoundsException oe){

                }

                if(maze[i][j]==2){    //When array has the value 2, it means we are on termination point. DOT_SIZE/2 is used in order to find the circle of the circle we are drawing
                    g.setColor(Color.RED);
                    g.fillOval(j * CELL_WIDTH + MARGIN-(DOT_SIZE/2), i * CELL_WIDTH + MARGIN-(DOT_SIZE/2), DOT_SIZE, DOT_SIZE);
                    g.setColor(Color.BLACK);
                }


            }
        }

    }

    protected Dimension windowSize() // returns the ideal size of the window (for JScrollPanes)
    {
        return new Dimension(CELL_WIDTH + MARGIN * 2, CELL_WIDTH + MARGIN * 2);
    }
}