import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
 
/**
 * <h1>Multiply two rectangular matrices</h1>
* The MatrixMultiplication program implements a console application that
* takes two file name inputs of files containing Matrix 1 and Matrix 2 respectively from user at run time
*Sample Matrices Format::
*1 2 3
*4 5 6	
* @author  Himanshu Jethawa
* @version 1.0
* @since   2018-02-05
*/
public class MatrixMultiplication
{
	/**
     *Variables in class
     */
	static String fileName1,fileName2;
	static int first[][],second[][],flagForInconsistent=0;

	public static void main(String args[])
   {
	   //Test paths
	   //"C:\\Users\\Himanshu\\eclipse-workspace\\MatrixMultiplication\\src\\Matrix1.txt"
	   //"C:\\Users\\Himanshu\\eclipse-workspace\\MatrixMultiplication\\src\\Matrix2.txt"
 
	   //Taking input from user
      Scanner in = new Scanner(System.in);
      System.out.println("Enter the two absolute file paths for matrix multiplication");
      fileName1=in.nextLine();
      fileName2=in.nextLine();

      //Generating the two arrays from file inputs
      try {
      first=readFileToMatrix(fileName1);	}
      catch(NumberOfColumnsInconsistentException e) {
    	  System.out.println("NumberOfColumnsInconsistentException: The first matrix file has inconsistent entries,please correct it!");
    	  flagForInconsistent=1;
      }
      try {
      second=readFileToMatrix(fileName2);
      }
      catch(NumberOfColumnsInconsistentException e) {
    	  System.out.println("NumberOfColumnsInconsistentException: The second matrix file has inconsistent entries");
    	  flagForInconsistent=1;
      }
      
 
      if(flagForInconsistent==1);
      else
      if ( first[0].length != second.length)
         System.out.println("Matrices with entered orders can't be multiplied with each other.");
      else
      {
        //Naive matrix multiplication Algorithm-- Complexity O(n^3)
    	  printMatrixMultiplication(first,second);
      }
      
   }
   
   /**
    * This method is used to convert the file input to 2d array, 
    * throws an exception if one or more rows of the file have different number of columns!
    * @param fileName Name of the file to convert into two dimensional array of matrix
    * @return int[][] two dimensional array representation of matrix
    * @throws NumberOfColumnsInconsistentException Whenever number of columns in rows are different in input file, this is thrown!
    */
   public static int[][] readFileToMatrix(String fileName) throws NumberOfColumnsInconsistentException{
	   int previousNumberOfRows=0;
	   int flag=0;
	   
	   // read in the data
	   ArrayList<ArrayList<Integer>> a = new ArrayList<ArrayList<Integer>>();
	   Scanner input=null;
	   try {
	   input = new Scanner(new File(fileName));
	   }
	   catch(Exception e) {
		   
	   }
	   while(input.hasNextLine())
	   {
	       Scanner colReader = new Scanner(input.nextLine());
	       ArrayList col = new ArrayList();
	       while(colReader.hasNextInt())
	       {
	           col.add(colReader.nextInt());
	       }
	       if(flag==0) {previousNumberOfRows=col.size();flag=1;}
           else if(previousNumberOfRows!=col.size()) {
        	   throw new NumberOfColumnsInconsistentException();
        	   }
	       a.add(col);
	   }
	   
	   //Convert Arraylist of integer to array of ints using Java 8
	   int[][] array = new int[a.size()][];
	   for (int i = 0; i < a.size(); i++) {
	       ArrayList<Integer> row = a.get(i);
	       array[i]=row.stream()
			  .mapToInt(Integer::intValue)
			  .toArray();
	   }
	   return array;
   }
   
   /**
    * This method is used to computer and print the multiplication of two matrices
    * @param first First matrix
    * @param second Second matrix
    */
   public static void printMatrixMultiplication(int first[][],int second[][]) {
	   int m, n, p, q, sum = 0, c, d, k;
	   m=first.length;n=first[0].length;p=second.length;q=second[0].length;
	   int multiply[][] = new int[m][q];
	   
	   for ( c = 0 ; c < m ; c++ )
       {
          for ( d = 0 ; d < q ; d++ )
          {   
             for ( k = 0 ; k < p ; k++ )
             {
                sum = sum + first[c][k]*second[k][d];
             }

             multiply[c][d] = sum;
             sum = 0;
          }
       }

       System.out.println("Product of entered matrices:-");

       for ( c = 0 ; c < m ; c++ )
       {
          for ( d = 0 ; d < q ; d++ )
             System.out.print(multiply[c][d]+"\t");

          System.out.print("\n");
       }
      
   }
}