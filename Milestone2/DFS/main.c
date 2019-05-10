#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>


static int sizeMatr = 5;
int initCoor[2] = {-1,-1};

/*
 * Static array used in our scenarios. If you wish to modify dont forget to change
 * sizeMatr accordingly
 * 1 used as walls
 * 2 used as visiting nodes
 * 7 used for nodes that are already visited
 * 9 used as termination point
 * 3 used as starting point
 */
int sampleMatrix[5][5] ={
        {3,2,2,2,2},
        {1,1,1,2,2},
        {2,1,2,2,2},
        {2,1,1,2,2},
        {2,2,2,2,1}
};

/*
 * Function used to print sampleMatrix and will showcase our modifications after dfs algorithm is applied
 */
void matrixPrinter(){
    printf("\nPRINT SAMPLE MATRIX\n\n");
    for(int i = 0;i< sizeMatr;i++){
        for(int j=0; j<sizeMatr;j++){
            printf("%d\t",sampleMatrix[i][j]);
        }
        printf("\n");
    }
}


/*
 * Function used to track our starting point. Starting point is highlighted with number 3.
 * Our matrix MUST include a starting point otherwise the program terminates
 */
void startFinder(){

    for(int i = 0;i< sizeMatr;i++){
        for(int j=0; j<sizeMatr;j++){
            if(sampleMatrix[i][j] == 3){
                printf("\nSTARTING POINT:[%d,%d]\n",i,j);
                initCoor[0] = i;
                initCoor[1] = j;
                return;
            }
        }
    }

    /*
     * This code is used for demontration puproses so we define our array and our tests
     * accordingly. We are not supposed to reach this part of the code and exit. But we
     * add this case to prevent errors.This case will be reached if our sampleMatrix has no value 3
     * (starting Point)
     */
    printf("No starting point was defined. Exiting.\n");
    exit(-9);


}


/*
 * Function that applies depth first search algorithm.The function works recursively
 */
int dfs(int row, int col)
{
    //Don't go out of bounds
    if (row < 0 || row > sizeMatr || col < 0 || col > sizeMatr){
        return 0;
    }

    if (sampleMatrix[row][col] == 9) {
        return 1;
    }

    /*
     * Our implementation applies dfs for nodes first upwards(UP), then leftwards(LEFT), then downwards
     * (DOWN) and finally rightwatds(RIGHT)
     */

    if (sampleMatrix[row][col] == 2 || sampleMatrix[row][col] == 3) {
        sampleMatrix[row][col] = 7;

        if (dfs(row - 1, col)){
            sampleMatrix[row][col] = 7;
            return 1;
        }

        if (dfs(row, col - 1)){
            sampleMatrix[row][col] = 7;
            return 1;
        }

        if (dfs(row + 1, col)){
            sampleMatrix[row][col] = 7;
            return 1;
        }

        if (dfs(row, col + 1)){
            sampleMatrix[row][col] = 7;
            return 1;
        }

    }

    return 0;
}



/*
 * Our main function.All the functions used are explained above.Not much to talk about
 */
int main() {
    matrixPrinter();

    startFinder();

    dfs(initCoor[0],initCoor[1]);

    matrixPrinter();

    return 0;
}