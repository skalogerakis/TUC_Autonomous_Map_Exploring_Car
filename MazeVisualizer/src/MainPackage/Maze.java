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
    public int testSize = 10;
    public static final int CELL_WIDTH = 50; // maze square size
    public static final int MARGIN = 50; // buffer between window edge and maze
    public static final int DOT_SIZE = 10; // size of maze solution dot
    public int CELL_DYN_WIDTH;
    public int CELL_DYN_HEIGHT;
    private boolean[] path; // array representing the unique path solution


    /*
     * Static declaration of initial maze.TODO change that when we learn the final format
     */
    public int[][] mazeStatic={							// initialize the maze 2D array
            {1,0,1,0,0,1,0,0,0,1},
            {1,0,1,1,1,1,1,1,1,1},
            {1,0,1,0,0,0,0,1,0,1},
            {1,1,1,0,0,0,0,0,0,0},
            {0,0,1,1,1,1,1,1,1,0},
            {0,0,0,0,1,0,0,0,1,1},
            {0,0,0,0,1,1,0,0,0,0},
            {0,0,0,0,0,1,0,0,1,0},
            {0,0,0,0,1,1,1,1,1,2},
            {0,0,0,0,0,1,0,1,1,0}};

    public void drawTester(Graphics g){

        g.setColor(Color.BLACK);
        //CELL_DYN_HEIGHT = g.getWidth();
        for(int i=0;i< testSize;i++ ){
            for(int j=0;j< testSize;j++){
                //System.out.print("\t" + mazeStatic[i][j]);


                if(mazeStatic[i][j]!=1 && mazeStatic[i][j]!=2){
                    continue;
                }

//                g.drawLine(j * CELL_WIDTH + MARGIN, i * CELL_WIDTH
//                        + MARGIN, j * CELL_WIDTH + MARGIN, i * CELL_WIDTH
//                        + MARGIN);


                try{
                    if(mazeStatic[i+1][j] != 0 ){        //THIS IS SOUTH CASE
                        //System.out.println("SOUTH Coor X "+i+" Coor Y "+j+ " VAL "+ mazeStatic[i+1][j]);
                        g.drawLine(j * CELL_WIDTH + MARGIN, i * CELL_WIDTH
                                + MARGIN, j * CELL_WIDTH + MARGIN, (i+1) * CELL_WIDTH
                                + MARGIN);
                    }


                }catch(ArrayIndexOutOfBoundsException oe){

                }

                try{
                    if(mazeStatic[i][j+1] != 0){        //THIS IS WEST CASE
                        //System.out.println("EAST Coor X "+i+" Coor Y "+j+ " VAL "+ mazeStatic[i][j+1]);
                        g.drawLine((j) * CELL_WIDTH + MARGIN, i * CELL_WIDTH
                                + MARGIN, (j+1) * CELL_WIDTH + MARGIN, i * CELL_WIDTH
                                + MARGIN);
                    }

                }catch(ArrayIndexOutOfBoundsException oe){

                }

                if(mazeStatic[i][j]==2){    //When array has the value 2, it means we are on termination point. DOT_SIZE/2 is used in order to find the circle of the circle we are drawing
                    g.setColor(Color.RED);
                    g.fillOval(j * CELL_WIDTH + MARGIN-(DOT_SIZE/2), i * CELL_WIDTH
                            + MARGIN-(DOT_SIZE/2), DOT_SIZE, DOT_SIZE);
                    g.setColor(Color.BLACK);
                }


            }
            //System.out.println("");
        }
        //System.out.println("\n\n");
    }

    public Dimension windowSize() // returns the ideal size of the window (for
    // JScrollPanes)
    {
        return new Dimension(CELL_WIDTH + MARGIN * 2, CELL_WIDTH + MARGIN
                * 2);
    }
}