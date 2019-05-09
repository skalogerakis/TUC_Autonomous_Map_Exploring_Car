package MainPackage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

/*
 * FileInputReader.java
 * Author: Stefanos Kalogerakis, Aris Zervakis
 * This java class is responsible to parse data from input file.
 * Once we find the input file from given path then this class updates
 * a 2D array which will be used later to print the final maze.
 * NOTE: Our array must be symmetrical
 */

public class FileInputReader {

    /*
        We initialize here with value 10 which is the max size of a possible array.
     */
    protected int finArrSize = 10;
    private ArrayList<Integer> data;

    /*
        Parse is used only inside the class. It takes as parameter a string of files given path
        We parse the file and all data is added dynamically in an arraylist.
        NOTE: values must have a comma as a delimeter
     */
   private List<Integer> parse(String srcFile) {
       data = new ArrayList<>();

       String line = null;//contain current line
       int lineNum = 1;//line number

       //Some exceptions here to prevent errors
       try {
           FileReader fileReader = new FileReader(srcFile);

           try (final BufferedReader reader = new BufferedReader(fileReader)) {
               while ((line = reader.readLine()) != null) {//try read line from reader
                   String[] split = line.split(",");

                   for (int i = 0; i < split.length; i++) {
                       data.add(Integer.parseInt(split[i].trim()));
                   }
                   //Update the size of symmetric array after find the size of the first line
                   if (lineNum == 1) {
                       finArrSize = split.length;
                   }
                   lineNum++;
               }
           } catch (NumberFormatException we){
               System.err.println("Something was wrong with data from input file. REMINDER: Data must be integers");
               exit(-1);
           } catch (Exception e) {
               System.err.println("line parsing " + lineNum + " : " + line+".Exiting");
               e.printStackTrace();
               exit(-1);
           }
       }catch (FileNotFoundException fe){
           System.err.println("Demanded file "+srcFile+" not found.Check that file path is correct!");
           exit(-1);
       }

       return data;
    }

    protected int[][] input2Darr(String srcFile){

        int [][] finArr = new int[finArrSize][finArrSize];
        int row = 0;
        int column = 0;

        parse(srcFile);

        for(int k=0; k < data.size();k++){

            if(row < finArrSize){
                if(column == finArrSize ){
                    column = 0;
                    row++;
                }
                try{
                    int tempor = data.get(k);
                    finArr[row][column] = data.get(k);
                    column++;
                }catch (ArrayIndexOutOfBoundsException io){
                    continue;
                }


            }
        }
//      Prints input file as 2D array for debbuging purposes only
//        for(int i=0; i < finArrSize;i++){
//            for(int j=0; j < finArrSize;j++){
//                System.out.print("\t"+finArr[i][j]);
//            }
//            System.out.println();
//        }

        return finArr;

    }


}
